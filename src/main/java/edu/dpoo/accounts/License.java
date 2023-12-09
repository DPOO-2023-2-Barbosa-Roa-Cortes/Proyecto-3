package edu.dpoo.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor @Getter @Setter public class License {
    private long licenseNumber;
    private String expeditionCountry;
    private LocalDate expirationDate;

    public static License parse(String text) {
        String[] format = text.split(",");
        return new License(Long.parseLong(format[0]), format[1], LocalDate.parse(format[2]));
    }

    @Override public String toString() {
        return licenseNumber + "," + expeditionCountry + "," + expirationDate;
    }
}
