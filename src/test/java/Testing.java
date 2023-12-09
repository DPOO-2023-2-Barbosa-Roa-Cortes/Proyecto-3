import edu.dpoo.db.CompanyDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class Testing {
    @Test void load() {
        assertDoesNotThrow(CompanyDB.getInstance()::init);
    }
}
