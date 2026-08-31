package org.example;

public class Main_t1_q31 {
    public static void main(String[] args){
        double d = Double.parseDouble(args[0]);
        int i = Integer.parseInt(args[1]);
        // method(d, i) の実引数は (double, int)。
        // オーバーロード解決は以下の優先順位で候補を絞り、最初に候補が1つに
        // 決まった時点で確定する（優先度：暗黙の型変換 > ボクシング/アンボクシング > 可変長引数）。
        //
        // フェーズ1（暗黙の型変換のみ許容。ボクシング・可変長引数は不可）:
        //   ・method(double, double)：int→doubleはwidening（暗黙の型変換）で変換可 → 該当
        //   ・method(int, int)      ：double→intはnarrowingで不可 → 対象外
        //   ・method(Double, float) ：double→Doubleはボクシングが必要 → フェーズ1では対象外
        //   ・method(int...) / method(double...) ：可変長引数はフェーズ1では対象外
        // → フェーズ1の時点でmethod(double, double)のみが該当するため、
        //   フェーズ2（ボクシング/アンボクシング許容）やフェーズ3（可変長引数許容）は
        //   評価されずに確定する。
        //
        // 結果として method(double, double) が呼ばれ "A" が出力される。
        new Main_t1_q31().method(d,i);
    }
    public void method(double x, double y){
        System.out.println("A");
    }
    public void method(Double x, float y){
        System.out.println("B");
    }
    public void method(int... x){
        System.out.println("C");
    }
    public void method(double... d){
        System.out.println("D");
    }
    public void method(int x, int y){
        System.out.println("E");
    }
}
