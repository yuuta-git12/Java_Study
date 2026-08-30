package org.example;

// このファイル名は Main_t1_q8.java だが、public クラス名は A（不一致）
//
// javac Main_t1_q8.java の場合:
//   コンパイルエラー「クラス A は public であり、ファイル A.java で宣言する必要があります」
//   → javac は public クラス名とファイル名の一致を必須とする
//
// java Main_t1_q8.java（ソースファイルモード, JEP 330）の場合:
//   コンパイルエラーにならず実行できる。結果: 7
//   → ソースファイルモードはメモリ上でコンパイルして即実行するだけなので、
//     public クラス名とファイル名の一致は要求されない（.class もディスクに残らない）
public class A {
    public static void main(String... args){
        // 0b0100 (2進数=4) | 3 → 0100 | 0011 = 0111 = 7
        System.out.println(0b0100 | 3);
    }
}
