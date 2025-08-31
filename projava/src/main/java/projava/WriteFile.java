package projava;

import java.io.IOException; // 例外を投げる可能性があるので、throws IOExceptionを宣言
import java.nio.file.Files; // ファイルを操作するためのクラス
import java.nio.file.Path; // ファイルのパスを表すクラス

public class WriteFile {
    public static void main(String[] args) {
        String message = """
                        Hello!
                        test
                        message
                        """;
        try {
            var path = Path.of("dataっd.txt"); // ファイルのパスを指定 Java8ではPaths.get("data.txt")
            Files.writeString(path, message);   // ファイルにメッセージを書き込む
            System.out.println(Files.size(path)); // ファイルのサイズを表示(バイト数)

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            System.out.println("処理が終わったよ");
        }
    }
}
