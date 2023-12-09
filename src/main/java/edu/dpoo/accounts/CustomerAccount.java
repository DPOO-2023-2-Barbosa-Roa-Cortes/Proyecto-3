package edu.dpoo.accounts;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter public class CustomerAccount extends UserAccount {
    private String address;
    private String city;
    private long cellphoneNumber;
    private LocalDate birthday;
    private String country;
    private String countryIndicative;
    private long identificationNumber;
    private License license;

    public CustomerAccount(String fullName, String username, int password, String address, String city,
                           long cellphoneNumber,
                           LocalDate birthday, String country, String countryIndicative, long identificationNumber,
                           License license) {
        super(fullName, username, password);
        this.address = address;
        this.city = city;
        this.cellphoneNumber = cellphoneNumber;
        this.birthday = birthday;
        this.country = country;
        this.countryIndicative = countryIndicative;
        this.identificationNumber = identificationNumber;
        this.license = license;
    }

    public static CustomerAccount parse(String text) {
        if (text.equals("null"))
            return null;
        String[] format = text.split(";");
        return new CustomerAccount(format[0], format[1], Integer.parseInt(format[2]), format[3], format[4],
                Long.parseLong(format[5]), LocalDate.parse(format[6]), format[7], format[8], Long.parseLong(format[9]),
                License.parse(format[10]));
    }

    @Override public String toString() {
        return address + ";" + city + ";" + cellphoneNumber + ";" + birthday + ";" + country + ";" + countryIndicative + ";" + identificationNumber + ";" + license;
    }

}
