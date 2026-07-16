package projava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwitchSampleTest {

    private void runWithInput(String input) {
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testSwitchStatement() {
        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        try {
            // number = 1 -> "one-two"
            runWithInput("1\n");
            SwitchSample.main(new String[]{});
            assertTrue(out.toString().contains("one-two"));
            out.reset();

            // number = 3 -> "three"
            runWithInput("3\n");
            SwitchSample.main(new String[]{});
            assertTrue(out.toString().contains("three"));
            out.reset();

            // number = 10 -> "other"
            runWithInput("10\n");
            SwitchSample.main(new String[]{});
            assertTrue(out.toString().contains("other"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
} 