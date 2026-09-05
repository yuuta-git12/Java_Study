# Java 学習メモ:質疑応答まとめ

## 1. `ArrayList.add()` の戻り値の型による代入エラー

```java
ArrayList<String> list = new ArrayList<>();
var value1 = list.add(0, "Carol ");  // コンパイルエラー
```

- `add(E e)`(1引数)の戻り値は `boolean`
- `add(int index, E element)`(2引数)の戻り値は **`void`**
- `void` を返すメソッド呼び出しを変数に代入しようとするとコンパイルエラーになる
- 代入せず単なる式文として `list.add(0, "Carol ");` と書けば問題ない

| メソッド | 戻り値の型 | 変数に代入 |
|---|---|---|
| `add(E e)` | `boolean` | 可能 |
| `add(int index, E element)` | `void` | 不可(コンパイルエラー) |

### 例題
次のコードのうち、コンパイルエラーになる行はどれか。

```java
ArrayList<String> list = new ArrayList<>();
boolean r1 = list.add("A");        // (1)
var r2 = list.add(0, "B");         // (2)
list.add(1, "C");                  // (3)
```

**答え:(2)**。`add(int, E)` の戻り値は `void` のため、変数に代入できない。(1)と(3)は問題ない。

---

## 2. `list.add(0, "Carol ")` 実行時の中身の変化

- 指定インデックスに要素を**挿入**し、それ以降の要素は後ろへ1つずつシフトする
- リストのサイズが1増える

例:`[Duke , James , null]` → `add(0, "Carol ")` → `[Carol , Duke , James , null]`

### 例題
```java
ArrayList<String> list = new ArrayList<>(List.of("A", "B", "C"));
list.add(1, "X");
System.out.println(list);
```

**答え:`[A, X, B, C]`**。index1に"X"が挿入され、元のB, Cは1つずつ後ろにずれる。

---

## 3. フィールドとローカル変数(引数)の名前が同じ場合のシャドーイング

```java
public void setName(String name) {
    name = name;  // ローカル変数(引数)同士の代入。フィールドには影響しない
}
```

- Javaは変数名解決の際、**一番近いスコープ(ローカル変数・引数)を優先**する
- 同名のローカル変数が存在する場合、フィールドは自動的に選ばれない(文脈による賢い判断はしない)
- フィールドに代入したい場合は **`this.name = name;`** と明示する必要がある
- 同名のローカル変数がスコープ内に存在しない場合のみ、自動的にフィールドが参照される

### 例題
```java
public class Counter {
    int count = 0;
    public void setCount(int count) {
        count = count + 1;
    }
    public static void main(String[] args) {
        Counter c = new Counter();
        c.setCount(5);
        System.out.println(c.count);
    }
}
```

**答え:`0`**。`setCount`内の`count`はすべて引数(ローカル変数)を指しており、フィールドの`count`は書き換わらない。

---

## 4. `record` と `class` の違い

| 項目 | class | record |
|---|---|---|
| フィールドの可変性 | 自由 | 常に `final`(不変) |
| コンストラクタ・getter・equals等 | 自分で書く | 自動生成 |
| 継承 | 他クラスを継承できる | 他クラスを継承できない(暗黙に`java.lang.Record`) |
| 用途 | 汎用オブジェクト | データの入れ物(値オブジェクト) |
| フィールド追加 | 自由 | コンポーネント以外の追加不可 |

- コンパクトコンストラクタでバリデーションや値の加工が可能
- 独自メソッドの追加も可能

### 例題
次のうち、コンパイルエラーになるものはどれか。

```java
record Point(int x, int y) {
    private int z;               // (1)
    public Point {                // (2)
        if (x < 0) throw new IllegalArgumentException();
    }
    public int sum() { return x + y; }  // (3)
}
```

**答え:(1)**。recordはコンポーネント以外の**インスタンスフィールド**を持てない(staticフィールドなら可)。(2)のコンパクトコンストラクタや(3)の独自メソッド追加は問題ない。

---

## 5. `record` 内でのメソッドオーバーロードの重複エラー

```java
public int calc() { return price * amount; }
public long calc() { return (long)price * amount; }  // コンパイルエラー
```

- Javaのオーバーロードは**引数の型・数・順序(シグネチャ)**で区別される
- **戻り値の型だけが異なるメソッドは同じシグネチャとみなされ、多重定義エラーになる**
- 可変長引数(`double... rate`)と固定引数(`double discount`)は別シグネチャとして共存可能
- 引数1つの呼び出し時は、Javaは**可変長引数より固定引数を優先**して解決する

### 例題
次のクラスはコンパイルできるか。

```java
class Sample {
    void print(int a) { System.out.println("int: " + a); }
    void print(long a) { System.out.println("long: " + a); }
    int print(int a, int b) { return a + b; }   // 戻り値だけ異なるがOK?
}
```

**答え:コンパイルできる。** `print(int)`と`print(long)`は引数の型が違うので別シグネチャ。`print(int,int)`も引数の数が違うので別シグネチャ。戻り値の違いだけで区別しているメソッドが存在しないため問題ない。

---

## 6. `record` のデフォルト `toString()` の書式

```java
record Book(int id, String author, double price) { }
```

出力形式:
```
レコード名[コンポーネント名1=値1, コンポーネント名2=値2, ...]
```

例:`Book[id=100, author=WILLIAM, price=7.99]`

- カンマの後に**半角スペース**が入るのが特徴
- コンポーネント名はレコード宣言時のヘッダーの名前がそのまま使われる

### 出力を `Book[id=100, author=WILLIAM, price=7.99]` にする2パターン

**パターン1:コンパクトコンストラクタでフィールド自体を大文字化(toStringは自動生成のまま)**
```java
record Book(int id, String author, double price) {
    public Book {
        author = author.toUpperCase();
    }
    // toString()は書かない → デフォルトの書式がそのまま使える
}
```

**パターン2:フィールドは元のまま、toString内で大文字化**
```java
record Book(int id, String author, double price) {
    @Override
    public String toString() {
        return "Book[id=" + id + ", author=" + author.toUpperCase() + ", price=" + price + "]";
    }
}
```

| | パターン1 | パターン2 |
|---|---|---|
| `book.author()` の戻り値 | `"WILLIAM"` | `"william"`(元のまま) |
| `toString()` | 自動生成のまま | 自分で書いてデフォルト書式を再現 |
| equals/hashCodeへの影響 | 大文字化された値で比較 | 元の値のまま比較 |

### 例題
```java
record User(String name, int age) { }

public class Main {
    public static void main(String[] args) {
        User u = new User("Taro", 20);
        System.out.println(u);
    }
}
```

**答え:`User[name=Taro, age=20]`**。デフォルトの`toString()`は「レコード名[コンポーネント名=値, ...]」の形式になる。

---

## 7. `instanceof` パターンマッチングと `if` 文の独立性

```java
private static void validate(Object obj) {
    if (obj instanceof Integer o) {
        System.out.print("Integer ");
    }
    if (!(obj instanceof String s)) {
        throw new RuntimeException("Ex ");
    }
    System.out.print("String ");
}
```

- `if` 文には自動的な分岐終了(`break`のような機構)がない
- 最初の `if` ブロックに `return` がないため、`Integer ` 出力後も**そのまま次の `if` 文が評価される**
- `validate(17)`(Integer)の場合:`Integer ` 出力後、2つ目の `if` で `String` ではないため例外がスローされる
- `validate("Java")`(String)の場合:`String ` が出力され正常終了

処理を途中で止めたい場合は明示的に `return` を書く必要がある。

### 例題
```java
static void check(Object obj) {
    if (obj instanceof String s) {
        System.out.print("A");
    }
    if (obj instanceof Integer i) {
        System.out.print("B");
    }
    System.out.print("C");
}
public static void main(String[] args) {
    check("hi");
}
```

**答え:`AC`**。`"hi"`はStringなので1つ目のifで"A"、Integerではないので2つ目のifはスキップ、最後に"C"が必ず出力される。

---

## 8. `interface` のルール(正誤整理)

| 項目 | ルール |
|---|---|
| フィールド | 常に **`public static final`**(暗黙的に付与) |
| フィールドへのアクセス | インターフェース名・実装クラス名・インスタンスいずれからも可能 |
| 抽象メソッド | 暗黙的に `public abstract` |
| defaultメソッド | 暗黙的に `public`(`final`ではなく、オーバーライド可能) |
| staticメソッド | 暗黙的に `public static`(継承・オーバーライド不可) |
| finalメソッド | **interfaceでは宣言できない**(コンパイルエラー) |

誤りだった点:
- 「メンバ変数へのアクセスはインターフェース名からしかできない」→ 誤り。staticなので実装クラス名・インスタンス経由でもアクセス可能
- 「メンバメソッドもpublic static finalとなる」→ 誤り。`final`はinterfaceのメソッドには付けられない(abstract/defaultの趣旨と矛盾するため)

### 例題
次のうち、コンパイルエラーになるものを選べ。

```java
interface Greet {
    String HELLO = "Hello";              // (1)
    final void say();                     // (2)
    default void greet() { System.out.println(HELLO); }  // (3)
}
```

**答え:(2)**。interfaceの抽象メソッドに`final`は付けられない(オーバーライド必須という抽象メソッドの性質と矛盾するため)。(1)は暗黙的に`public static final`となる定数フィールドとして問題なく、(3)のdefaultメソッドも問題ない。

---

## 9. 値渡し(参照型 vs プリミティブ型)

```java
public class Main {
    public static void main(String... args) {
        Item item = new Item("abc");
        int y = 1;
        new Main().method(item, y);
        System.out.print(item.x + ":" + y + ":");
    }
    public void method(Item item, int y){
        item.x = "xyz";
        y += 2;
        System.out.print(item.x + ":" + y + ":");
    }
}
class Item {
    public String x;
    public Item(String x) { this.x = x; }
}
```

**出力:`xyz:3:xyz:1:`**

- Javaは常に**値渡し**
- プリミティブ型(`int`):値そのものがコピーされる → `method`内での変更は`main`に影響しない
- 参照型(`Item`):**参照(アドレス)の値がコピー**される → 変数は別物だが、**指す先のオブジェクトの実体は共通**
- `item.x = "xyz"` は「共有しているオブジェクトの中身を書き換える」操作 → `main`側にも反映される

### 出力を `xyz:3:abc:1:` にしたい場合

```java
public void method(Item item, int y){
    item = new Item("xyz");   // フィールドを書き換えず、新しいオブジェクトへ参照を差し替える
    y += 2;
    System.out.print(item.x + ":" + y + ":");
}
```

- `item.x = "xyz"`(フィールド書き換え)と `item = new Item("xyz")`(参照の差し替え)は全く別の操作
- 参照の差し替えは`method`内のローカル変数の向き先を変えるだけなので、`main`側の`item`には影響しない

| 操作 | 効果 | mainの`item`への影響 |
|---|---|---|
| `item.x = "xyz"` | 同じオブジェクトの中身を書き換える | 影響あり |
| `item = new Item("xyz")` | ローカル変数の参照先を差し替える | 影響なし |

### 例題
```java
static void update(int[] arr) {
    arr[0] = 99;
    arr = new int[]{1, 2, 3};
    arr[0] = 100;
}
public static void main(String[] args) {
    int[] data = {0, 0, 0};
    update(data);
    System.out.println(data[0]);
}
```

**答え:`99`**。`arr[0] = 99`は共有している配列の中身を書き換えるので`main`側に反映される。その後の`arr = new int[]{...}`は`update`内のローカル変数`arr`の参照先を差し替えるだけなので、`main`側の`data`には影響しない。
