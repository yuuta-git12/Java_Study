/** 
 * ランレングス圧縮
 * 連続する文字を圧縮する
 * 例：abbcccbaaaabcccccccccccddd -> a1b2c3b1a4b1c9d3
 * 圧縮した文字列を展開する
 * 例：a1b2c3b1a4b1c9d3 -> abbcccbaaaabcccccccccccddd
 * 
 * count = 0の場合: '0' + 0 = 48 + 0 = 48 → (char)48 = '0'
 * count = 1の場合: '0' + 1 = 48 + 1 = 49 → (char)49 = '1'
 * count = 2の場合: '0' + 2 = 48 + 2 = 50 → (char)50 = '2'
 * ...
 * count = 9の場合: '0' + 9 = 48 + 9 = 57 → (char)57 = '9'
 */


package projava;

public class RunLengthConpression {
    public static void main(String[] args) {
        final var COUNTER_BASE = -1;
        var data = "abbcccbaaaabcccccccccccddd";

        var count = COUNTER_BASE;
        char prev = 0;

        var builder = new StringBuilder();
        for(var ch : data.toCharArray()){
            if(prev == ch){
                // 同じ文字が続くとき
                count++;
                if(count == 9){
                    builder.append('9');
                    count = COUNTER_BASE;
                    prev = 0;
                }
            }else{
                // 異なる文字が出現したとき
                if(count >= 0){
                    builder.append((char)('0' + count));    // 数字を文字に変換 0~9 を文字に変換
                    count = COUNTER_BASE;
                }
                builder.append(ch);
                prev = ch;
            }
        }
        if(count >= 0){
            builder.append((char)('0' + count));
        }
        var result = builder.toString();
        System.out.println(data);
        System.out.println(result);
    }
}
