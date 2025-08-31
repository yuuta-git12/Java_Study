package projava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwitchSampleTest {
    
    @Test
    void testSwitchStatement() {
        // テスト用の出力をキャプチャするためのByteArrayOutputStreamを作成
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        
        try {
            // テストケース1: number = 1
            SwitchSample.main(new String[]{});
            String output = out.toString();
            assertTrue(output.contains("one-two"));
            
            // 出力をクリア
            out.reset();
            
            // テストケース2: number = 3
            SwitchSample.main(new String[]{});
            output = out.toString();
            assertTrue(output.contains("three"));
            
            // 出力をクリア
            out.reset();
            
            // テストケース3: number = 10 (デフォルトケース)
            SwitchSample.main(new String[]{});
            output = out.toString();
            assertTrue(output.contains("other"));
        } finally {
            // 標準出力を元に戻す
            System.setOut(System.out);
        }
    }
} 