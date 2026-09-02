package org.example;

/**
 * try-catch-finally の実行順序と、配列外アクセスによる
 * {@link ArrayIndexOutOfBoundsException}（RuntimeException のサブクラス）が
 * どのようにキャッチされるかを確認するためのサンプル。
 */
public class Main_t1_q47 {
    /**
     * コマンドライン引数なしで実行されるため、args は要素数 0 の配列。
     * その空配列を method(String...) に渡す。
     *
     * @param args コマンドライン引数（未指定なら長さ0の配列）
     */
    public static void main(String[] args){
        new Main_t1_q47().method(args);
    }

    /**
     * args（可変長引数、実際は長さ0の配列）を受け取り、3回ループする。
     * 各ループで次の順に処理される。
     * <ol>
     *   <li>try ブロック開始時に "A" を出力。</li>
     *   <li>{@code args[i]} へのアクセスで、args が空配列のため
     *       i の値に関わらず必ず {@link ArrayIndexOutOfBoundsException}
     *       がスローされる。これは {@link RuntimeException} のサブクラスなので
     *       catch(RuntimeException ex) で捕捉され、"B" を出力する。</li>
     *   <li>例外の発生有無に関わらず finally ブロックは必ず実行され、
     *       "C" を出力する。</li>
     * </ol>
     * これが3回（i=0,1,2）繰り返されるため、最終的な標準出力は
     * "ABCABCABC" となる。
     *
     * @param args 可変長引数（呼び出し元からは空配列が渡される）
     */
    void method(String... args){
        for(var i = 0; i< 3; i++){
            try{
                System.out.print("A");
                String s = args[i];
            }catch(RuntimeException ex){
                System.out.print("B");
            }finally {
                System.out.print("C");
            }
        }
    }
}
