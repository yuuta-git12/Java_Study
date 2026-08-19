# Javaのクラスとオブジェクト
## クラスとは
- クラスとは、オブジェクトの設計図のようなもの。
- クラスを定義することで、オブジェクトの属性（フィールド）や動作（メソッド）をまとめて表現することができる。
- クラスは、同じ種類のオブジェクトを作成するためのテンプレートとして機能する。
## クラスのメンバ
- クラスのメンバには、フィールド（属性）とメソッド（動作）がある。
### メンバ変数(フィールド)
- メンバ変数は、クラス内で定義される変数で、オブジェクトの状態を表す。
- 例: 商品クラスにおける「価格」や「名前」など。
```java
[修飾子] データ型 変数名;

String name; // 商品名

✖️ var name; // エラー: varはローカル変数でのみ使用可能
```
### メンバメソッド(メソッド)
- メンバメソッドは、クラス内で定義される関数で、オブジェクトの動作を表す。
- 例: 商品クラスにおける「在庫数を更新する」メソッドなど。
```java
[修飾子] 戻り値の型 メソッド名(引数リスト) {
    // メソッドの処理
}
public void updateStock(int newStock) { // 戻り値がvoidの場合は値を返さない
    // 在庫数を更新する処理
}

public int calculatePrice(int quantity) { // 戻り値がintの場合は整数値を返す
    // 価格を計算する処理
    return price * quantity;
}
```
- `return`より後に処理が書かれている場合、コンパイルエラーになるので注意すること。

### setterとgetter
- setterは、メンバ変数の値を設定するためのメソッド
- getterは、メンバ変数の値を取得するためのメソッド
- 例: 商品クラスにおける「価格」を設定・取得するメソッド
```java

private int price; // メンバ変数の宣言

public void setPrice(int price) {
    this.price = price; // thisは現在のオブジェクトを指す
}
public int getPrice() {
    return this.price; // thisは現在のオブジェクトを指す
}
```

### 変数のスコープ,thisキーワード
- 変数のスコープとは、変数が有効な範囲のことを指す。
- クラス内で定義された変数は、クラスのスコープ内で有効であり、クラスの外部からはアクセスできない。
- メソッド内で定義された変数は、メソッドのスコープ内で有効であり、メソッドの外部からはアクセスできない。
- `this`キーワードは、現在のオブジェクトを指す参照であり、メンバ変数とローカル変数の名前が同じ場合に、メンバ変数を明示的に参照するために使用される。
- `this`キーワードは以下の場合、省略可能である
  - メンバ変数とローカル変数の名前が異なる場合
```java
public class Product {
    private String name; // メンバ変数の宣言

    public void setName(String name) {
        this.name = name; // thisは現在のオブジェクトを指す
    }
    public String getName() {
        return this.name; // thisは現在のオブジェクトを指す
    }
}
```

## オブジェクトの生成と使用
- クラスを定義した後、オブジェクトを生成して使用することができる。
- オブジェクトは参照型であり、変数にはオブジェクトの参照が格納される。
- オブジェクトの生成には、`new`キーワードを使用する。
```java
Product product = new Product(); // Productクラスのオブジェクトを生成
product.setName("Laptop"); // オブジェクトのメソッドを使用して名前を設定
String productName = product.getName(); // オブジェクトのメソッドを使用して名前を取得
System.out.println(productName); // 出力: Laptop
```

## アクセス修飾子
- アクセス修飾子は、クラスやメンバのアクセス範囲を制御するためのキーワードである。
- 主なアクセス修飾子には、`public`、`private`、`protected`がある。
  - 可視性：クラス、クラスに定義したメンバ変数、メソッドなどのメンバがアクセスされる範囲
  - アクセス制御：可視性を制御することで、クラスの内部実装を隠蔽し、外部からの不正なアクセスを防ぐこと

|修飾子|可視性|説明|
|:---|:---|:---|
|public|どこからでもアクセス可能|クラス、メンバ変数、メソッドなどが、どのクラスからでもアクセスできる。|
|protected|同じパッケージ内またはサブクラスからアクセス可能|メンバ変数、メソッドなどが、同じパッケージ内のクラスや、サブクラスからアクセスできる。（トップレベルのクラスには使用不可）|
|なし（package-private）|同じパッケージ内のみアクセス可能|修飾子を省略した場合。同じパッケージ内のクラスからのみアクセスできる。`public`ではないので注意。|
|private|クラス内のみアクセス可能|クラス、メンバ変数、メソッドなどが、同じクラス内からのみアクセスできる。|

- トップレベルクラス（ファイル直下に定義されるクラス）に使用できるアクセス修飾子は`public`または`default`（指定なし＝パッケージプライベート）のみ。`private`や`protected`はトップレベルクラスには使用できない。

### カプセル化
- カプセル化とは、クラスの内部実装を隠蔽し、外部からのアクセスを制御することで、オブジェクトの状態を保護すること。
- オブジェクトのデータへのアクセスを制御することで、データの整合性を保つことができる。
  - オブジェクトのデータへのアクセスにsetter/getterメソッドを使用することで、データの整合性を保つことができる。
  - メンバ変数を`private`にすることで、外部から直接アクセスできなくし、メソッドを通じてのみアクセスできるようにする。

## メソッドのオーバーロード
- メソッドのオーバーロードとは、同じ名前のメソッドを複数定義することができる機能である。
- メソッドのオーバーロードは、引数の型や数が異なる場合に有効であり、同じ名前のメソッドを使い分けることができる。
- メソッドのオーバーロードは、コードの可読性を向上させ、同じ処理を行うメソッドをまとめることができる。
```java
public class Calculator {
    // 2つの整数を加算するメソッド
    public int add(int a, int b) {
        return a + b;
    }
    // 2つの小数を加算するメソッド
    public double add(double a, double b) {
        return a + b;
    }
    // 3つの整数を加算するメソッド
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```
### シグネチャ
- メソッド名と引数の型・数を組み合わせたものをシグネチャと呼ぶ。
### 可変長引数
- 可変長引数とは、メソッドの引数の数が可変であることを示す機能である。
- 可変長引数は、引数の型の後に`...`を付けることで定義することができる。
- 可変長引数は、引数の数が不定である場合に便利であり、配列として扱うことができる。
```java
public class Calculator {
    // 可変長引数を使用した加算メソッド
    public int add(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
```
### メソッド呼び出しの優先度
- メソッド呼び出しの優先度は、引数の型や数が一致するメソッドが優先される。
- 引数の型や数が一致するメソッドが複数存在する場合は、最も具体的な型のメソッドが優先される。
- 例: `add(int a, int b)`と`add(double a, double b)`が存在する場合、`add(1, 2)`は`add(int a, int b)`が呼び出され、`add(1.0, 2.0)`は`add(double a, double b)`が呼び出される。
- 優先順位
  1. 引数の型と数が完全に一致するメソッド
  2. 引数の型が一致するメソッド（自動型変換が可能な場合）
  3. 可変長引数を使用したメソッド

## オブジェクトの初期化
- メンバ変数に最初に値を設定することをオブジェクトの初期化という
- メンバ変数の初期化方法
  - デフォルトの初期値を利用
  - クラス定義の際にメンバ変数に値を設定
  - コンストラクタを使用
### デフォルトの初期値を利用
- メンバ変数は、データ型に応じたデフォルトの初期値が設定される
- デフォルトの初期値は以下の通り 

| データ型            | 値     |
|:--------------------|:-------|
| byte,short,int,long | 0      |
| float,double        | 0.0    |
| boolean             | false  |
| char                | \u0000 |
| String              | null   |

```java
public class Product {
    private String name; // デフォルト値はnull
    private int price; // デフォルト値は0

    public void printInfo() {
        System.out.println("商品名: " + name);
        System.out.println("価格: " + price);
    }
}
```
### コンストラクタを使用
- コンストラクタとは、オブジェクトが生成される際に呼び出される特殊なメソッドで、オブジェクトの初期化を行う。
- **コンストラクタは、クラス名と同じ名前を持ち、戻り値の型を持たない。**(他の言語と違い__constructor__は戻り値の型を持たない)
- コンストラクタは、オブジェクト生成時に自動的に呼び出され、メンバ変数の初期化を行う。
- コンストラクタは、引数を持つことができ、オーバーロードすることも可能である。
```java
public class Product {
    private String name;
    private int price;

    // コンストラクタ
    // 戻り値の型を持たない。戻り値の型がある場合はただのメソッドになる 
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void printInfo() {
        System.out.println("商品名: " + name);
        System.out.println("価格: " + price);
    }
}
```
#### デフォルトコンストラクタ
- **コンストラクタを1つも定義しない場合**、Javaコンパイラが自動的に引数なし・処理なしのコンストラクタを生成する。これをデフォルトコンストラクタと呼ぶ。
- デフォルトコンストラクタはコンパイラが自動生成するもので、コード上には現れない（書かなくてよい）。
- メンバ変数はデータ型に応じたデフォルト値（`0`、`null`、`false`など）で初期化される。
- **引数ありのコンストラクタを1つでも定義すると、デフォルトコンストラクタは自動生成されなくなる**ため、引数なしでも生成できるようにしたい場合は明示的に定義する必要がある。

```java
// コンストラクタを何も定義しないケース
// → コンパイラが自動的に「public Product() {}」を生成してくれる
public class Product {
    private String name; // デフォルト値: null
    private int price;   // デフォルト値: 0

    public void printInfo() {
        System.out.println("商品名: " + name); // null
        System.out.println("価格: " + price);  // 0
    }
}

// 引数ありのコンストラクタを定義するケース
// → デフォルトコンストラクタは自動生成されない
public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    // ↓ これがないと new Product() はコンパイルエラーになる
    // public Product() { this.name = "未設定"; this.price = 0; }
}
```
### コンストラクタのオーバーロード
- コンストラクタのオーバーロードとは、同じ名前のコンストラクタを複数定義することができる機能である。
- コンストラクタのオーバーロードは、引数の型や数が異なる場合に有効であり、同じ名前のコンストラクタを使い分けることができる。
- コンストラクタのオーバーロードは、コードの可読性を向上させ、同じ処理を行うコンストラクタをまとめることができる。
```java
public class Product {
    private String name;
    private int price;
    // 引数なしのコンストラクタ
    public Product() {
        this.name = "未設定";
        this.price = 0;
    }
    // 引数ありのコンストラクタ
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public void printInfo() {
        System.out.println("商品名: " + name);
        System.out.println("価格: " + price);
    }
}
public class Main {
    public static void main(String[] args) {
        Product product1 = new Product(); // 引数なしのコンストラクタを使用
        product1.printInfo(); // 出力: 商品名: 未設定
        Product product2 = new Product("Laptop", 1000); // 引数ありのコンストラクタを使用
        product2.printInfo(); // 出力: 商品名: Laptop
    }
}
```
## static変数・staticメソッド
### static変数
- static変数は、クラスに属する変数であり、オブジェクトに属する変数ではない。
- static変数は、クラスがロードされる際にメモリに確保され、すべてのオブジェクトで共有される。
- static変数は、クラス名を使ってアクセスすることができる。
```java
public class Product {
    private String name;
    private int price;
    private static int productCount = 0; // static変数の宣言

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        productCount++; // オブジェクトが生成されるたびにカウントを増やす
    }

    public static int getProductCount() {
        return productCount;
    }

    public void printInfo() {
        System.out.println("商品名: " + name);
        System.out.println("価格: " + price);
    }
}
```
### staticメソッド
- staticメソッドは、クラスに属するメソッドであり、オブジェクトに属するメソッドではない。
- staticメソッドは、クラスがロードされる際にメモリに確保され、すべてのオブジェクトで共有される。
- staticメソッドは、クラス名を使ってアクセスすることができる。
- staticメソッドは、static変数や他のstaticメソッドにアクセスすることができるが、非staticメソッドや非static変数にはアクセスできない。
```java
public class Product {
    private String name;
    private int price;
    private static int productCount = 0; // static変数の宣言
    
    static public int getProductCount() {
        // staticメソッドからstatic変数にアクセスすることができる
        return productCount; // static変数にアクセス
        // return this.name; // エラー: staticメソッドから非static変数にアクセスできない
    }
    public void printInfo() {
        System.out.println("商品名: " + name);
        System.out.println("価格: " + price);
        // インスタンスメソッドからstatic変数へアクセスすることは可能
        System.out.println("回数: " + productCount);
    }
}
```
## オブジェクトのライフサイクル
- オブジェクトが生成されてから破棄されるまでの期間のこと
- オブジェクトのライフサイクルは以下の3つのフェーズからなる
  - **生成**: `new`キーワードによりオブジェクトが生成され、メモリに確保される
  - **使用**: オブジェクトのメソッドやフィールドを通じて処理が行われる
  - **破棄**: オブジェクトへの参照がなくなった場合、ガベージコレクタによってメモリが解放される
### ガベージコレクタ
- JVMにある生成されたオブジェクトを管理する機能
- 使用されていないオブジェクトの検出・破棄を行う（メモリ領域の解放を行ってくれる）
- オブジェクトの参照を切るにはオブジェクトに`null`を代入する、または別のオブジェクトを代入する
- ガベージコレクションのタイミングはJVMが自動的に判断するため、プログラマが明示的に制御することはできない
- `System.gc()`を呼び出すことでガベージコレクションを促すことができるが、実行のタイミングはJVMに依存する


## オブジェクト指向について
- オブジェクト指向とは、プログラムをオブジェクトの集合として捉え、オブジェクト同士の関係や振る舞いを中心に設計する考え方。
- 物理的に存在する「商品」や概念的な「ユーザー」など、現実世界のものをプログラム上で表現するために使われる。


## 理解不足の内容
- `final`修飾子がついている場合は**初期化が必須**
  - `final`フィールドは宣言時またはコンストラクタ内で必ず値を設定しなければコンパイルエラーになる
  - `final`メソッドはサブクラスでオーバーライドできない
  - `final`クラスはサブクラスを持てない（継承不可）
  ```java
  public class Product {
      private final String name; // finalフィールド: 必ず初期化が必要
      public Product(String name) {
          this.name = name; // コンストラクタ内で初期化
      }
      // this.name = "other"; // コンストラクタ外での再代入はコンパイルエラー
  }
  ```
- メソッド宣言の場合`[修飾子]`はつけなくても良い、つけない場合は**パッケージプライベート（package-private）扱い**
  - `public`ではなく同じパッケージ内からのみアクセス可能になるため注意
  ```java
  void someMethod() { } // package-private（同パッケージ内のみアクセス可）
  public void someMethod() { } // publicとは意味が異なる
  ```
- メソッド、コンストラクタ内で`this()`で別のコンストラクタを呼び出す場合、ブロックの一番最初に記述する
  ```java
  public Product() {
      this("未設定", 0); // 必ず最初の行に書く。2行目以降はコンパイルエラー
  }
  public Product(String name, int price) {
      this.name = name;
      this.price = price;
  }
  ```
- クラス定義内では`this.変数名`と記述しなくても良い、`this`なしで`変数名`で記述でも良い
  - ただし、メソッドの引数名やローカル変数名とメンバ変数名が同じ場合は`this.変数名`で明示する必要がある
- メソッドのオーバーロードのルール
  - 同じメソッド名かつ引数リストが異なる→OK
  - 同じメソッド名かつ引数リストが同じ→コンパイルエラー（引数名が同じでも同じ型の場合エラーとなる）
  - 戻り値の型だけが異なる場合もコンパイルエラー（引数が同じなら区別できないため）
- コンストラクタのルール
  - クラスと同じ名前のメソッド
  - `void`,`int`等の返り値の型は記述しない
  - コンストラクタを一切書かない場合、コンパイラがデフォルトコンストラクタを自動生成する
  - 引数ありコンストラクタを1つでも書くと、デフォルトコンストラクタは自動生成されなくなる
- `public static main()`内では`this.変数名`の記述はできない
  - `static`メソッドはオブジェクトに紐づかないため、`this`（現在のオブジェクトへの参照）が存在しない

## クラス宣言に付与できる修飾子
- トップレベルクラス（1ファイルにつき1つ書けるような通常のクラス）に付与できる修飾子
  - `public` / 省略（パッケージプライベート）
  - `final`
  - `abstract`
  - `sealed`
  - `non-sealed`
  - `strictfp`（Java17以降非推奨）
  - `private`と`static`は使用不可（コンパイルエラーになる）
    ```java
    private class Foo { }   // NG: modifier private not allowed here
    static class Bar { }    // NG: illegal combination of modifiers
    ```
- ネストしたクラス（他のクラスの内部で定義するメンバークラス）に付与できる修飾子
  - 上記に加えて`private`・`protected`・`static`も使用可能
    ```java
    class Outer {
        public static class A { }
        private class B { }
        protected class C { }
        static class D { }
    }
    ```
- 組み合わせ上の注意
  - `final`と`abstract`は同時に付与不可（矛盾するため）
  - `sealed`は`permits`句、または同一ファイル内の直接サブクラスによる暗黙的な許可リストが必要
  - `non-sealed`は`sealed`クラスを継承するサブクラスに付与し、そのクラス以降は継承を自由に開放する

## コンストラクタの注意事項
- サブクラスのコンストラクタで明示的に`super(...)`や`this(...)`の呼び出しを書かなかった場合
  - コンパイラが自動的に**引数なしのsuper()**をコンストラクタの先頭に挿入する

```java
class Animal {
  Animal() {
    System.out.println("Animal constructor");
  }
}

class Dog extends Animal {
  Dog() {
    // ここに暗黙的に super(); が挿入される
    System.out.println("Dog constructor");
  }
}
```
```
実行結果:
Animal constructor
Dog constructor
```
### 注意点
- 1. 親クラスに引数なしコンストラクタが無いとエラーになる
```java
class Animal {
  Animal(String name) {  // 引数ありのみ定義
    System.out.println("Animal: " + name);
  }
}

class Dog extends Animal {
  Dog() {
    // 暗黙的に super(); を呼ぼうとするが、
    // Animal() が存在しないためコンパイルエラー
  }
}
```
このエラーを避けるには、明示的に`super(name);`のように対応する引数付きコンストラクタを呼ぶ必要があります。

- 2. 明示的な`super(...)`や`this(...)`がある場合は、暗黙の挿入は行われない
```java
class Dog extends Animal {
  Dog() {
    super("Pochi");  // これが優先され、super() は挿入されない
  }
}
```
- 3. `super(...)`または `this(...)` は必ずコンストラクタの最初の文でなければならない
  - これも上記の仕組み(暗黙的に先頭に挿入される)と整合性が取れています。
- 4. Objectクラスまで連鎖する
  - すべてのクラスは最終的に`Object`を継承しているので、コンストラクタ呼び出しの連鎖は最終的に`Object()`まで遡ります。

#レビュー済み