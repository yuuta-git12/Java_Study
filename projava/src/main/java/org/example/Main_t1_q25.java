package org.example;

// javac Main_t1_q25.java の場合: コンパイルエラーにならず正常に成功する
//
// 実行結果（出力2行）:
//   100.0:0
//   100.0:0
//
// 理由:
//   MyClass, フィールド a（static, パッケージプライベート）, メソッド getB()（パッケージプライベート）は
//   いずれも修飾子なし（デフォルトアクセス）だが、Main_t1_q25 と同じパッケージ(org.example)内なので
//   問題なくアクセスできる。
//   a は static 初期化ブロックで 100.0 が代入されるため、MyClass.a も new MyClass().a も 100.0。
//   b は int フィールドでデフォルト値 0 のまま初期化されないので getB() は常に 0 を返す。
//   （private static getA() はどこからも呼ばれていないが、未使用でもコンパイルエラーにはならず警告のみ）
public class Main_t1_q25 {
    public static void main(String[] args){
        System.out.println(MyClass.a + ":" + new MyClass().getB());
        System.out.println(new MyClass().a + ":" + new MyClass().getB());
    }
}

class MyClass{
    static double a;
    private int b;
    static { a = 100.0; }
    private static double getA() { return a; }
    int getB() { return b; }
    public String toString(){ return "MyClass:" + a + ',' + b; }
}
