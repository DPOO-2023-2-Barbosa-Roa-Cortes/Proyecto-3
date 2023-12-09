package edu.dpoo.gui;

import edu.dpoo.db.CompanyDB;
import edu.dpoo.gui.cards.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class CustomerView extends JFrame implements ActionListener {
    private CompanyDB db = CompanyDB.getInstance();
    private CustomerLoginPanel loginPanel;
    private CustomerRegisterPanel signupPanel;
    private CustomerMainPanel homePanel;
    private CustomerAccountPanel payingPanel;
    private CardLayout layout = new CardLayout();

    public CustomerView() {
        super("EasyDrive Car Rentals");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(layout);
        try {
            this.db.init();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading database", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        this.loginPanel = new CustomerLoginPanel();
        loginPanel.loginButton.addActionListener(this);
        loginPanel.signUpButton.addActionListener(this);
        add(loginPanel, "login");

        this.signupPanel = new CustomerRegisterPanel();
        signupPanel.loginButton.addActionListener(this);
        signupPanel.signUpButton.addActionListener(this);
        add(signupPanel, "signup");

        this.homePanel = new CustomerMainPanel();
        homePanel.continueButton.addActionListener(this);
        add(homePanel, "home");

        this.payingPanel = new CustomerAccountPanel();
        payingPanel.cancelButton.addActionListener(this);
        payingPanel.payButton.addActionListener(this);
        add(payingPanel, "paying");

        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new CustomerView();
    }

    @Override public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        // Identifica la estrategia según la fuente del evento
        StrategyUtils strategy = null;
        if (source == loginPanel.loginButton) {
            strategy = StrategyUtils.L_LOGIN;
        } else if (source == loginPanel.signUpButton) {
            strategy = StrategyUtils.L_SIGNUP;
        } else if (source == signupPanel.loginButton) {
            strategy = StrategyUtils.S_LOGIN;
        } else if (source == signupPanel.signUpButton) {
            strategy = StrategyUtils.S_SIGNUP;
        } else if (source == homePanel.logOutButton) {
            strategy = StrategyUtils.CH_LOGOUT;
        } else if (source == homePanel.continueButton) {
            strategy = StrategyUtils.CH_CONTINUE;
        } else if (source == payingPanel.cancelButton) {
            strategy = StrategyUtils.CP_CANCEL;
        } else if (source == payingPanel.payButton) {
            strategy = StrategyUtils.CP_PAY;
        }

        if (strategy != null) {
            strategy.execute(this);
        }
    }


    public String checkPassword(String password) {
        return CompanyDB.checkPassword(password);
    }

    public boolean isDecimal(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isLocalDate(String s) {
        try {
            java.time.LocalDate.parse(s);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean isAvailableRange(LocalDate start, LocalDate end) {
        LocalDate now = LocalDate.now();
        return start.isAfter(now) || end.isBefore(start);
    }
}
