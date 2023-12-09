package edu.dpoo.vehicles;

import edu.dpoo.entities.GeoPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor @Getter @Setter public abstract class Vehicle {
    private static final double BASE_PRICE = 2500;

    private String identifier;
    private GeoPosition position;
    private String brand;
    private boolean rented;

    private String clientUsername;
    private LocalDate pickDate;
    private LocalDate returnDate;
    private String returnBranchName;

    private String standBranchName;
    private LocalDate reactivationDate;

    private DangerLevel dangerousness;

    public enum DangerLevel {Low, Medium, High}

    public double price() {
        return BASE_PRICE * (1 + (double) (dangerousness.ordinal() * 2) / 10);
    }

    public String specialString() {
        return "plate/id: " + identifier + ", brand: " + brand + ", dangerousness: " + dangerousness;
    }

    @Override public String toString() {
        return identifier + ";" + position + ";" + brand + ";" + rented + ";" + clientUsername + ";" + pickDate + ";" + returnDate + ";" + returnBranchName + ";" + standBranchName + ";" + reactivationDate + ";" + dangerousness;
    }
}
