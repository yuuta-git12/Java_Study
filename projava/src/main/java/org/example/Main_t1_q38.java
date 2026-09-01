package org.example;

/**
 * name(String)を1つだけ持つイミュータブルなレコードクラス。
 * equals/hashCode/toString、アクセサ{@code name()}が自動生成される。
 */
record Country(String name){}

/**
 * 参照型フィールド（オブジェクト参照 / String）の {@code ==} 比較の挙動を確認するための検証用クラス。
 */
public class Main_t1_q38 {
    String name;
    Country country;
    Main_t1_q38(String name, Country country){
        this.name = name;
        this.country = country;
    }
}

/**
 * {@code ==} 演算子による同一性比較（オブジェクト参照比較・文字列リテラルのインターン）の違いを検証するエントリポイント。
 * <p>実行結果: {@code Y:Y:Y:N:}</p>
 */
class Main38 {
    public static void main(String[] args){
        Main_t1_q38 c1 = new Main_t1_q38("Duke", new Country("US"));
        Main_t1_q38 c2 = c1;                                                  // c2はc1と同一の参照（同じインスタンスを指す）
        Main_t1_q38 c3 = new Main_t1_q38("Carol", new Country("US"));
        Main_t1_q38 c4 = new Main_t1_q38("Johan", new Country("Germany"));
        String result = "";
        result += c1 == c2 ? "Y:" : "N:";   // c1とc2は同一インスタンス参照 → true ➡️ "Y:"
        result += !(c2.country == c3.country) ? "Y:" : "N:"; // c2.countryとc3.countryは別々にnewした異なるインスタンス → == はfalse → !false=true ➡️ "Y:"
        result += c2.country.name() == c3.country.name() ? "Y:" : "N:"; // 両方とも文字列リテラル"US"（コンパイル時に文字列プールへインターンされ同一参照） → true ➡️ "Y:"
        result += c3.name == c4.name ? "Y:" : "N:"; // "Carol"と"Johan"は異なる文字列リテラル → false ➡️ "N:"
        System.out.println(result); // "Y:Y:Y:N:" を出力
    }
}
