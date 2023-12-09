package edu.dpoo.db;

import edu.dpoo.accounts.AdminAccount;
import edu.dpoo.accounts.CustomerAccount;
import edu.dpoo.accounts.UserAccount;
import edu.dpoo.credit.CreditCard;
import edu.dpoo.entities.Branch;
import edu.dpoo.pdf.Billing;
import edu.dpoo.vehicles.MotorizedVehicle;
import edu.dpoo.vehicles.NonMotorizedVehicle;
import edu.dpoo.vehicles.Vehicle;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CompanyDB {
    private static final File registerFolder = new File(Objects.requireNonNull(CompanyDB.class.getResource("databases")).getPath());
    public static final CompanyDB INSTANCE = new CompanyDB();
    public static final Set<Integer> PAYMENT_IDs = new HashSet<>();
    private boolean initialized;

    private Map<String, UserAccount> accounts = new HashMap<>();
    private UserAccount employee;
    private CustomerAccount customer;

    private Map<String, CreditCard> payment = new HashMap<>();

    private List<Vehicle> vehicles = new LinkedList<>();

    private Map<String, Branch> branches = new HashMap<>();
    private String branch;
    private LocalDate startDate, endDate;

    private Vehicle vehicle;

    public static CompanyDB getInstance() {
        return INSTANCE;
    }

    public static String checkPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$")
                ? password
                : "Invalid password format";
    }

    public void init() throws IOException {
        if (initialized) return;
        initialized = true;
        loadAccounts();
        loadCards();
        loadVehicles();
        loadBranches();
        loadIDs();
    }

    private void loadIDs() {
        Arrays.stream(registerFolder.listFiles()).map(file -> Integer.parseInt(file.getName().split("\\.")[0]))
                .forEach(PAYMENT_IDs::add);
    }

    private void loadBranches() throws IOException {
        try (BufferedReader branchR = new BufferedReader(new FileReader(new File(registerFolder, "branches.csv")))) {
             branchR.lines().map(Branch::parse).forEach(branch -> branches.put(branch.getName(), branch));
        }
    }

    private void loadVehicles() throws IOException {
        try (BufferedReader vehicleR = new BufferedReader(new FileReader(new File(registerFolder, "vehicles.csv")))) {
            String[] array = vehicleR.lines().toArray(String[]::new);
            for (String line : array) {
                Vehicle vehicle;
                if (line.startsWith("Motorized;"))
                    vehicle = MotorizedVehicle.parse(line.substring(10));
                else if (line.startsWith("NonMotorized;"))
                    vehicle = NonMotorizedVehicle.parse(line.substring(13));
                else
                    vehicle = null;
                vehicles.add(Objects.requireNonNull(vehicle));
            }
        }
    }

    private void loadCards() throws IOException {
        try (BufferedReader cardR = new BufferedReader(new FileReader(new File(registerFolder, "credit_cards.csv")))) {
            cardR.lines().map(CreditCard::parse).forEach(card -> payment.put(card.getHolderFullName(), card));
        }
    }

    private void loadAccounts() throws IOException {
        try (BufferedReader companyR = new BufferedReader(new FileReader(new File(registerFolder, "company_accounts.csv")))) {
            String[] array = companyR.lines().toArray(String[]::new);
            for (String line : array) {
                UserAccount account;
                if (line.startsWith("GLOBAL;"))
                    account = AdminAccount.Global.parse(line.substring(7));
                else if (line.startsWith("LOCAL;"))
                    account = AdminAccount.Local.parse(line.substring(6));
                else
                    account = null;
                accounts.put(Objects.requireNonNull(account).getUsername(), account);
            }
        }

        try (BufferedReader customerR = new BufferedReader(new FileReader(new File(registerFolder, "customer_accounts.csv")))) {
            String[] array = customerR.lines().toArray(String[]::new);
            Arrays.stream(array).map(CustomerAccount::parse)
                    .forEach(account -> accounts.put(Objects.requireNonNull(account).getUsername(), account));
        }
    }

    public boolean isAvailable(Vehicle vehicle, LocalDate start, LocalDate end) {
        return vehicle.isRented() ? vehicle.getReturnDate().isBefore(start) || vehicle.getPickDate()
                .isAfter(end) : vehicle.getReactivationDate().isBefore(start);
    }

    public void makePayment(String holder, LocalDate start, LocalDate end, boolean reserve){
        Billing.makePayment(reserve, vehicle, payment.get(holder));
        vehicle.setStandBranchName(null);
        vehicle.setReactivationDate(null);
        vehicle.setClientUsername(holder);
        vehicle.setPickDate(start);
        vehicle.setReturnDate(end);
        vehicle.setReturnBranchName(branch);
        update();
    }

    private void update() {
        try {
            loadCards();
            loadVehicles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicle fetchVehicle(String branch, LocalDate start, LocalDate end, String returnBranch, Object category) {
        this.branch = returnBranch;
        this.startDate = start;
        this.endDate = end;
        if (category instanceof MotorizedVehicle.Category) {
            MotorizedVehicle.Category realCategory = (MotorizedVehicle.Category) category;
            this.vehicle = vehicles.stream().filter(v -> v instanceof MotorizedVehicle)
                    .map(MotorizedVehicle.class::cast)
                    .filter(v -> v.getCategory() == realCategory && isAvailable(vehicle, start, end) &&
                                 (v.getReturnBranchName().equals(branch) || v.getStandBranchName().equals(branch)))
                    .findFirst()
                    .orElse(null);
            return this.vehicle;
        } else if (category instanceof NonMotorizedVehicle.Category) {
            NonMotorizedVehicle.Category realCategory = (NonMotorizedVehicle.Category) category;
            this.vehicle = vehicles.stream().filter(v -> v instanceof NonMotorizedVehicle)
                    .map(NonMotorizedVehicle.class::cast)
                    .filter(v -> v.getKind() == realCategory && isAvailable(vehicle, start, end) &&
                                 (v.getReturnBranchName().equals(branch) || v.getStandBranchName().equals(branch)))
                    .findFirst()
                    .orElse(null);
            return this.vehicle;
        } else {
            return this.vehicle = null;
        }
    }


    public boolean loginCustomer(String username, String password) {
        UserAccount account = accounts.get(username);
        if (account == null || account.getClass() != CustomerAccount.class || !account.validate(username, password)
            || customer != null)
            return false;
        customer = (CustomerAccount) account;
        return true;
    }

    public void logoutCustomer() {
        customer = null;
    }

    public boolean addRegister(UserAccount account) {
        try {
            if (accounts.containsKey(account.getUsername())) return false;
            accounts.put(account.getUsername(), account);
            loadAccounts();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
