package org.example;

/**
 * フィールドのアクセス修飾子（protected / private）による継承・参照可否を
 * 確認するための親クラス。Main_t1_q41Bから継承される。
 */
public class Main_t1_q41 {
    protected int no = 100;          // protected: サブクラスから直接参照可能
    private String name = "Super";   // private: サブクラスからは継承されず、直接参照不可（サブクラス側で同名フィールドを定義しても別物として扱われる）
    void display(){ System.out.println(no + ":" + name);}   // "100:Super" を出力
}
