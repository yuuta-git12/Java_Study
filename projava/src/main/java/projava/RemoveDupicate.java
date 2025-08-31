package projava;

public class RemoveDupicate {
    public static void main(String[] args) {
        var data = "abcccbaabcc";

        var builder = new StringBuilder();  // 文字列を作成     StringBuilderは文字列を作成するためのクラス
        for (int i = 0; i< data.length(); i++){
            char ch = data.charAt(i);  // 文字を取得
            if(i>0 && ch == data.charAt(i-1)){  // 前の文字と同じ場合   continueはループの先頭に戻る
                continue;
            }
            builder.append(ch);  // 文字を追加  StringBuilderは文字列を作成するためのクラス
        }
        var result = builder.toString();  // 文字列を作成  StringBuilderは文字列を作成するためのクラス
        System.out.println(data);
        System.out.println(result);
    }
}
