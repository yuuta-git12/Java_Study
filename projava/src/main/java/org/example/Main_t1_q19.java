package org.example;

// javac Main_t1_q19.java の場合: コンパイルエラーにならず正常に成功する
//
// 実行結果（出力3行）:
//   c|d|
//   c|d|
//   c|d|
//
// 理由:
//   'c' は char リテラルで、文字コード（Unicode コードポイント）は 99 のため
//   int c = 'c'; により c は 99 になる。
//   3つの switch はいずれも同じ 99 を対象にしており、それぞれ異なる構文（従来のcolon+break方式、
//   Java14+ の arrow(->) 方式、Java14+ の colon+yield 方式）で書かれているだけで、
//   意味的にはすべて等価。case 99,100 に一致するのでどれも "c|d|" を返す。
//   これら3種類のswitch構文はいずれも文法として正しいためコンパイルは通る。
public class Main_t1_q19 {
    public static void main(String[] args){
        int c = 'c';
        String text = "";
        switch(c){
            case 97:
            case 98:
                text = "a|b|";
                break;
            case 99:
            case 100:
                text = "c|d|";
                break;
            case 101:
                text = "e|";
                break;
            default:
                text = "the others";
        }
        String text2 = switch(c){
            case 97,98->"a|b|";
            case 99,100->"c|d|";
            case 101->"e|";
            default -> "the others";
        };
        String text3 = switch(c){
            case 97,98:yield "a|b|";
            case 99,100:yield "c|d|";
            case 101:yield "e|";
            default:yield "the others";
        };


        System.out.println(text);
        System.out.println(text2);
        System.out.println(text3);
    }
}
