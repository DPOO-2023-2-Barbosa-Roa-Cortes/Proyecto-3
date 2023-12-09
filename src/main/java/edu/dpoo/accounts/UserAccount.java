package edu.dpoo.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter public abstract class UserAccount {
    private final String fullName;
    private final String username;
    private final int password;

    public boolean validate(String username, String password) {
        return this.username.equals(username) && this.password == password.hashCode();
    }

    @Override public String toString() {
        return fullName + ";" + username + ";" + password;
    }
}
