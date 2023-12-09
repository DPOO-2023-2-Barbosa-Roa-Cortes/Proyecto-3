package edu.dpoo.gui.cards;

import javax.swing.*;
import java.awt.*;

public class CustomerAccountPanel extends JPanel {
    public JTextField holderField;
    public JTextField numbersField;
    public JComboBox<Integer> expirationMonthComboBox;
    public JComboBox<Integer> expirationYearComboBox;
    public JPasswordField cvcField;
    public JButton payButton;
    public JButton cancelButton;
    public boolean reserve;

    public CustomerAccountPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        gbc.gridy++;
        add(new JLabel("Numbers:"), gbc);

        gbc.gridy++;
        add(new JLabel("Due date:"), gbc);

        gbc.gridy++;
        add(new JLabel("CVC:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        holderField = new JTextField(20);
        add(holderField, gbc);

        gbc.gridy++;
        numbersField = new JTextField(20);
        add(numbersField, gbc);

        JPanel dueDatePanel = new JPanel();
        dueDatePanel.setLayout(new FlowLayout());

        expirationMonthComboBox = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            expirationMonthComboBox.addItem(month);
        }
        dueDatePanel.add(expirationMonthComboBox);

        expirationYearComboBox = new JComboBox<>();
        int currentYear = java.time.Year.now().getValue();
        for (int year = currentYear; year <= currentYear + 15; year++)
            expirationYearComboBox.addItem(year);

        dueDatePanel.add(expirationYearComboBox);

        gbc.gridy++;
        add(dueDatePanel, gbc);

        gbc.gridy++;
        cvcField = new JPasswordField(5);
        add(cvcField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        payButton = new JButton("Make Payment");
        add(payButton, gbc);

        gbc.gridy++;
        cancelButton = new JButton("Cancel");
        add(cancelButton, gbc);
    }
}
