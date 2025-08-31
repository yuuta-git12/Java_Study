/*
 * ランレングス圧縮
 * 連続する文字を圧縮する
 * 例：aa0bcd1efg1gg0abc -> aA0bCd1eFg1gG0aBc
 * 圧縮した文字列を展開する
 * 例：aA0bCd1eFg1gG0aBc -> aa0bcd1efg1gg0abc
 */

package projava;

public class RunLengthConpression2 {
    public static void main(String[] args) {
        var data = "aa0bcd1efg1gg0abc";

        var builder = new StringBuilder();
        var large_flag = false;
        for(var ch : data.toCharArray()){
            if(Character.isDigit(ch)){
                // 数字が出現したとき
                if(ch == '0'){
                    large_flag = true;
                }else{
                    large_flag = false;
                }
                continue;
            }
            if(large_flag){
                builder.append(Character.toUpperCase(ch));
            }else{
                builder.append(ch);
            }
        }
        
        var result = builder.toString();
        System.out.println(data);
        System.out.println(result);
    }
}