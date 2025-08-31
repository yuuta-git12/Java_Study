package projava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebClient2 {
    public static void main(String[] args)
        throws IOException, InterruptedException{   // 例外を投げる
            HttpClient client = HttpClient.newHttpClient();// クライアントを作成
            URI uri = URI.create("https://example.com");
            HttpRequest req = HttpRequest.newBuilder(uri).build();// リクエストを作成
            HttpResponse<String> response = client.send(    // リクエストを送信
                req, HttpResponse.BodyHandlers.ofString());// レスポンスを取得
            String body = response.body();// レスポンスの本文を取得
            body.lines().limit(5).forEach(System.out::println); // レスポンスの先頭5行を出力
            System.out.println(body);// レスポンスの全文を出力
        }
    
}
