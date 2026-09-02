package org.example;

/**
 * 戻り値の型が {@link CharSequence} のメソッド x() を定義するインタフェース。
 */
interface X {
    /**
     * 何らかの文字列表現を返す。
     *
     * @return CharSequence 型のインスタンス
     */
    CharSequence x();
}

/**
 * インタフェース {@link X} を実装するクラス。
 * <p>
 * オーバーライドしたメソッド x() の戻り値の型を、
 * インタフェースで宣言された CharSequence ではなく
 * その具象サブタイプである StringBuilder にしている点がポイント。
 */
public class Main_t1_q44 implements X{
    String name = "Foo";

    /**
     * X#x() をオーバーライドしたメソッド。
     * <p>
     * 戻り値の型を CharSequence ではなく StringBuilder にしているが、
     * これはコンパイルエラーにならずオーバーライドとして成立する。
     * <p>
     * 理由: Java 5 以降、オーバーライド時の戻り値の型は
     * 元のメソッドの戻り値の型と完全に一致させる必要はなく、
     * その「サブタイプ（共変戻り値型 / covariant return type）」であれば許容される。
     * StringBuilder は CharSequence インタフェースを実装しているため
     * CharSequence のサブタイプにあたり、この条件を満たしている。
     * そのため呼び出し側は X 型として x() を呼んでも CharSequence として
     * 扱えるし、実際には StringBuilder のインスタンスが返る。
     *
     * @return name フィールドから生成した StringBuilder
     */
    public StringBuilder x(){ return new StringBuilder(name); }
}
