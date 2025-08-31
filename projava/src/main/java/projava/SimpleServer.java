package projava;

import java.io.IOException; // 例外を投げる可能性があるので、throws IOExceptionを宣言
import java.io.InputStream; // 入力ストリームを読み込む
import java.net.ServerSocket; // サーバーソケットを作成する
import java.net.Socket; // ソケットを作成する

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        var server = new ServerSocket(1700); // サーバーソケットを作成する
        System.out.println("Waiting..."); // 待機する
        try (Socket soc = server.accept();
            InputStream input = soc.getInputStream()) 
        {
            System.out.println("connected from " + soc.getInetAddress());     // 接続したクライアントのアドレスを表示する
            System.out.println(input.read());   // 入力ストリームを読み込む    
        }
    }
}
