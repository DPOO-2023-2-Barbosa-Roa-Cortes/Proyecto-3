package edu.dpoo;

import edu.dpoo.gui.CustomerView;
import lombok.SneakyThrows;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashedDataFixer {
    public static final File DBS = new File(Objects.requireNonNull(HashedDataFixer.class.getResource("/databases"))
            .getPath().replace("%20", " "));
    public static final File BILLS = new File(Objects.requireNonNull(HashedDataFixer.class.getResource("/bills"))
            .getPath().replace("%20", " "));

    public static void main(String[] args) {
        System.out.println(HashedDataFixer.class.getResource("/bills"));
        File databaseFolder = DBS;

        String[][] read = IOUtils.read(databaseFolder);
        IOUtils.write(databaseFolder, read[0], read[1], read[2]);

        new CustomerView();
    }
}

class IOUtils {
    private IOUtils() {
        throw new UnsupportedOperationException();
    }

    @SneakyThrows static String[][] read(File databaseFolder) {

        try (BufferedReader unhashed = new BufferedReader(new FileReader(new File(databaseFolder, "un-hashed-sources.csv")))) {
            String[] data = unhashed.lines().toArray(String[]::new);

            int index = 1;

            List<String> company = new LinkedList<>();
            List<String> customers = new LinkedList<>();
            List<String> creditCards = new LinkedList<>();

            while (index < data.length && !data[index].isEmpty()) {
                String[] line = data[index].split(";");
                line[3] = String.valueOf(Objects.hash(line[3]));
                company.add(String.join(";", line));
                index++;
            }
            index += 2;

            while (index < data.length && !data[index].isEmpty()) {
                String[] line = data[index].split(";");
                line[2] = String.valueOf(Objects.hash(line[2]));
                customers.add(String.join(";", line));
                index++;
            }
            index += 2;

            while (index < data.length && !data[index].isEmpty()) {
                String[] line = data[index].split(";");
                line[1] = String.valueOf(Objects.hash(line[1]));
                creditCards.add(String.join(";", line));
                index++;
            }

            return new String[][]{
                    company.toArray(new String[0]),
                    customers.toArray(new String[0]),
                    creditCards.toArray(new String[0])
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void write(File databaseFolder, String[] company, String[] customer, String[] creditCards) {
        try (BufferedWriter companyAccounts = new BufferedWriter(new FileWriter(new File(databaseFolder, "companyAccounts.csv")));
             BufferedWriter customerAccounts = new BufferedWriter(new FileWriter(new File(databaseFolder, "customerAccounts.csv")));
             BufferedWriter creditCardsFile = new BufferedWriter(new FileWriter(new File(databaseFolder, "creditCards.csv")))) {
            for (String account : company) companyAccounts.append(account).append('\n');
            for (String account : customer) customerAccounts.append(account).append('\n');
            for (String account : creditCards) creditCardsFile.append(account).append('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
