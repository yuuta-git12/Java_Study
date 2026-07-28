# Java継承

## 継承
- 一般的なクラスを元に、より具体的な新しいクラスを定義すること
### スーパークラス（親クラス、基底クラス）
- 元になるクラス
### サブクラス（子クラス、派生クラス）
- 新たに定義されるクラス
- サブクラスにはスーパークラスのメンバ（変数、メソッド）が引き継がれる
- 差分プログラミング：サブクラスに差分のメンバを定義することで機能拡張すること
- *複数のスーパークラスを継承するサブクラスは定義できない*

- サブクラスの定義例
```java
// 定義構文
[修飾子] class サブクラス名 extends スーパークラス名 {}

class Item {
    private int id = 100;
    public void printItem(){}
}

class Book extends Item{}
```


#### 修飾子による継承の可否
- `protected`,`public`のメンバは継承される
- `private`メンバはサブクラスに継承されない
- パッケージが異なる場合、パッケージプライベートのメンバも継承されない
- コンストラクタも継承できない

## メソッドのオーバーライド
### オーバーライドとsuper
- メソッドのオーバーライド：サブクラス内からアクセス可能なスーパークラスのメソッドを再定義すること
- オーバーライドが行える条件
  - メソッドのシグネチャ（メソッド名、引数リスト）が同一
  - 戻り値の型が同一、またはサブクラスの型（共変戻り値型）
    - 例：スーパークラスが `Animal` を返す場合、サブクラスで `Dog`（`Animal` のサブクラス）を返すようオーバーライドできる
  - アクセス修飾子が同一、またはより公開範囲の広い修飾（`private`➡️`public`のような感じ）
- サブクラスのメソッドからスーパークラスのメンバにアクセスする場合は`super.アクセスしたいメンバ`とする
```java
class Animal {
    String name = "Animal";
    public void speak() {
        System.out.println("...");
    }
}

class Dog extends Animal {
    String name = "Dog";

    @Override
    public void speak() {
        super.speak();               // スーパークラスのメソッドを呼び出す
        System.out.println("Woof!");
    }

    public void printNames() {
        System.out.println(name);        // "Dog"（サブクラスのフィールド）
        System.out.println(super.name);  // "Animal"（スーパークラスのフィールド）
    }
}
```
### @Overrideアノテーション
- `アノテーション`：@から始まる行、クラスやメソッドに注釈を指定するための仕組み
- `@Override`はオーバーライドしたメソッド定義の前に付与する。コンパイラがオーバーライドのルールを満たしているかチェックしてくれる
```java
class Animal {
    public void speak() {
        System.out.println("...");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {  // コンパイラがオーバーライドの条件を検証する
        System.out.println("Woof!");
    }

    // @Override
    // public void speek() {}  // コンパイルエラー：スーパークラスに speek() は存在しない
}
```
### オーバーライドにおけるメンバ変数とstaticメソッドの再定義
- メンバ変数と`static`メソッドは**オーバーライドではなく、隠蔽（ハイディング）**になる
- インスタンスメソッドのオーバーライドは実行時の型で動的に決まるが、隠蔽は参照型（コンパイル時の型）で静的に決まる
```java
class Animal {
    static String type = "Animal";
    String name = "animal";

    static void staticMethod()   { System.out.println("Animal static"); }
    void instanceMethod()        { System.out.println("Animal instance"); }
}

class Dog extends Animal {
    static String type = "Dog";   // 隠蔽（ハイディング）
    String name = "dog";          // 隠蔽（ハイディング）

    static void staticMethod()    { System.out.println("Dog static"); }   // 隠蔽
    @Override
    void instanceMethod()         { System.out.println("Dog instance"); } // オーバーライド
}

Animal a = new Dog();
System.out.println(a.type);  // "Animal"（コンパイル時の型＝Animal で決まる）
System.out.println(a.name);  // "animal"（コンパイル時の型＝Animal で決まる）
a.staticMethod();             // "Animal static"（コンパイル時の型で決まる）
a.instanceMethod();           // "Dog instance"（実行時の型＝Dog で決まる）
```
### finalメソッド・finalクラス
- メソッド、クラスの宣言時に`final修飾子`をつけて定義することが可能
- `final修飾子`をつけられたクラスは**継承ができない**
```java
// 構文 ➡️ [修飾子] final クラス名{}
public final class String{}
```
- `final修飾子`をつけられたメソッドは**オーバーライドできない**
```java
// 構文 ➡️ [修飾子] final 戻り値の型　メソッド名(引数リスト){}
public final void methodA(){}
```
## 継承関係におけるコンストラクタの扱い
- 継承関係にあるサブクラスのオブジェクトを生成する場合、**スーパークラスのコンストラクタが優先して実行される**
- ただし、サブクラスにスーパークラスのコンストラクタは引き継がれない
### サブクラスのオブジェクト生成の流れ
1. スーパークラスのオブジェクトを初期化（スーパークラスのコンストラクタが実行）
2. サブクラスのオブジェクトを初期化（サブクラスのコンストラクタが実行）
- 明示的にスーパークラスのコンストラクタ`super()`をサブクラスのコンストラクタに記載しない場合は暗黙的に`super()`が実行される
- スーパークラスのコンストラクタに引数がある場合は、サブクラスで明示的に記載する必要がある
  - 引数ありのコンストラクタのみを定義するとデフォルトコンストラクタ（引数なし）が自動生成されなくなり、サブクラスからの暗黙的な`super()`呼び出しがコンパイルエラーになる。スーパークラスに引数なしのコンストラクタも用意するか、サブクラスで明示的に`super(引数)`を呼び出す必要がある
- `super()`と`this()`を同時に呼び出すことはできない
```java
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
        System.out.println("Animalコンストラクタ実行: " + name);
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name);   // スーパークラスのコンストラクタを明示的に呼び出す（コンストラクタの先頭行に書く）
        this.breed = breed;
        System.out.println("Dogコンストラクタ実行: " + breed);
    }
}

// Dog d = new Dog("ポチ", "柴犬"); と実行すると
// → "Animalコンストラクタ実行: ポチ"
// → "Dogコンストラクタ実行: 柴犬"
// の順に出力される
```

## java.lang.Objectクラスとレコードクラス
### Objectクラス
- Javaのクラス階層のルート
- すべてのクラスのスーパークラス
- 配列含めたすべての参照型は`Objectクラス`のメソッドを継承している
  - `boolean equals()`：オブジェクトの等価性を比較するメソッド。デフォルトでは参照（同一インスタンスかどうか）を比較する
  - `int hashCode()`：オブジェクトのハッシュコード（整数値）を返すメソッド。`equals()`でtrueとなる2つのオブジェクトは同じ`hashCode()`を返す必要がある
  - `String toString()`：オブジェクトの文字列表現を返すメソッド。デフォルトでは「クラス名@ハッシュコードの16進数表現」を返す
### レコードクラス
- イミュータブル（変更不可）なデータの保持を目的で使用
- フィールド（コンポーネント）、コンストラクタ、アクセサメソッドなどを簡潔な構文で自動生成できるクラス
```java
// 構文
[修飾子]　record レコード名([コンポーネント]){}
// コンポーネントとしてidとnameを持つレコード
public record Item(int id, String name){}
```
- コンパイルするとレコード名と同名のfinalクラスができ、以下が用意される
  - コンポーネントごとに２つのメンバ
    - `private final`な変数（定数、コンポーネントフィールド）
    - `public`なアクセス用メソッド（コンポーネントフィールドの値を取得）
  - 標準コンストラクタ
  - `hashCode()`と`equals()`メソッド
  - `toString()`メソッド
  - `final`クラスだから編集はできない
  - イミュータブルだから値の変更ができないsetterもない
    - オブジェクトを生成した際の初期値がずっと同じままである
- 疑問点
  - 値を処理するメソッドもレコードクラスに記述するという認識で良い？
    - 回答：その認識でよい。コンポーネントの値を使った計算やフォーマット処理などのメソッドは通常のクラス同様にレコードクラス内に定義できる。ただしレコードは状態を持たない（イミュータブル）ため、フィールドの値を書き換えるようなメソッドは定義できない
  - レコードクラスを利用する状況がよく分からない。通常のクラスと同様に使う認識でよい
    - 回答：通常のクラスと全く同じ用途ではない。レコードクラスは「値の集まりを保持するだけのデータ」を表す場合（DTO、APIのレスポンス、集計結果、複数の戻り値をまとめる場合など）に向いている。逆に、内部状態が変化する（setterで値を更新する、可変なフィールドを持つ）オブジェクトや、継承関係を持たせたいクラスにはレコードクラスは向かない（レコードは`final`のため継承できず、他のクラスも継承できない）
  - メンバ変数が後から増える場合、レコードクラスの方が拡張性があるなら最初から全てレコードクラスで定義すべき？
    - 回答：拡張性の観点で最初から全てレコードクラスにすべきとは言えない。レコードクラスはコンポーネント（フィールド）が変わるとコンストラクタ・アクセサ・`equals()`/`hashCode()`/`toString()`が全て変わり、利用側のコード（`new Item(...)`の呼び出し箇所など）にも影響が及ぶため、頻繁にフィールドが増減するクラスには不向き。「不変なデータの入れ物」であるかどうかで通常のクラスかレコードクラスかを選ぶべきで、拡張性だけを理由に選択するものではない
#### コンパクトコンストラクタ
- 標準コンストラクタとコンパクトコンストラクタはどちらか１つしか使用できない
- 引数リストの括弧`()`を省略して記述するコンストラクタ。主にコンポーネントの値の検証（バリデーション）や正規化に使用する
```java
// 構文
[修飾子] レコード名 { 検証処理など }

public record Item(int id, String name) {
    // コンパクトコンストラクタ
    public Item {
        if (id < 0) {
            throw new IllegalArgumentException("idは0以上である必要があります");
        }
    }
}
```
#### レコードクラスのメンバ
- コンポーネント以外のフィールド（インスタンスフィールド）は追加できない
- static変数やstaticメソッド、独自のインスタンスメソッドは追加で定義できる
## 抽象クラス