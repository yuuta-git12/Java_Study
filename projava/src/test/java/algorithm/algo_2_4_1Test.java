package algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class algo_2_4_1Test {

    private String runWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(out));
            // 引数：new String[]{}は必要なのか？
            // なんでalgo_2_4_1はインスタンスオブジェクトでなくて良いの？
            algo_2_4_1.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return out.toString().trim();
    }

    @Test
    void testN0() {
        // N=0: 2*0+3=3
        assertEquals("3", runWithInput("0"));
    }

    @Test
    void testN1() {
        // N=1: 2*1+3=5
        assertEquals("5", runWithInput("1"));
    }

    @Test
    void testN5() {
        // N=5: 2*5+3=13
        assertEquals("13", runWithInput("5"));
    }

    @Test
    void testNegative() {
        // N=-1: 2*(-1)+3=1
        assertEquals("1", runWithInput("-1"));
    }

    @Test
    void testLargeN() {
        // N=100: 2*100+3=203
        assertEquals("203", runWithInput("100"));
    }
}
