package org.example;

/**
 * try-catch-finallyにおけるcatch節の記述順序を確認するための検証コード。
 * <p>
 * javacでのコンパイル結果: エラーとなる。
 * 先に記述した {@code catch(Exception e)} が全ての例外（NullPointerException,
 * IndexOutOfBoundsExceptionを含む）を捕捉してしまうため、後続の
 * {@code catch(NullPointerException | IndexOutOfBoundsException e)} は
 * 到達不能となり「すでに捕捉されています」というコンパイルエラーになる。
 * マルチキャッチ・サブクラスのcatchは、より汎用的な親クラスのcatchより前に書く必要がある。
 * </p>
 */
public class Main_t1_39 {
    public static void main(String[] args){
        try{
            args[0].charAt(0);              // argsが空配列の場合 ArrayIndexOutOfBoundsException が発生する想定の処理
            System.out.println("A");
        }catch (Exception e){               // javac NG要因: すべての例外（IndexOutOfBoundsException等）をここで捕捉してしまう
            System.out.println("B");
        }catch(NullPointerException | IndexOutOfBoundsException e){ // javac NG: 直前のcatch(Exception)で既に捕捉済みのため到達不能でコンパイルエラー
            System.out.println("C");
        }finally{
            System.out.println("E");        // 例外の有無にかかわらず必ず実行される
        }
    }
}
