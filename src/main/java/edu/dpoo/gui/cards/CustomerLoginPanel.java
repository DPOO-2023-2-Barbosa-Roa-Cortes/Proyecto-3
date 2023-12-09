package edu.dpoo.gui.cards;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginPanel extends JPanel {
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton;
    public JButton signUpButton;

    public CustomerLoginPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridy++;
        add(new JLabel("Password:"), gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

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
