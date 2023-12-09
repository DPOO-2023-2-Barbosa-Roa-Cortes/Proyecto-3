package edu.dpoo.gui;

import edu.dpoo.db.CompanyDB;
import edu.dpoo.gui.cards.CustomerAccountPanel;
import edu.dpoo.gui.cards.CustomerLoginPanel;
import edu.dpoo.gui.cards.CustomerMainPanel;
import edu.dpoo.gui.cards.CustomerRegisterPanel;

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

        this.loginPanel = new CustomerLoginPanel(this);
        add(loginPanel, "login");
        this.signupPanel = new CustomerRegisterPanel(this);
        add(signupPanel, "signup");
        this.homePanel = new CustomerMainPanel(this);
        add(homePanel, "home");
        this.payingPanel = new CustomerAccountPanel(this);
        add(payingPanel, "paying");

        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new CustomerView();
    }

    @Override public void actionPerformed(ActionEvent e) {
        StrategyUtils.valueOf(e.getActionCommand()).execute(this);
    }

    public String checkPassword(String password) {
        return db.checkPassword(password);
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
