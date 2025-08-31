package projava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WebClient {
    public static void main(String[] args) throws IOException {
        var domain = "example.com"; // ドメイン名
        try(var soc = new Socket(domain, 80); // ソケットを作成
            var pw  = new PrintWriter(soc.getOutputStream()); // 出力ストリームを作成
            var isr = new InputStreamReader(soc.getInputStream()); // 入力ストリームを作成
            var bur = new BufferedReader(isr)) // バッファリングされた入力ストリームを作成
            {
                pw.println("GET /index.html HTTP/1.1"); // リクエストを送信
                pw.println("Host: " + domain); // ホスト名を送信
                pw.println(); // 空行を送信
                pw.flush(); // 出力ストリームをフラッシュ(強制的にデータを送信)
                bur.lines().limit(18).forEach(System.out::println); // レスポンスを表示
            }
            
        
    }
}
