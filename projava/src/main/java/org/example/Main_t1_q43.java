package org.example;

/**
 * フィールドの「隠蔽（hiding）」はメソッドのオーバーライドと異なり動的束縛（実行時ポリモーフィズム）
 * が働かないこと、および可変長引数オーバーロードの解決規則を確認するための検証コード。
 * <p>
 * 実行結果: {@code Bar:classBase:abstractBase:abstractBase:abstract}
 * </p>
 */
public class Main_t1_q43 {
    public static void main(String[] args){
        Bar bar = new Bar();          // 変数barの静的型はBar、実体もBarインスタンス
        Base base = new Foo();        // 変数baseの静的型はBase、実体はFooインスタンス（アップキャスト）
        Foo foo = (Foo)base;          // 変数fooの静的型はFoo、実体はFooインスタンス
        print(bar);                   // 引数が1つでBar型に完全一致するため print(Bar obj) が選ばれる（varargsより優先度が高い） → "Bar:class"
        print(bar, base, foo);        // 引数が3つでprint(Bar)には合わないため print(Base... obj) が選ばれる → 詳細は下記print(Base...)を参照
        print();                      // 引数なし。print(Bar obj)には合わないため print(Base... obj) が選ばれ、空配列で呼ばれる（何も出力されない）
    }

    /**
     * <p>
     * 可変長引数版のprintメソッド。{@code print(bar, base, foo)} 呼び出し時の詳細な処理は以下の通り。
     * </p>
     * <ol>
     *   <li>オーバーロード解決: 引数の個数が3つで {@code print(Bar obj)}（引数1つ）とはシグネチャが合わないため、
     *       この可変長引数版が選択される。渡された {@code bar, base, foo}（それぞれ実体はBar, Foo, Fooインスタンス）は
     *       自動的に {@code Base[]} 配列にまとめられて {@code obj} に渡される。</li>
     *   <li>拡張for文 {@code for(Base b:obj)} により、配列{@code obj}の各要素を
     *       <b>静的型がBaseの変数 {@code b}</b> として1つずつ取り出す。
     *       このとき実際に代入されるオブジェクトの実行時型（Bar, Foo, Foo）は変わらないが、
     *       ループ変数{@code b}自体の「宣言された型（コンパイル時の型）」はBaseになる。</li>
     *   <li>{@code b.type} でのフィールドアクセスがポイント。
     *       <b>フィールドはメソッドと違って実行時のポリモーフィズム（動的束縛）が働かず、
     *       「変数の静的型（コンパイル時に決まる型）」に基づいて解決される。</b>
     *       そのため、bの実体が実際はBarやFooのインスタンスであっても、
     *       {@code b.type} は常にBaseクラスで宣言されたフィールド（{@code type = "abstract"}）を参照する。</li>
     *   <li>結果として、bar・base・fooのいずれの要素についても {@code b.type} は
     *       {@code "abstract"} となり、3回とも {@code "Base:abstract"} が出力される
     *       （{@code Base:abstractBase:abstractBase:abstract"}）。
     *       もしtypeがフィールドではなくオーバーライド可能なメソッド（例: {@code getType()}）であれば、
     *       実行時型（Bar, Foo, Foo）に応じて異なる値が出力されていたはずである点が対比のポイント。</li>
     * </ol>
     *
     * @param obj 可変長引数として渡されたBase型（またはそのサブクラス）のオブジェクト列。内部的にBase[]配列として扱われる
     */
    static void print(Base... obj){
        for(Base b:obj){
            System.out.print("Base:" + b.type);   // b.typeは常にBase.type（="abstract"）を参照する（静的型による解決）
        }
    }

    /**
     * Bar型専用のprintメソッド。引数がBar型1つの呼び出しでは、varargs版より優先してこちらが選択される。
     *
     * @param obj Bar型のオブジェクト（静的型がBarなので obj.type はBar.typeを参照する）
     */
    static void print(Bar obj){
        System.out.print("Bar:" + obj.type);   // objの静的型はBarなので、Bar.type(="class")が出力される
    }
}

/** typeフィールドを持つ抽象クラス（フィールド隠蔽の検証における最上位の型）。 */
abstract class Base{ String type = "abstract";}
/** Baseを継承し、同名フィールドtypeで隠蔽（オーバーライドではない）する。 */
class Foo extends Base{ String type = "class";}
/** Fooを継承し、さらに同名フィールドtypeで隠蔽する。 */
class Bar extends Foo { String type = "class";}
