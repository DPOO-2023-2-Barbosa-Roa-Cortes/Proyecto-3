package edu.dpoo.credit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Getter @Setter public class CreditCard {
    private int numbers;
    private String holderFullName;
    private LocalDate expirationDate;
    private int cvc;
    private double capacity;
    private double retained;

    public static Optional<CreditCard> parse(String text) {
        String[] format = text.split(";");

        CreditCard instance;
        try {
            instance = MetaManager.fromName(format[0] + "CreditCard");
            instance.numbers = Integer.parseInt(format[0]);
            instance.holderFullName = format[1];
            instance.expirationDate = LocalDate.parse(format[2]);
            instance.cvc = Integer.parseInt(format[3]);
            instance.capacity = format.length > 4 ? Double.parseDouble(format[4]) : 1_000_000;
            instance.retained = format.length > 5 ? Double.parseDouble(format[5]) : 0;
            return Optional.of(instance);
        } catch (ReflectiveOperationException e) {
            return Optional.empty();
        }
    }

    public double getNetCapacity() {
        return capacity - retained;
    }

    public boolean retain(double price) {
        if (getNetCapacity() - price < 0)
            return false;
        retained -= price;
        return true;
    }

    public void freeze(double amount) {
        capacity -= amount * 0.3;
        retained -= amount * 0.7;
    }

    public void pay(double amount) {
        capacity -= amount;
    }

    public boolean compareSpecial(String holder, int numbers, LocalDate parse, int cvc) {
        return this.holderFullName.equals(holder) && this.numbers == numbers && parse.equals(this.expirationDate) && this.cvc == cvc;
    }

    @Override public String toString() {
        return String.valueOf(numbers) + ';' + holderFullName + ';' +
               expirationDate + ';' + cvc + ';' + capacity + ';' +
               retained;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return getNumbers() == that.getNumbers() && getCvc() == that.getCvc() && Double.compare(getCapacity(), that.getCapacity()) == 0 && Double.compare(getRetained(), that.getRetained()) == 0 && Objects.equals(getHolderFullName(), that.getHolderFullName()) && Objects.equals(getExpirationDate(), that.getExpirationDate());
    }
}
