package edu.dpoo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter public class Branch {
    private String name;
    private GeoPosition position;
    private Set<String> plates;
    private Set<String> employees = new HashSet<>();

    public Branch(String name, GeoPosition position, String... plates) {
        this.name = name;
        this.position = position;
        this.plates = Arrays.stream(plates).collect(Collectors.toSet());
    }
    public static Branch parse(String text) {
        String[] format = text.split(";");
        return new Branch(format[0], GeoPosition.parse(format[1]), format[2].split(","));
    }
}
