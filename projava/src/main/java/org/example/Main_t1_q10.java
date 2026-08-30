package org.example;

// javac Main_t1_q10.java の場合: コンパイルエラーになる
//
//   Main_t1_q10.java:30: エラー: クラス Main_t1_q10のコンストラクタ Main_t1_q10は指定された型に適用できません。
//           System.out.println(new Main_t1_q10());
//     期待値: String
//     検出値:    引数がありません
//
// 理由:
//   20行目の "void Main_t1_q10()" はクラス名と同じ名前だが戻り値の型 void があるため、
//   コンストラクタではなく単なる（紛らわしい）通常のメソッドとして扱われる。
//   23行目の "public Main_t1_q10(String name)" が唯一の実コンストラクタ（String 引数1個）。
//   明示的にコンストラクタを1つでも定義すると、引数なしのデフォルトコンストラクタは
//   自動生成されなくなるため、30行目の "new Main_t1_q10()"（引数なし）に対応する
//   コンストラクタが存在せずコンパイルエラーになる。
//   （31行目の "new Main_t1_q10(\"Coffee\")" は String 版コンストラクタに合致するが、
//     30行目のエラーで止まるためコンパイルまで到達しない）
//
//   また23行目の "name = name;" はコンストラクタの仮引数 name を自分自身に代入しているだけで、
//   フィールド this.name には代入されていない（コンパイルは通るがバグ）。
public class Main_t1_q10 {
    private String name;
    void Main_t1_q10(){
        name = "Something";
    }
    public Main_t1_q10(String name){
        name = name;
    }
    public String toString(){
        return " Item:" + name;
    }
    public static void main(String...args){
        System.out.println(new Main_t1_q10());
        System.out.println(new Main_t1_q10("Coffee"));
    }
}
