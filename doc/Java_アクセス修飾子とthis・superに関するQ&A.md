# Javaのアクセス修飾子とthis/superに関するQ&A

## Q1. public, static, privateでのメンバ変数へのアクセスの可否を継承・実装を踏まえて教えて。合わせて、thisでメンバ変数へアクセスする場合との違いも説明

### 1. `private` — 同一クラス内のみ

```java
class Animal {
    private String name = "dog";

    void show() {
        System.out.println(name); // ○ 同じクラス内なのでOK
    }
}

class Dog extends Animal {
    void bark() {
        // System.out.println(name); // × コンパイルエラー：継承しても見えない
    }
}
```

- `private` フィールドは**サブクラスからは直接参照できません**。「継承はしている（メモリ上には存在する）」が「見えない・アクセスできない」という状態です。
- サブクラスから触りたい場合は、スーパークラス側に `protected` の getter/setter やメソッドを用意して間接的にアクセスさせるのが定石です。

### 2. `public` — どこからでもアクセス可能

```java
class Animal {
    public String name = "dog";
}

class Dog extends Animal {
    void bark() {
        System.out.println(name); // ○ 継承したフィールドとしてそのまま使える
    }
}
```

- パッケージ違い・継承関係の有無に関わらず、参照さえ持っていればどこからでもアクセス可能。
- 実務ではフィールドを `public` にするのは避け、`private` + アクセサメソッドにするのが一般的（カプセル化のため）。

### 3. `static` — クラスに属する（アクセス制御とは別軸）

`static` は public/private とは**別の軸**の修飾子で、「インスタンスではなくクラスに属する」ことを表す。組み合わせて使う。

```java
class Counter {
    public static int count = 0;   // クラスに1つだけ存在
    private static int secret = 0; // クラス内からのみ
}

class SubCounter extends Counter {
    void inc() {
        count++;        // ○ public static は継承先からもアクセス可
        // secret++;     // × private static はアクセス不可
    }
}
```

重要な注意点：
- **`static` フィールドはオーバーライドされず「隠蔽（hiding）」される**。サブクラスで同名の `static` フィールドを定義すると、親のものを上書きするのではなく、別物として隠す。
- `static` メンバはインスタンスを作らなくても `クラス名.フィールド名` でアクセスできる。

### 4. インターフェースの実装での扱い

```java
interface Constants {
    int MAX = 100; // 暗黙的に public static final
}

class Impl implements Constants {
    void show() {
        System.out.println(MAX); // ○ 継承的にアクセス可能
    }
}
```

- インターフェースのフィールドは**自動的に `public static final`** になる（インスタンス変数を持てない＝状態を持てない）。
- 実装クラスは「継承」ではなく「実装」だが、この定数はクラスに継承されたように使える。

---

### `this` でアクセスする場合との違い

`this` は「今動いているインスタンス自身への参照」。アクセス制御そのものを変えるものではなく、**どの変数を指すかを明示する**ための機能。

```java
class Animal {
    private String name;

    Animal(String name) {
        this.name = name; // 引数nameとフィールドnameが同名なので this で区別
    }

    void show() {
        System.out.println(name);      // ○
        System.out.println(this.name); // ○ 上と全く同じ意味
    }
}
```

| 観点 | 説明 |
|---|---|
| **アクセス制御への影響** | `this` を付けてもアクセス可否のルールは一切変わらない。`private` は `this.フィールド` と書いても他クラスからは呼べない |
| **主な用途** | ローカル変数／引数とフィールド名が同じ場合の名前解決（コンストラクタでよく使う） |
| **`static` メンバとの関係** | `this` は「このインスタンス」を指すため、**`static` フィールド/メソッド内では `this` は使えない**（インスタンスに紐付かないため）。逆にインスタンスメソッド内から `this.staticフィールド` と書くことは文法上可能だが、意味的にはクラス変数を指しており、可読性のため通常は `クラス名.フィールド` と書くべき（IDEでも警告が出ることが多い） |
| **継承との関係** | `this` はサブクラスのインスタンスを指す。ただし親クラスの `private` フィールドは、`this.フィールド名` と書いてもサブクラス側からは依然として見えない（private の可視範囲はあくまで「定義されたクラス自身」） |
| **`super` との対比** | フィールドが隠蔽されている場合、`this.field` は自分（サブクラス）のもの、`super.field` は親クラスのものを指す |

#### 隠蔽が絡む具体例

```java
class Parent {
    protected String name = "parent";
}

class Child extends Parent {
    protected String name = "child"; // 親のnameを隠蔽

    void show() {
        System.out.println(this.name);  // "child"（自分のフィールド）
        System.out.println(super.name); // "parent"（親のフィールド）
    }
}
```

- これは `private` ではなく `protected`/`public` なフィールドを**サブクラスで再定義した場合**の例。メソッドと違い、フィールドは動的ディスパッチ（ポリモーフィズム）が起きず、**宣言された型によって参照先が決まる**という点も重要。

#### まとめ

- `private`：継承されても子クラスから直接見えない（クラス内限定）
- `public`：継承関係やパッケージを問わずどこからでも見える
- `static`：インスタンスでなくクラスに属し、継承先ではオーバーライドではなく「隠蔽」される
- `this`：アクセス制御を変えるものではなく、「インスタンス自身」を明示する仕組み。`static` メンバには使えず、`private` の可視範囲も変えない

---

## Q2. thisの場合は該当のインスタンスの変数、superの場合はインスタンスの親クラスの変数ということ？

はい、その理解で正しい。より正確には以下の通り。

### 基本の理解

- **`this.変数`** → そのインスタンス自身が持っている（自分のクラスで定義した）フィールド
- **`super.変数`** → そのインスタンスが継承している、**親クラスで定義されたフィールド**

```java
class Parent {
    protected String name = "parent";
}

class Child extends Parent {
    protected String name = "child"; // 同名フィールドを再定義（隠蔽）

    void show() {
        System.out.println(this.name);  // "child"
        System.out.println(super.name); // "parent"
    }
}
```

### 補足3点

**① 実際には「1つの変数」を指しているわけではない**

`this.name` と `super.name` は同じ変数の「今の値」を見ているのではなく、**メモリ上に2つの別々のフィールドが存在していて**、それぞれを指している。

```java
Child c = new Child();
// c のメモリイメージ:
//   Parent部分の name = "parent"
//   Child部分の  name = "child"
```

`Child` のインスタンスは、親クラス分のフィールドと自分の分のフィールドを**両方**持っている（隠蔽であって上書きではないため）。`this.name` は Child 部分を、`super.name` は Parent 部分を指す、というイメージ。

**② 同名フィールドがなければ `this` でも親のフィールドが見える**

`Child` が独自に `name` を定義していなければ、`this.name` は普通に継承した親のフィールドを指す（隠蔽が起きていなければ `this.name` と `super.name` は同じものを指す）。

```java
class Parent {
    protected String name = "parent";
}

class Child extends Parent {
    void show() {
        System.out.println(this.name);  // "parent"（Childは再定義していない）
        System.out.println(super.name); // "parent"（同じもの）
    }
}
```

**③ メソッドとは挙動が違う（重要）**

フィールドは**ポリモーフィズム（動的ディスパッチ）が効かない**。参照している「変数の宣言型」によってどちらのフィールドが見えるかが決まる。

```java
Parent p = new Child();
System.out.println(p.name); // "parent" ← 実体はChildだが、型がParentなのでParent側のnameが見える
```

これはオーバーライドされたメソッドが常に実体（実際のインスタンス）のものが呼ばれるのと対照的。「フィールドは静的束縛、メソッドは動的束縛」という違いは、Javaのつまずきポイントの一つなのでセットで覚えておくと良い。

---

## Q3. 拡張for文のループ変数もフィールドの静的束縛の対象になる？（可変長引数との組み合わせ）

`Main_t1_q43.java` の `print(Base... obj)` を題材にした確認。

```java
abstract class Base{ String type = "abstract"; }
class Foo extends Base{ String type = "class"; }   // Baseのtypeを隠蔽
class Bar extends Foo { String type = "class"; }   // Fooのtypeを隠蔽

static void print(Base... obj){
    for(Base b : obj){
        System.out.print("Base:" + b.type);
    }
}

// 呼び出し側
Bar bar = new Bar();
Base base = new Foo();
Foo foo = (Foo)base;
print(bar, base, foo); // 実体はBar, Foo, Foo
```

**Q: `Base b` は結局 `Base` 型で宣言されているため、`obj` の中身が実際は `Bar` や `Foo` でも `b.type` は常に `Base` 型として `"abstract"` を参照するということ？**

はい、その理解で正しい。

### ポイント

- 可変長引数 `Base... obj` に渡された `bar, base, foo` は、実体が `Bar`・`Foo`・`Foo` であっても、配列としてまとめられる時点で要素の型は `Base[]` になる。
- 拡張for文 `for(Base b : obj)` のループ変数 `b` は **`Base` 型として宣言**される。ループのたびに中身（実体）は入れ替わるが、`b` という変数の**静的型（コンパイル時の型）は常にBase**のまま変わらない。
- フィールドアクセス `b.type` は前述の通り**動的束縛が効かず、変数の静的型で解決される**。そのため `b` の実体が `Bar` でも `Foo` でも関係なく、`b.type` は常に `Base` クラスで定義された `type`（`"abstract"`）を参照する。
- 結果として `print(bar, base, foo)` の3回のループはすべて `"Base:abstract"` を出力する（`Base:abstractBase:abstractBase:abstract`）。

### メソッドだったら結果は逆になる

もし `type` がフィールドではなく、オーバーライド可能なメソッド（例: `getType()`）だったなら、メソッドは動的束縛が働くため、`b` の宣言型が `Base` であっても、実際の実体（`Bar`・`Foo`・`Foo`）に応じたオーバーライド先のメソッドが呼ばれ、`"class"` などそれぞれ異なる値が返っていたはずである。

「変数の宣言型（静的型）で決まるフィールド」と「実体の型（動的型）で決まるメソッド」という対比は、拡張for文や可変長引数のように**変数の宣言箇所が呼び出し元から離れている場合**でも変わらず成立する、という点がこの例の学びどころ。