package edu.dpoo.gui.cards;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Getter public class CustomerMainPanel extends JPanel {
    public JRadioButton motorizedRadioButton;
    public JComboBox<String> categoryOrKindComboBox;
    public JComboBox<String> pickUpBranchesComboBox;
    public JTextField startDate;
    public JTextField endDate;
    public JComboBox<String> returnBranchesComboBox;
    public JButton continueButton;
    public JButton logOutButton;

    public final String[] branches = new String[]{"", "SpeedyWheels Central", "Summit Drive Rent-a-Car",
            "UrbanRide Rentals", "Horizon Motors Hub", "Coastal Cruisers Center", "MetroMile Car Hire",
            "ValleyView Auto Rentals", "Skyline Wheels Station", "Oasis Rent-a-Car Plaza", "Sunrise Motors Depot"};
    public final String[] categories = new String[]{"", "Economy", "Compact", "Standard", "Van", "Luxury"};
    public final String[] kinds = new String[]{"", "Bikes", "ElectricBike", "Skateboard", "ElectricSkateboard"};

    public CustomerMainPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        motorizedRadioButton = new JRadioButton("Motorized");
        motorizedRadioButton.setSelected(true);
        motorizedRadioButton.addActionListener((ActionEvent event) -> change());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(motorizedRadioButton, gbc);

        JLabel categoryOrKindLabel = new JLabel("Category/Kind:");
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(categoryOrKindLabel, gbc);

        categoryOrKindComboBox = new JComboBox<>(categories);
        categoryOrKindComboBox.setPreferredSize(new Dimension(165, 25));
        gbc.gridx++;
        add(categoryOrKindComboBox, gbc);

        JLabel pickUpBranchesLabel = new JLabel("Pick Up Branch:");
        gbc.gridx = 0;
        gbc.gridy++;
        add(pickUpBranchesLabel, gbc);

        pickUpBranchesComboBox = new JComboBox<>(branches);
        gbc.gridx++;
        add(pickUpBranchesComboBox, gbc);

        JLabel startDateLabel = new JLabel("Start Date:");
        gbc.gridx = 0;
        gbc.gridy++;
        add(startDateLabel, gbc);

        startDate = new JTextField(10);
        gbc.gridx++;
        add(startDate, gbc);

        JLabel endDateLabel = new JLabel("End Date:");
        gbc.gridx = 0;
        gbc.gridy++;
        add(endDateLabel, gbc);

        endDate = new JTextField(10);
        gbc.gridx++;
        add(endDate, gbc);

        JLabel returnBranchesLabel = new JLabel("Return Branch:");
        gbc.gridx = 0;
        gbc.gridy++;
        add(returnBranchesLabel, gbc);

        returnBranchesComboBox = new JComboBox<>(branches);
        gbc.gridx++;
        add(returnBranchesComboBox, gbc);

        continueButton = new JButton("Continue");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(continueButton, gbc);

        logOutButton = new JButton("Log Out");
        gbc.gridy++;
        add(logOutButton, gbc);
    }

    public void change() {
        if (motorizedRadioButton.isSelected()) {
            categoryOrKindComboBox.removeAllItems();
            for (String category : categories)
                categoryOrKindComboBox.addItem(category);
        } else {
            categoryOrKindComboBox.removeAllItems();
            for (String kind : kinds)
                categoryOrKindComboBox.addItem(kind);
        }
    }
}
