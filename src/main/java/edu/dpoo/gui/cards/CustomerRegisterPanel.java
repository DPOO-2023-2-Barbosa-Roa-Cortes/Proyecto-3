package edu.dpoo.gui.cards;

import javax.swing.*;
import java.awt.*;

public class CustomerRegisterPanel extends JPanel {
    // Account Fields
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JPasswordField confirmPasswordField;

    // Personal Information Fields
    public JTextField fullNameField;
    public JTextField addressField;
    public JTextField cityField;
    public JTextField cellphoneNumberField;
    public JTextField birthdayField; // JTextField for simplicity
    public JTextField countryField;
    public JTextField countryIndicativeField;
    public JTextField identificationNumberField;

    // License Information Fields
    public JTextField licenseNumberField;
    public JTextField expeditionCountryField;
    public JTextField expirationField; // JTextField for simplicity

    // Buttons
    public JButton loginButton;
    public JButton signUpButton;

    public CustomerRegisterPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Account Block
        gbc.gridy++;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        add(confirmPasswordField, gbc);

        // Personal Information Block
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Personal Information"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Full-name:"), gbc);

        gbc.gridx = 1;
        fullNameField = new JTextField(15);
        add(fullNameField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        addressField = new JTextField(15);
        add(addressField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("City:"), gbc);

        gbc.gridx = 1;
        cityField = new JTextField(15);
        add(cityField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Phone:"), gbc);

        gbc.gridx = 1;
        cellphoneNumberField = new JTextField(15);
        add(cellphoneNumberField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Birthday (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        birthdayField = new JTextField(15);
        add(birthdayField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Country:"), gbc);

        gbc.gridx = 1;
        countryField = new JTextField(15);
        add(countryField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Indicative (+1, +57, ...):"), gbc);

        gbc.gridx = 1;
        countryIndicativeField = new JTextField(15);
        add(countryIndicativeField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Identification:"), gbc);

        gbc.gridx = 1;
        identificationNumberField = new JTextField(15);
        add(identificationNumberField, gbc);
        // ... Continue adding other fields for personal information...

        // License Information Block
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("License Information"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("License Number:"), gbc);

        gbc.gridx = 1;
        licenseNumberField = new JTextField(15);
        add(licenseNumberField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Expedition country:"), gbc);

        gbc.gridx = 1;
        expeditionCountryField = new JTextField(15);
        add(expeditionCountryField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Expiration date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        expirationField = new JTextField(15);
        add(expirationField, gbc);

        // ... Continue adding other fields for license information...

        // Buttons
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginButton = new JButton("Login");
        add(loginButton, gbc);

        gbc.gridy++;
        signUpButton = new JButton("Sign Up");
        add(signUpButton, gbc);
    }
}
