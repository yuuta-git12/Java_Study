package projava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer  {
    public static void main(String[] args) throws IOException {
        var server = new ServerSocket(8880);    // サーバーソケットを作成
        for (;;) {  // 無限ループ
            try (Socket soc = server.accept();// ソケットを作成
                var isr = new InputStreamReader(soc.getInputStream());// 入力ストリームを作成
                var bur = new BufferedReader(isr);// バッファリングされた入力ストリームを作成
                var w = new PrintWriter(soc.getOutputStream());// 出力ストリームを作成
            ) {
                System.out.println("connected from" + soc.getInetAddress());
                bur.lines().takeWhile(line -> !line.isEmpty()).forEach(System.out::println);
                w.println("""
                        HTTP/1.1 200 OK
                        Content-Type: text/html
                        
                        <html><head><title>Hello</title></head>
                        <body><h1>Hello</h1>It works!</body></html>
                        """);
            }
        }
    }
}
