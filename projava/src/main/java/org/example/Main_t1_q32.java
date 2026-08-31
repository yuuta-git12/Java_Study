package org.example;

public class Main_t1_q32 {
    private double price;
    private Main_t1_q32(){
        // this(100.0) はコンストラクタの先頭でのみ呼び出し可能な「他コンストラクタ呼び出し」。
        // 同じクラスの Main_t1_q32(double price) を呼び出す（コンストラクタチェイン）。
        this(100.0);
        // this(100.0) の呼び出しが完了してからここに戻る。
        // ここでの price はローカル変数が無いためフィールド this.price を指すが、
        // 下のコンストラクタ内でフィールドへの代入が行われていない（後述）ため
        // デフォルト値 0.0 のまま。よって " 1:0.0" が出力される。
        System.out.print(" 1:" + price);
    }
    Main_t1_q32(double price){
        // 仮引数 price がフィールド price を隠蔽（シャドーイング）している。
        // 右辺・左辺どちらも仮引数 price を指すため、これは
        // 「仮引数を自分自身に代入」しているだけで、フィールド this.price には
        // 何も代入されていない（本来は this.price = price; とすべき箇所）。
        // フィールド this.price はこの後もデフォルト値 0.0 のまま変わらない。
        price = price;
        // ここでの price は仮引数（100.0）を指すので " 2:100.0" が出力される。
        System.out.print(" 2:" + price);
    }
    public void print(){
        // this.price は明示的にフィールドを指す。
        // 上記のシャドーイングの影響でフィールドは一度も更新されていないため 0.0。
        // よって " 3:0.0" が出力される。
        System.out.println(" 3:" + this.price);
    }
    public static void main(String[] args){
        // 実行結果: " 2:100.0 1:0.0 3:0.0"
        new Main_t1_q32().print();
    }
}
