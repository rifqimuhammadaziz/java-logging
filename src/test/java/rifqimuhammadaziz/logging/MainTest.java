package rifqimuhammadaziz.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Test
    void testLog() {
        System.out.println("Hello testing log.");
        log.info("Hello testing log.");
        System.out.println("Learning java logging");
        log.info("Learning java logging");
    }
}
