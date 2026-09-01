package org.example;
import org.example.Main_t1_q41;

/**
 * privateフィールドの非継承、およびインスタンスフィールドへの
 * クラス名経由アクセスの可否を確認するための検証用サブクラス。
 * <p>
 * javacでのコンパイル結果: エラーとなる（8, 9行目）。
 * </p>
 * <ol>
 *   <li>{@code super.name}（8行目）:
 *       親クラスのnameは{@code private}のため、サブクラスから継承されず参照不可。
 *       「nameはMain_t1_q41でprivateアクセスされます」というコンパイルエラーになる。</li>
 *   <li>{@code Main_t1_q41B.name}（9行目）:
 *       nameはstaticでないインスタンスフィールドのため、クラス名経由（{@code ClassName.field}）
 *       でアクセスすることはできない。
 *       「staticでない変数nameをstaticコンテキストから参照することはできません」という
 *       コンパイルエラーになる（インスタンス経由 {@code this.name} や単に {@code name} と書けばアクセス可能）。</li>
 * </ol>
 */
public class Main_t1_q41B extends Main_t1_q41{
    private String name = "Sub";    // 親クラスのprivate name(="Super")とは無関係の別フィールド（オーバーライドではなくフィールド隠蔽にもならない、単なる別物）
    public void display(){
        super.display();                                  // 親クラスのdisplay()を呼び出し、"100:Super"を出力
        System.out.print(no + ":");                        // protectedフィールドnoは継承されているため参照可能。"100:"を出力
        System.out.print(super.name + ":");                // javac NG: 親クラスのnameはprivateのため、サブクラスから直接参照できずコンパイルエラー
        System.out.print(Main_t1_q41B.name + ":");          // javac NG: nameは非staticフィールドのため、クラス名経由(ClassName.field)でのアクセスは不可でコンパイルエラー
    }
    public static void main(String[] args){
        new Main_t1_q41B().display();
    }
}
