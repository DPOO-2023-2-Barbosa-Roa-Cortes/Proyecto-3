package edu.dpoo.vehicles;

import edu.dpoo.entities.GeoPosition;
import lombok.Getter;

import java.time.LocalDate;

@Getter public class MotorizedVehicle extends Vehicle {
    private final Category category;
    private final String model;
    private final String transmissionType;

    public MotorizedVehicle(String identifier, GeoPosition position, String brand, boolean rented,
                            String clientUsername, LocalDate pickDate, LocalDate returnDate, String returnBranchName,
                            String standBranchName, LocalDate reactivationDate, DangerLevel dangerousness,
                            Category category, String model, String transmissionType) {
        super(identifier, position, brand, rented, clientUsername, pickDate, returnDate, returnBranchName, standBranchName,
                reactivationDate, dangerousness);
        this.category = category;
        this.model = model;
        this.transmissionType = transmissionType;
    }

    public static MotorizedVehicle parse(String text) {
        String[] format = text.split(";");
        return new MotorizedVehicle(format[0], GeoPosition.parse(format[1]), format[2], Boolean.parseBoolean(format[3]),
                format[4].equals("null") ? null : format[4], format[5].equals("null") ? null : LocalDate.parse(format[5]),
                format[6].equals("null") ? null : LocalDate.parse(format[6]), format[7].equals("null") ? null : format[7],
                format[8].equals("null") ? null : format[8], format[9].equals("null") ? null : LocalDate.parse(format[9]),
                DangerLevel.valueOf(format[10]), Category.valueOf(format[11]), format[12], format[13]);
    }

    @Override public double price() {
        return super.price() * (1 + (double) (category.ordinal() * 2) / 10);
    }

    @Override public String toString() {
        return "Motorized;" + super.toString() + ";" + category + ";" + model + ";" + transmissionType;
    }

    public enum Category {
        Economy, Compact, Standard, Van, Luxury
    }
}
