package org.example;

interface X45 {
    double POINT_RATE = 0.05;
    default Number calculate (int price){
        return (int)(price * POINT_RATE);
    }
}
interface Y45 {
    double TAX_RATE = 0.1;
    default Number calculate (int price){
        return (int)(price * TAX_RATE);
    }
}

/**
 * X45 と Y45 の両方を実装するクラス。
 * <p>
 * どちらのインタフェースも同じシグネチャの default メソッド
 * {@code calculate(int)} を持っているため、このままでは
 * 「class One inherits unrelated defaults for calculate() from types
 * X45 and Y45」というコンパイルエラーになる（ダイヤモンド問題）。
 * Java はどちらの default メソッドを使うべきか自動で判断できないため、
 * One 自身で calculate(int) をオーバーライドして明示的に解決する必要がある。
 * 解決方法は主に次の3パターン。
 *
 * <pre>{@code
 * // パターン1: X45側のdefault実装を明示的に使う
 * class One implements X45, Y45 {
 *     public Number calculate(int price) {
 *         return X45.super.calculate(price);
 *     }
 * }
 *
 * // パターン2: Y45側のdefault実装を明示的に使う
 * class One implements X45, Y45 {
 *     public Number calculate(int price) {
 *         return Y45.super.calculate(price);
 *     }
 * }
 *
 * // パターン3: どちらにも委譲せず、独自の実装で上書きする
 * class One implements X45, Y45 {
 *     public Number calculate(int price) {
 *         return 0;
 *     }
 * }
 * }</pre>
 */
class One implements X45, Y45 {
    public Number calculate(int price) {
        return X45.super.calculate(price);
    }
}
class Two extends One {}

public class Main_t1_q45 {

}
