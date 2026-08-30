package org.example;

public class Main_t1_q5 {
    public static void main(String[] args){
        main(new Main_t1_q5(), args);
    }
    static void main(Main_t1_q5 m, String... s){
        // 実行結果: org.example.Main_t1_q5@<hash>,[Ljava.lang.String;@<hash>
        // m, s ともに toString() 未オーバーライドのため Object のデフォルト表記になる
        System.out.println(m + "," + s);
    }
}
