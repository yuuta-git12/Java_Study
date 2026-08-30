package org.example;

// javac Main_t1_q28.java の場合: コンパイルエラーにならず正常に成功する
//
// コメントの引数指定 "00 01" 0010 0011 で実行した場合（java Main_t1_q28 "00 01" 0010 0011）:
//   args[0] = "00 01"（ダブルクォートで囲まれているためスペースを含む1個の引数）
//   args[1] = "0010"
//   args[2] = "0011"
// 実行結果: 0010 0011
//
// 理由:
//   シェル上で "00 01" のようにダブルクォートで囲むと、内部にスペースがあっても
//   1つの引数としてまとめて渡される。よって args の要素数は3個になり、
//   args[1] と args[2] は存在するため配列外アクセス（ArrayIndexOutOfBoundsException）は起きない。
public class Main_t1_q28 {
    // 引数："00 01" 0010 0011
    public static void main(String[] args){
        System.out.println(args[1] + " " + args[2]);
    }
}
