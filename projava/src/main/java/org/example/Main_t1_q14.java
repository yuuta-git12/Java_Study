package org.example;

// javac Main_t1_q14.java の場合: コンパイルエラーになる
//
//   Main_t1_q14.java:8: エラー: 変数VALUEは初期化されていない可能性があります
//           System.out.println(VALUE);
//
// 理由:
//   FLAG は final かつリテラル true で初期化されているため「定数変数」(JLS 4.12.4) となり、
//   !FLAG はコンパイル時定数式として false に確定する。
//   if 文は while/for と違い条件が定数でも「到達不可能な文」としては扱われないが、
//   確定代入(definite assignment)の解析上は「条件が定数 false の then 節は実行されない」
//   とみなされるため、VALUE = 100; は VALUE を確定代入したことにならない。
//   結果として if 文を抜けた時点で VALUE は「初期化されたとは限らない」状態のままとなり、
//   8行目の参照でコンパイルエラーになる。
//   （なお main(int a) は main(String[] args) とは別シグネチャなので、
//     メソッドのオーバーロードとしては合法。単にエントリポイントとしては認識されない）
public class Main_t1_q14 {
    protected void main(int a){
        final var FLAG = true;
        final int VALUE;
        if(!FLAG)VALUE = 100;
        System.out.println(VALUE);
    }

    public static void main(String[] args){
        new Main_t1_q14().main(100);
    }
}
