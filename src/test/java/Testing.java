import edu.dpoo.accounts.CustomerAccount;
import edu.dpoo.accounts.License;
import edu.dpoo.db.CompanyDB;
import edu.dpoo.vehicles.MotorizedVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {
    @BeforeEach void init() {
        CompanyDB.getInstance().fetchVehicle("SpeedyWheels Central", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-10"), "SpeedyWheels Central", MotorizedVehicle.Category.Van);
    }

    @Test void load() {
        assertDoesNotThrow(CompanyDB.getInstance()::init);
    }

    @Test void createAccount() {
        assertTrue(CompanyDB.getInstance().addRegister(new CustomerAccount("test", "main", Objects.hash("root"), "example@example.com", "Here", 1000000, LocalDate.parse("2001-09-11"), "Over-there", "+1", 2343241242L, new License(12312412L, "Colombia", LocalDate.parse("2050-01-01")))));
    }

    @Test void makeReservation() {
        assertDoesNotThrow(() -> CompanyDB.getInstance().makePayment("John Smith", LocalDate.parse("2025-11-01"), LocalDate.parse("2028-02-01"), true));
    }

    @Test void login() {
        assertTrue(CompanyDB.getInstance().loginCustomer("johnsmith123", "Pass@word123"));
    }
}
