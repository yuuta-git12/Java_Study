package org.example;

import java.util.ArrayList;

/**
 * ローカル変数型推論（var）の使用可否を確認するための検証コード。
 * <p>
 * javacでのコンパイル結果:
 * <ul>
 *   <li>8, 9行目: メンバ変数（フィールド）にはvarを使用できずコンパイルエラー</li>
 *   <li>13行目: カンマ区切りの複合宣言にはvarを使用できずコンパイルエラー</li>
 *   <li>それ以外（final var、拡張for文、ジェネリクスの型推論）は使用可能</li>
 * </ul>
 */
public class Main_t1_q37 {

    // メンバ変数にvarは使用できない
    var a = "Main";     // javac NG: フィールドの型にvarは使用不可
    var b = null;       // javac NG: フィールドの型にvarは使用不可（初期値nullでも型推論できないため二重にNG）

    /**
     * varの宣言パターンを一通り試すメソッド。
     *
     * @param a 未使用の引数
     */
    void test(int a){
        // varは複合宣言で使用できない
        var x1 = 1,x2 = 2;              // javac NG: カンマ区切りの複合宣言にはvar使用不可
        final var x3 = 3.14f;           // javac OK: finalを付けてもvarは使用可能（x3はfloatと推論される）
        var arr = new int[]{1,2,3};     // javac OK: arrはint[]と推論される
        for(var i: arr) System.out.print(i); // javac OK: 拡張for文でもvar使用可能（iはintと推論される）
        var list = new ArrayList<>();   // javac OK: ダイヤモンド演算子と組み合わせるとArrayList<Object>と推論される
        list.add(' ');                  // char ' ' はCharacterにオートボックスされて追加される
        list.add("LIST");               // Object型リストなのでStringも追加可能
    }
}
