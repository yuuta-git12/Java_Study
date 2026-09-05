# Java 学習メモ

## 1. `default` メソッドと `abstract` メソッド

### interfaceの場合

```java
interface Sample {
    // abstractメソッド(暗黙的にabstract) → {}禁止
    void method1();

    // 明示的にabstractを付けても同じ → {}禁止
    abstract void method2();

    // defaultメソッド → {}必須
    default void method3() {
        System.out.println("default実装");
    }

    // staticメソッドも{}必須
    static void method4() {
        System.out.println("static実装");
    }
}
```

- interface内のメソッドは、何も付けなければ暗黙的に `public abstract` なので `{}` は書けない(書くとコンパイルエラー)。
- `default` を付けたメソッドは実装(`{}`)が **必須**。付けないとコンパイルエラー。

### classの場合

```java
abstract class Sample {
    // abstractメソッド → {}禁止、かつクラス自体もabstractである必要がある
    abstract void method1();

    // 通常のメソッド(いわゆる"default"にあたる) → {}必須
    void method2() {
        System.out.println("通常の実装");
    }
}
```

- `abstract` を付けたメソッドは `{}` を書いてはいけない。
- `abstract` を付けていない通常のメソッドは `{}` が必須。
- **`abstract` メソッドを1つでも持つクラスは、クラス自体にも `abstract` を付ける必要がある。**

### まとめ表

| 場所 | 修飾子 | `{}` の要否 |
|---|---|---|
| interface | (何もなし) / `abstract` | 禁止 |
| interface | `default` | 必須 |
| interface | `static` | 必須 |
| interface | `private`(Java9〜) | 必須 |
| class | `abstract` | 禁止 |
| class | (何もなし=通常メソッド) | 必須 |

「`abstract` が付いていれば `{}` なし、付いていなければ `{}` あり」というルールは、interfaceでもclassでも一貫している。

---

## 2. チェック例外と `throws`

### サンプルコード(修正版)

```java
public class Main {
    public static void main(String[] args) throws Exception {
        methodA("Ex");
    }
    static void methodA(String s) throws Exception {
        if(s.length() < 0) throw new RuntimeException(); // (A) unchecked → throws不要
        methodB(s);
    }
    static void methodB(String s) throws Exception {
        if(!(s.endsWith("Z")))
            throw new Exception("Incorrect letter");     // (B) checked → throws必須
    }
}
```

### 例外の分類

| 種類 | 継承元 | throws宣言 |
|---|---|---|
| Error | `Error` | 不要(通常catchもしない) |
| チェック例外 | `Exception`(`RuntimeException`を除く) | **必須**(catchかthrows) |
| 非チェック例外 | `RuntimeException` | 不要 |

- `RuntimeException` は非チェック例外なので `throws` 宣言は不要。
- `Exception` を直接 `new` して投げるとチェック例外に分類され、呼び出し元は **catchするかthrowsするか** をコンパイラに強制される。

### `main`でtry-catchしない場合

`throws Exception` とだけ書いて`catch`しない場合、例外はJVMに投げられる。JVMは以下を自動的に行う。

- スタックトレースを標準エラー出力に表示
- プログラムを異常終了(終了コード1)

**try-catchなし(throwsで流す)が適切なケース**
- 学習用コード、簡単なスクリプト、ツール
- 例外発生時に即座に異常終了させて原因を確認したい場合
- 例外から回復する手段がない場合

**try-catchありが適切なケース**
- 実運用のアプリケーション(ユーザーにわかりやすいメッセージを出したい)
- 例外が起きても処理を継続・リトライしたい
- ログに記録してから正常終了させたい

```java
public class Main {
    public static void main(String[] args) {
        try {
            methodA("Ex");
        } catch (Exception e) {
            System.err.println("エラーが発生しました: " + e.getMessage());
        }
    }
    static void methodA(String s) throws Exception {
        if (s.length() < 0) throw new RuntimeException();
        methodB(s);
    }
    static void methodB(String s) throws Exception {
        if (!(s.endsWith("Z")))
            throw new Exception("Incorrect letter");
    }
}
```

---

## 3. `final` 修飾子

`final` は付ける対象によって意味が異なる。

| 対象 | `final`の意味 |
|---|---|
| 変数(ローカル/フィールド) | 再代入禁止(実質的な定数) |
| メソッド | オーバーライド禁止 |
| クラス | 継承禁止 |

### 変数

```java
final int MAX = 100;
MAX = 200; // コンパイルエラー: 再代入不可
```

### メソッド

```java
class Parent {
    final void method1() {
        System.out.println("Parentの実装");
    }
}

class Child extends Parent {
    // コンパイルエラー: overridden method is final
    void method1() {
        System.out.println("Childで上書きしたい");
    }
}
```

継承自体は可能だが、`final` が付いたメソッドだけはオーバーライドできない。

### クラス

```java
final class Parent { }

// コンパイルエラー: cannot inherit from final Parent
class Child extends Parent { }
```

`String` クラスなどはこの `final` が付いており継承不可。

「変更・上書きを禁止する」という共通の思想がベースにあり、対象によって「何を変更禁止にするか」が変わる。

---

## 4. `final` メソッドと `super.メソッド()`

`final` メソッドと `super.メソッド()` は別の話。

### `final`メソッド(オーバーライド不可)の場合

```java
class Parent {
    final void method1() {
        System.out.println("Parentの実装");
    }
}

class Child extends Parent {
    void test() {
        method1();        // 呼べる(継承したものをそのまま使う)
        super.method1();  // これも書けるが、同じ意味
    }
}
```

`final` が付いていると子クラスは独自実装を持てないため、`method1()` と `super.method1()` の結果は同じ(常にParentの実装)。

### 通常メソッド(オーバーライド済み)の場合

```java
class Parent {
    void method1() {  // finalなし → オーバーライド可能
        System.out.println("Parentの処理");
    }
}

class Child extends Parent {
    @Override
    void method1() {
        super.method1();  // 親の処理を先に呼ぶ
        System.out.println("Childの追加処理");
    }
}
```

`Child`のインスタンスで`method1()`を呼ぶと以下が出力される。

```
Parentの処理
Childの追加処理
```

`super.method1()` がなければParentの処理は実行されない。

### まとめ

| 状況 | `super.メソッド()`の意味 |
|---|---|
| `final`メソッド(オーバーライド不可) | 呼んでも呼ばなくても結果は同じ(常に親の実装) |
| 通常メソッド(オーバーライド済み) | 親の実装を明示的に呼び出す(意味がある) |

`super.メソッド()` が本領を発揮するのは、あくまでオーバーライドしているとき。

---

## 5. `try`-`catch`-`finally` の構文ルール

### 通常の`try`

`try` ブロックは、例外の種類(チェック/非チェック)に関わらず、**必ず`catch`か`finally`のどちらか(または両方)が必要**。これは文法上の決まりで、例外の種類とは無関係。

```java
try {
    // 何か処理
}
// catchもfinallyもない → コンパイルエラー(例外の種類に関係なく)
```

```java
try {
    // 何か処理
} finally {
    // 後処理
}
// catchがなくてもOK(finallyがあれば良い)
```

### 「catch/throwsの強制」との違い

これは`try`構文の話とは別で、**メソッド呼び出し時にチェック例外を投げる可能性がある場合の話**。

```java
static void methodB(String s) throws Exception {
    if (!(s.endsWith("Z")))
        throw new Exception("Incorrect letter");
}

static void methodA(String s) {
    methodB(s); // コンパイルエラー! catchするかthrowsするか必須
}
```

- チェック例外を投げる可能性のあるメソッドを呼ぶ側は、`try-catch`でcatchするか、自分のメソッドにも`throws`を付けて上に投げるか、どちらかが必須。
- 非チェック例外(`RuntimeException`系)を投げる可能性のあるメソッドは、呼ぶ側で何もしなくてもコンパイルは通る。

### まとめ表

| ルール | 対象 |
|---|---|
| `try`には`catch`か`finally`が必須 | チェック/非チェック問わず、構文上のルール |
| メソッド呼び出し側で`catch`か`throws`が必須 | チェック例外のみ(コンパイラによる強制) |
| メソッド呼び出し側で`catch`も`throws`も不要 | 非チェック例外(`RuntimeException`系) |

---

## 6. try-with-resources

### 通常の`try`との最大の違い:`catch`も`finally`も省略できる

```java
try (FileReader fr = new FileReader("file.txt")) {
    // 何か処理
}
// catchもfinallyもないが、これはOK(コンパイルエラーにならない)
```

`()` 内にリソース宣言があれば、それだけで構文的に成立する。リソースの後処理(`close()`)をJavaが自動的に保証してくれる仕組みのため。

### ただし「チェック例外の強制ルール」はそのまま生きている

`FileReader` のコンストラクタは `FileNotFoundException`(チェック例外)を投げる可能性があるので、それは別途対処が必要。

```java
static void method1() {
    try (FileReader fr = new FileReader("file.txt")) {
        // 処理
    }
    // コンパイルエラー! FileNotFoundExceptionをcatchもthrowsもしていない
}
```

正しくは:

```java
// パターン1: catchする
static void method1() {
    try (FileReader fr = new FileReader("file.txt")) {
        // 処理
    } catch (IOException e) {
        System.out.println("エラー: " + e.getMessage());
    }
}

// パターン2: throwsで投げる
static void method1() throws IOException {
    try (FileReader fr = new FileReader("file.txt")) {
        // 処理
    }
}
```

### `close()`自体も例外を投げうる

`AutoCloseable` インターフェースの `close()` メソッドは `throws Exception` と宣言されているため、リソースの実装によってはクローズ時にもチェック例外が発生しうる。これも自動的にcatch/throwsの対象になる(通常はcatch節で1つにまとめて処理される)。

### まとめ表

| 項目 | 通常の`try` | try-with-resources |
|---|---|---|
| `catch`/`finally`の要否 | どちらか必須 | リソース宣言があれば両方省略可 |
| チェック例外のcatch/throws義務 | あり | 同様にあり(リソースのopen/closeで発生しうる例外) |
| リソースの後始末 | 自分で`finally`に書く必要 | 自動でclose()される |

「`catch`/`finally`が要らなくなる」のは構文上のメリットであって、「チェック例外はcatchかthrowsが必須」という大原則自体は変わらない。
