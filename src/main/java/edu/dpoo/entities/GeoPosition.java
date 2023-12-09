package edu.dpoo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter public class GeoPosition {
    private final double latitude;
    private final double longitud;
    private final double radius;

    public static GeoPosition parse(String text) {
        String[] format = text.substring(1, text.length() - 1).split(", ");
        return new GeoPosition(Double.parseDouble(format[0]), Double.parseDouble(format[1]),
                format.length == 3 ? Double.parseDouble(format[2]) : 0);
    }
}
