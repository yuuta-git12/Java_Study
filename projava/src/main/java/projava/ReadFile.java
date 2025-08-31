package projava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {
    public static void main(String[] args) throws IOException { // 例外を投げる可能性があるので、throws IOExceptionを宣言
        try {
            var path = Path.of("data.txta");
            String message = Files.readString(path); // ファイルの内容を読み込む
            System.out.println(message); 
        } catch (IOException e) {   // 例外をキャッチ
            e.printStackTrace();    // 例外のスタックトレースを表示
        }
    }
}
