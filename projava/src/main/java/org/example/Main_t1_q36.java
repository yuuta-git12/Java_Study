package org.example;

/**
 * 演算子の優先順位と型変換（文字列連結 vs 数値演算）を確認するための検証コード。
 * ※ 11行目でString型に対する二項演算子 '-' を使用しているためコンパイルエラーとなる。
 */
public class Main_t1_q36 {
    /**
     * value(=0)の値によって分岐し、文字列連結と数値演算の評価順序の違いを出力する。
     *
     * @param args コマンドライン引数（未使用）
     */
    public static void main(String[] args){
        int value = 0x0000;                                        // 16進数リテラルで0を代入（value = 0）
        if(value <= 0)                                             // value(0) <= 0 は true なのでこの分岐が実行される
            System.out.print("value:" + value + 2 + 10);           // 左から順に文字列連結: "value:"+0="value:0" → +2="value:02" → +10="value:0210" を出力（数値加算にはならない）
        else if(value > 0 && value < 10)
            System.out.print("value:" + value * 2 + 10);           // value*2(数値演算が先に評価)の結果を文字列連結
        else if(value >= 10 && value < 20)
            System.out.print("value:" + value - value);            // "value:"+value で文字列化された後、文字列-intとなりコンパイルエラーになる行
        else
            System.out.println("value:" + value + value);
    }
}
