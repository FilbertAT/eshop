package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodRuns() {
        // When calling the main method, if no exceptions are thrown, we consider the test passed.
        assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
    }

}
