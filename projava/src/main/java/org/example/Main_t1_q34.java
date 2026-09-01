package org.example;

public class Main_t1_q34 {                                // javac OK: publicクラス名がファイル名(Main_t1_q34.java)と一致している
    public String name;
    { name = "N/A"; }                                      // javac OK: インスタンス初期化ブロック。コンストラクタ実行前に評価される
    public Main_t1_q34(){}                                 // javac OK: 引数なしコンストラクタ。初期化ブロックによりname="N/A"になる
    public Main_t1_q34(String name){ this.name = name; }   // javac OK: オーバーロードされたコンストラクタ
}

class Main34 {                                             // javac OK: 1つの.javaファイルに複数クラスを定義可能（publicは1つまで、かつファイル名と一致している必要がある。Main34はpublicではないので制約対象外）
    public static void main(String[] args){
        Main_t1_q34 one = new Main_t1_q34("Duke");
        Main_t1_q34 two = one;
        two.name = "James";
        System.out.println(one.name + ":" + two.name + ":");
        Main_t1_q34 three = new Main_t1_q34();
        System.out.println(three.name);
    }
}
