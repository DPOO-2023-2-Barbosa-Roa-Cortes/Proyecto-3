package edu.dpoo.gui;

import edu.dpoo.accounts.CustomerAccount;
import edu.dpoo.accounts.License;
import edu.dpoo.db.CompanyDB;
import edu.dpoo.gui.cards.CustomerAccountPanel;
import edu.dpoo.gui.cards.CustomerLoginPanel;
import edu.dpoo.gui.cards.CustomerMainPanel;
import edu.dpoo.gui.cards.CustomerRegisterPanel;
import edu.dpoo.vehicles.MotorizedVehicle;
import edu.dpoo.vehicles.NonMotorizedVehicle;
import edu.dpoo.vehicles.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

import static edu.dpoo.gui.CustomerView.*;

public enum StrategyUtils {
    L_LOGIN {
        @Override String execute(CustomerView view) {
            // Access the required fields or methods from CustomerView
            CustomerLoginPanel loginPanel = view.getLoginPanel();
            CardLayout cardLayout = view.getLayout();
            CompanyDB database = view.getDb();

            String username = loginPanel.usernameField.getText();
            String password = new String(loginPanel.passwordField.getPassword());
            loginPanel.usernameField.setText("");
            loginPanel.passwordField.setText("");

            if (username.isEmpty()) JOptionPane.showMessageDialog(view, "Please enter your username address.");
            else if (database.loginCustomer(username, password)) cardLayout.show(view.getContentPane(), "HomePanel");
            else JOptionPane.showMessageDialog(view, "Your username or password is incorrect.");

            return "Login executed";
        }
    }, L_SIGNUP {
        @Override public String execute(CustomerView view) {
            // Access the required fields or methods from CustomerView
            CustomerLoginPanel loginPanel = view.getLoginPanel();
            CustomerRegisterPanel signUpPanel = view.getSignupPanel();
            CardLayout cardLayout = view.getLayout();

            String username = loginPanel.usernameField.getText();
            String password = new String(loginPanel.passwordField.getPassword());
            signUpPanel.usernameField.setText(username);
            signUpPanel.passwordField.setText(password);

            cardLayout.show(view.getContentPane(), "SignUpPanel");

            return "Signup executed";
        }
    }, S_LOGIN {
        @Override public String execute(CustomerView view) {
            // Access the required fields or methods from CustomerView
            CustomerRegisterPanel signUpPanel = view.getSignupPanel();
            CustomerLoginPanel loginPanel = view.getLoginPanel();
            CardLayout cardLayout = view.getLayout();

            String username = signUpPanel.usernameField.getText();
            String password = new String(signUpPanel.passwordField.getPassword());
            loginPanel.usernameField.setText(username);
            loginPanel.passwordField.setText(password);

            cardLayout.show(view.getContentPane(), "LoginPanel");

            return "Switch to login executed";
        }
    }, S_SIGNUP {
        @Override public String execute(CustomerView view) {
            CustomerRegisterPanel signUpPanel = view.getSignupPanel();
            CustomerLoginPanel loginPanel = view.getLoginPanel();
            CardLayout cardLayout = view.getLayout();
            CompanyDB database = view.getDb();

            String username = signUpPanel.usernameField.getText().trim();
            String password = CompanyDB.checkPassword(new String(signUpPanel.passwordField.getPassword()).trim());

            if (username.length() < 5) {
                JOptionPane.showMessageDialog(view, (username.isEmpty() ? "Username" : "Username must be at least 5 characters") + " expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }

            if (password == null || !password.equals(new String(signUpPanel.confirmPasswordField.getPassword()).trim())) {
                JOptionPane.showMessageDialog(view, (password == null ? "Invalid password" : "Passwords don't match"), "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }

            String fullName = signUpPanel.fullNameField.getText().trim();
            if (fullName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Full-name expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String address = signUpPanel.addressField.getText().trim();
            if (address.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Address expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String city = signUpPanel.cityField.getText().trim();
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(view, "City expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String cellphone = signUpPanel.cellphoneNumberField.getText().trim();
            if (cellphone.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Phone expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!isDecimal(cellphone)) {
                JOptionPane.showMessageDialog(view, "Phone expected an integer", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String birthday = signUpPanel.birthdayField.getText().trim();
            if (birthday.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Birthday expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!isLocalDate(birthday)) {
                JOptionPane.showMessageDialog(view, "Birthday expected YYYY-MM-DD", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String country = signUpPanel.countryField.getText().trim();
            if (country.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Country expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String indicative = signUpPanel.countryIndicativeField.getText().trim();
            if (indicative.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Indicative expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!indicative.startsWith("+")) {
                JOptionPane.showMessageDialog(view, "Indicative format is not correct", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (indicative.substring(1).isEmpty()) {
                JOptionPane.showMessageDialog(view, "Indicative not detected", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!isDecimal(indicative.substring(1))) {
                JOptionPane.showMessageDialog(view, "Values after '+' in indicative must be integers", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String identification = signUpPanel.identificationNumberField.getText().trim();
            if (identification.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Identification expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!isDecimal(identification)) {
                JOptionPane.showMessageDialog(view, "Identification expected an integer", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String licenseNumber = signUpPanel.licenseNumberField.getText().trim();
            if (licenseNumber.isEmpty()) {
                JOptionPane.showMessageDialog(view, "License Number expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!isDecimal(licenseNumber)) {
                JOptionPane.showMessageDialog(view, "License Number expected an integer", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String expeditionCountry = signUpPanel.expeditionCountryField.getText().trim();
            if (expeditionCountry.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Expedition Country expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            String expirationDate = signUpPanel.expirationField.getText().trim();
            if (expirationDate.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Expiration Date expected a value", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            } else if (!isLocalDate(expirationDate)) {
                JOptionPane.showMessageDialog(view, "Expiration Date expected YYYY-MM-DD", "WARNING", JOptionPane.WARNING_MESSAGE);
                return "Signup failed";
            }
            if (database.exists(username)) {
                JOptionPane.showMessageDialog(view, String.format("username %s already exists", username));
                return "Signup failed";
            }

            CustomerAccount customer = new CustomerAccount(signUpPanel.fullNameField.getText()
                    .trim(), username, Objects.hash(password), signUpPanel.addressField.getText()
                    .trim(), signUpPanel.cityField.getText()
                    .trim(), Long.parseLong(signUpPanel.cellphoneNumberField.getText()
                    .trim()), LocalDate.parse(signUpPanel.birthdayField.getText()
                    .trim()), signUpPanel.countryField.getText().trim(), signUpPanel.countryIndicativeField.getText()
                    .trim(), Long.parseLong(signUpPanel.identificationNumberField.getText()
                    .trim()), new License(Long.parseLong(signUpPanel.licenseNumberField.getText()
                    .trim()), signUpPanel.expeditionCountryField.getText()
                    .trim(), LocalDate.parse(signUpPanel.expirationField.getText().trim())));

            if (!database.addRegister(customer)) {
                JOptionPane.showMessageDialog(view, "Something went wrong while trying to register the account", "ERROR", JOptionPane.ERROR_MESSAGE);
                return "Signup failed";
            }

            JOptionPane.showMessageDialog(view, "Account SignUp correctly");
            loginPanel.usernameField.setText(username);
            loginPanel.passwordField.setText(password);
            cardLayout.show(view.getContentPane(), "LoginPanel");

            return "Signup executed";
        }
    }, CH_LOGOUT {
        @Override public String execute(CustomerView view) {
            CompanyDB database = view.getDb();
            CardLayout cardLayout = view.getLayout();

            database.logoutCustomer();
            cardLayout.show(view.getContentPane(), "LoginPanel");

            return "Logout executed";
        }
    }, CH_CONTINUE {
        @Override public String execute(CustomerView view) {
            CustomerMainPanel homePanel = view.getHomePanel();
            CustomerAccountPanel payingPanel = view.getPayingPanel();
            CardLayout cardLayout = view.getLayout();
            CompanyDB database = view.getDb();

            String categoryOrKind = (String) Objects.requireNonNull(homePanel.getCategoryOrKindComboBox()
                    .getSelectedItem());
            String pickUpBranch = (String) Objects.requireNonNull(homePanel.getPickUpBranchesComboBox()
                    .getSelectedItem());
            String startDate = homePanel.getStartDate().getText().trim();
            String endDate = homePanel.getEndDate().getText().trim();
            String returnBranch = (String) Objects.requireNonNull(homePanel.getReturnBranchesComboBox()
                    .getSelectedItem());

            if (isInvalidInputForContinue(view, categoryOrKind, pickUpBranch, startDate, endDate, returnBranch))
                return "Continue failed";

            if (!isAvailableRange(LocalDate.parse(startDate), LocalDate.parse(endDate))) return "Continue failed";

            Vehicle vehicle = (homePanel.getMotorizedRadioButton()
                    .isSelected()) ? database.fetchVehicle(pickUpBranch, LocalDate.parse(startDate), LocalDate.parse(endDate), returnBranch, MotorizedVehicle.Category.valueOf(categoryOrKind)) : database.fetchVehicle(pickUpBranch, LocalDate.parse(startDate), LocalDate.parse(endDate), returnBranch, NonMotorizedVehicle.Category.valueOf(categoryOrKind));

            if (vehicle == null) {
                JOptionPane.showMessageDialog(view, "There's no available vehicles");
                return "Continue failed";
            }

            JOptionPane.showMessageDialog(view, "Total: USD $" + vehicle.price());
            payingPanel.reserve = LocalDate.parse(startDate).equals(LocalDate.now());
            cardLayout.show(view.getContentPane(), "PayingPanel");

            return "Continue executed";
        }

        private boolean isInvalidInputForContinue(CustomerView view, String categoryOrKind, String pickUpBranch,
                                                  String startDate, String endDate, String returnBranch) {
            if (categoryOrKind.isEmpty() || pickUpBranch.isEmpty() || startDate.isEmpty() || !isLocalDate(startDate) || endDate.isEmpty() || !isLocalDate(endDate) || returnBranch.isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields must be filled correctly", "WARNING", JOptionPane.WARNING_MESSAGE);
                return true;
            }
            return false;
        }
    }, CP_CANCEL {
        @Override public String execute(CustomerView view) {
            CardLayout cardLayout = view.getLayout();

            cardLayout.show(view.getContentPane(), "HomePanel");

            return "Payment cancellation executed";
        }
    }, CP_PAY {
        @Override public String execute(CustomerView view) {
            CustomerAccountPanel payingPanel = view.getPayingPanel();
            CardLayout cardLayout = view.getLayout();
            CompanyDB database = view.getDb();

            String holder = payingPanel.holderField.getText().trim();
            String numbers = payingPanel.numbersField.getText().trim();
            String expiration = String.format("%s-%s-01", payingPanel.expirationYearComboBox.getSelectedItem(), payingPanel.expirationMonthComboBox.getSelectedItem());
            String cvc = new String(payingPanel.cvcField.getPassword()).trim();

            if (isInvalidPaymentInput(view, holder, numbers, expiration, cvc)) {
                return "Payment failed";
            }

            try {
                database.makePayment(holder, LocalDate.parse(expiration), LocalDate.parse(expiration), payingPanel.reserve);
                JOptionPane.showMessageDialog(view, "Payment success");
                cardLayout.show(view.getContentPane(), "HomePanel");
            } catch (IllegalArgumentException ignored) {
                JOptionPane.showMessageDialog(view, "Not enough money to make the payment");
            }

            return "Payment executed";
        }

        private boolean isInvalidPaymentInput(CustomerView view, String holder, String numbers, String expiration,
                                              String cvc) {
            if (holder.isEmpty() || numbers.isEmpty() || !isDecimal(numbers) || numbers.startsWith("-") || numbers.length() != 16 || cvc.isEmpty() || !isDecimal(cvc) || cvc.startsWith("-") || cvc.length() != 3) {
                JOptionPane.showMessageDialog(view, "Invalid payment information, please check again", "WARNING", JOptionPane.WARNING_MESSAGE);
                return true;
            }
            return false;
        }
    };

    abstract String execute(CustomerView view);
}