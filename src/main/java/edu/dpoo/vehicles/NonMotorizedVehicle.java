package edu.dpoo.vehicles;

import edu.dpoo.entities.GeoPosition;
import lombok.Getter;

import java.time.LocalDate;

@Getter public class NonMotorizedVehicle extends Vehicle {
    private Category kind;

    public NonMotorizedVehicle(String identifier, GeoPosition position, String brand, boolean rented,
                               String clientUsername, LocalDate pickDate, LocalDate returnDate, String returnBranchName,
                               String standBranchName, LocalDate reactivationDate, DangerLevel dangerousness,
                               Category kind) {
        super(identifier, position, brand, rented, clientUsername, pickDate, returnDate, returnBranchName, standBranchName,
                reactivationDate, dangerousness);
        this.kind = kind;
    }

    public static NonMotorizedVehicle parse(String text) {
        String[] format = text.split(";");
        return new NonMotorizedVehicle(format[0], GeoPosition.parse(format[1]), format[2],
                Boolean.parseBoolean(format[3]), format[4].equals("null") ? null : format[4],
                format[5].equals("null") ? null : LocalDate.parse(format[5]),
                format[6].equals("null") ? null : LocalDate.parse(format[6]),
                format[7].equals("null") ? null : format[7], format[8].equals("null") ? null : format[8],
                format[9].equals("null") ? null : LocalDate.parse(format[9]), DangerLevel.valueOf(format[10]),
                Category.valueOf(format[11]));
    }

    @Override public double price() {
        return super.price() + (kind.name().startsWith("Electric") ? 1500 : 0);
    }

    @Override public String toString() {
        return "NonMotorizedVehicle;" + super.toString() + ";" + kind;
    }

    public enum Category {
        Bike, Scooter, Skateboard, ElectricBike, ElectricScooter, ElectricSkateboard
    }
}
