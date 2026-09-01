package org.example;

/**
 * コンストラクタチェーン（{@code this(...)} / {@code super(...)}）による
 * コンストラクタ呼び出し順序を確認するための検証コード。
 * <p>
 * 実行結果（{@code new C()} 呼び出し時）: {@code A1 → B → C2 → C} の順に出力される。
 * </p>
 * <h2>呼び出しの流れ</h2>
 * <ol>
 *   <li>{@code new C()} により引数なしの {@link C#C() C()} が呼び出される。</li>
 *   <li>{@link C#C() C()} の先頭行 {@code this(2)} により、同一クラスの
 *       {@link C#C(int) C(int)} へ処理が委譲される（コンストラクタの最初の文が
 *       {@code this(...)} の場合、暗黙の {@code super()} は呼ばれない）。</li>
 *   <li>{@link C#C(int) C(int)} には明示的な {@code this(...)}/{@code super(...)} が無いため、
 *       コンパイラが暗黙的に親クラス {@link B} の引数なしコンストラクタ {@link B#B() B()} を
 *       先頭に挿入する（暗黙の {@code super()}）。</li>
 *   <li>{@link B#B() B()} の先頭行 {@code super(1)} により、さらに親クラス {@link A40} の
 *       {@link A40#A40(int) A40(int)} が呼び出され、{@code "A1"} が出力される。</li>
 *   <li>{@link A40#A40(int) A40(int)} の処理が終わると {@link B#B() B()} に戻り、
 *       {@code "B"} が出力される。</li>
 *   <li>{@link B#B() B()} の処理が終わると {@link C#C(int) C(int)} に戻り、
 *       {@code "C2"} が出力される。</li>
 *   <li>{@link C#C(int) C(int)} の処理が終わると、{@code this(2)} を呼び出した元の
 *       {@link C#C() C()} に戻り、続きの文である {@code "C"} が出力される。</li>
 * </ol>
 * つまりコンストラクタの実行順序は「委譲先（this/super）の初期化が完全に終わってから、
 * 呼び出し元コンストラクタの残りの処理が実行される」というスタック的な流れになる。
 */
public class Main_t1_q40 {
    public static void main(String[] args){
        new C(); // C() → this(2) → C(int) → 暗黙super() → B() → super(1) → A40(int) の順に処理が積み上がり、逆順に完了していく
    }
}

/** コンストラクタチェーンの最上位（親）クラス。 */
class A40 {
    A40(){System.out.println("A");}
    A40(int i){System.out.println("A" + i);}      // C(int)からB()経由でsuper(1)により呼ばれ、"A1"を出力
}
/** A40を継承する中間クラス。 */
class B extends A40{
    public B() {
        super(1);                                   // 親A40のA40(int)を明示的に呼び出す → "A1"が先に出力される
        System.out.println("B");                    // super(1)の処理完了後に"B"を出力
    }
}
/** Bを継承する、実際にnewされるクラス。 */
class C extends B{
    public C(){
        this(2);                                     // 同一クラスのC(int)へ委譲。this(...)がある場合、暗黙のsuper()は呼ばれない
        System.out.println("C");                     // this(2)（内部でのB(),A40(int)呼び出しを含む）が完了した後に"C"を出力（最後に出力される）
    }
    public C(int i){
        System.out.println("C" + i);                 // this/superの明示呼び出しが無いため、暗黙的にsuper()（B()）が先頭に挿入される。B()完了後に"C2"を出力
    }
}