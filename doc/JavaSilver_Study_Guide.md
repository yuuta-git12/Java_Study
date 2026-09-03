# JavaSilver Study Guide(例題付き)

演習問題を解きながら整理した「つまずきやすいポイント」「本番での解き方」に加え、各テーマの理解度確認用の例題を追加した総合ガイドです。例題は先に自分で答えを出してから解説を読むことをおすすめします。

---

## 0. Exam Mindset

- 合格ラインは正答率63〜65%程度。頻出パターンを押さえれば十分届く範囲。
- **コードの中身(ロジック)を読む前に、修飾子・型・例外の種類など「機械的にチェックできる部分」を先に確認する**のが最大の時短テクニック。
- 「実行結果を答えよ」という問題文でも、**そもそもコンパイルエラーで実行されない**選択肢が非常に多い。まず「これはコンパイルが通るか?」を疑うクセをつける。
- 1問1分30秒前後が目安。修飾子スキャン→型チェック→ロジック、の順で「読まなくていい部分」を早く切り捨てる。

---

## 1. String / StringBuilder

### 1-1. Stringは不変(immutable)
```java
String s = "abc";
s.concat("def");
System.out.println(s); // "abc"(変わらない)
```

### 1-2. StringBuilderは可変(mutable)
```java
StringBuilder sb = new StringBuilder("abc");
sb.append("def");
System.out.println(sb); // "abcdef"(直接変わる)
```

### 1-3. よく出るメソッドの仕様
- `substring(begin, end)` → endは含まない(exclusive)
- `indexOf` → 見つからないと `-1`(例外にはならない)
- `charAt(int)` → 範囲外は `StringIndexOutOfBoundsException`

### 1-4. `+`演算子とコンパイルエラー
```java
String s = "abc";
s = s + 1; // OK "abc1"
s = 1 + s; // OK "1abc"
int x = "abc"; // コンパイルエラー
```

### 1-5. `==` と `equals()` の使い分け(超頻出)

| コード | 結果 | 理由 |
|---|---|---|
| `String a = "abc"; a == "abc"` | true | 両方リテラル → 文字列プールで同一オブジェクトを共有 |
| `String a = new String("abc"); a == "abc"` | false | `new`は強制的に別オブジェクトを生成 |
| `String b = "abc"; String a = b + "def"; a == "abcdef"` | false | 変数を含む`+`は**実行時**に新しいオブジェクトを生成 |

### 1-6. `trim()` の落とし穴
- `\u0020`(半角スペース)以下の文字のみ除去。**全角スペース(`\u3000`)は対象外**
- 除去が発生した場合、返るのは実行時生成の新しいStringオブジェクト

### 1-7. 数値リテラルの表記
- `0b0011`(2進数)、`0x000C`(16進数)は**コンパイル時に値が確定**。実行時変換は存在しない

### 📝 例題1-A
```java
public class Q1 {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        StringBuilder sb = new StringBuilder("Ja");
        sb.append("va");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(a == sb.toString());
    }
}
```
<details><summary>解答・解説</summary>

```
true
false
true
false
```
- `a == b`:両方リテラル → プール共有 → true
- `a == c`:`c`は`new`で生成 → 別オブジェクト → false
- `a.equals(c)`:値の比較 → 中身は同じ → true
- `a == sb.toString()`:`toString()`は実行時に新しいStringを生成 → false
</details>

### 📝 例題1-B
```java
public class Q2 {
    public static void main(String[] args) {
        String s = "　Study Java　"; // 前後は全角スペース
        s = s.trim();
        System.out.println("[" + s + "]");
        System.out.println(s.length());
    }
}
```
<details><summary>解答・解説</summary>

出力は元の文字列がそのまま(全角スペース付き)残る。長さも変わらない。
`trim()`は半角スペース以下のコードポイントしか除去しないため、全角スペース(`\u3000`)には効果がない。
</details>

---

## 2. Collections(List, Set, Map)/ Generics

- `ArrayList`:順序あり・重複可 / `HashSet`:順序保証なし・重複不可 / `LinkedHashSet`:追加順を保持 / `TreeSet`:ソートされる
- `HashMap`はキーの重複不可(上書きされる)、順序保証なし
- 拡張for文中でコレクションを直接変更すると**`ConcurrentModificationException`(実行時エラー)**
- `Map.put()`はキーが既存なら**古い値を返す**、新規なら`null`
- `List.of()` / `Map.of()`は不変 → `add()`すると`UnsupportedOperationException`(実行時エラー)

### 📝 例題2-A
```java
import java.util.*;
public class Q3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
        for (String s : list) {
            if (s.equals("B")) {
                list.remove(s);
            }
        }
        System.out.println(list);
    }
}
```
<details><summary>解答・解説</summary>

**実行時例外**(`ConcurrentModificationException`)。
拡張for文でIteratorを使って反復している最中に、リストを直接`remove()`すると検出され例外が発生する。
安全に削除したい場合は`Iterator`の`remove()`メソッドを使う、または`removeIf()`を使う。
</details>

### 📝 例題2-B
```java
import java.util.*;
public class Q4 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Integer old1 = map.put("x", 1);
        Integer old2 = map.put("x", 2);
        System.out.println(old1);
        System.out.println(old2);
        System.out.println(map.get("x"));
    }
}
```
<details><summary>解答・解説</summary>

```
null
1
2
```
`put()`はキーが存在しなければ`null`を返し、既存キーなら上書き前の古い値を返す。
</details>

---

## 3. オーバーロード解決の速解き手順

1. 呼び出し時の実引数の**宣言型**だけを確認する
2. 型が完全一致する定義があればそれが最優先
3. 完全一致がなければ`Object`版など汎用的な定義が使われる
4. **該当する定義の中身だけ**を読む

### 📝 例題3-A
```java
public class Q5 {
    static void show(Object o) { System.out.println("Object: " + o); }
    static void show(String s) { System.out.println("String: " + s); }
    static void show(StringBuilder sb) { System.out.println("SB: " + sb); }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hi");
        show(sb);
        show(sb.toString());
        show((Object) sb);
    }
}
```
<details><summary>解答・解説</summary>

```
SB: Hi
String: Hi
Object: Hi
```
- `show(sb)` → 引数の型は`StringBuilder` → 完全一致する`show(StringBuilder)`
- `show(sb.toString())` → `toString()`で型が`String`に変わる → `show(String)`
- `show((Object) sb)` → 明示的キャストで型が`Object`扱いになる → `show(Object)`
</details>

---

## 4. オートボクシング / アンボクシング

```java
Integer obj = 5;   // オートボクシング(int → Integer)
int x = obj;        // アンボクシング(Integer → int)
++obj;               // アンボクシング → +1演算 → オートボクシング
```

- `switch`の対象や`case`ラベルとの比較でもラッパークラスは自動でアンボクシングされる
- 引数の型と戻り値の型が食い違う場合、その境界で必ず変換が起きている
- コンパイルエラーにはならず正常動作する

### 📝 例題4-A
```java
public class Q6 {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3};
        for (int i = 0; i < nums.length; i++) {
            nums[i] = calc(nums[i]);
        }
        System.out.println(java.util.Arrays.toString(nums));
    }
    static int calc(Integer n) {
        return switch (n) {
            case 1, 2 -> n * 10;
            default -> n;
        };
    }
}
```
<details><summary>解答・解説</summary>

```
[10, 20, 3]
```
`switch(n)`は`Integer`をアンボクシングして`int`のcaseラベルと比較。`n * 10`もアンボクシング→計算→戻り値は`int`。
`calc`の戻り値`int`が`nums[i]`(`Integer`)に代入される際にオートボクシングされる。
</details>

---

## 5. 例外(検査例外 vs 非検査例外)

| 種類 | 代表例 | throws宣言 |
|---|---|---|
| 検査例外(checked) | `Exception`, `IOException` | **必須**(なければコンパイルエラー) |
| 非検査例外(unchecked) | `RuntimeException`とそのサブクラス | 不要 |

検査例外は呼び出し階層を遡って`throws`が必要(または`try-catch`で止める)。

### 📝 例題5-A
```java
public class Q7 {
    public static void main(String[] args) {
        try {
            process();
        } catch (RuntimeException e) {
            System.out.println("caught: " + e.getMessage());
        }
    }
    static void process() {
        validate();
    }
    static void validate() {
        throw new IllegalArgumentException("invalid");
    }
}
```
<details><summary>解答・解説</summary>

```
caught: invalid
```
`IllegalArgumentException`は`RuntimeException`のサブクラス(非検査例外)なので、`process()`や`validate()`に`throws`宣言は不要。
`main`の`try-catch`が`RuntimeException`型でキャッチできるので正常終了する。
</details>

### 📝 例題5-B(コンパイルエラーになる例)
```java
public class Q8 {
    public static void main(String[] args) {
        process();
    }
    static void process() {
        check();
    }
    static void check() throws Exception {
        throw new Exception("checked!");
    }
}
```
<details><summary>解答・解説</summary>

**コンパイルエラー**。
`check()`は検査例外`Exception`を`throws`宣言しているが、呼び出し元の`process()`が`try-catch`も`throws`もしていないため、`process()`の時点で
`unreported exception Exception; must be caught or declared to be thrown`
が発生する。
</details>

---

## 6. sealed / permits

| sealed型 | permitted側 | キーワード | 許可の種類 |
|---|---|---|---|
| `sealed interface` | クラス | `implements` | 実装の許可 |
| `sealed interface` | インターフェース | `extends` | 拡張の許可 |
| `sealed class` | クラス | `extends` | 拡張の許可 |

permitted側は必ず`final` / `sealed` / `non-sealed`のいずれかを宣言(インターフェースは`final`不可)。
`permits`が要求するのは**直接**のサブタイプ関係のみ。

### 📝 例題6-A
```java
sealed interface Vehicle permits Car, Bike {}
final class Car implements Vehicle {}
non-sealed class Bike implements Vehicle {}
class Scooter extends Bike {}

public class Q9 {
    public static void main(String[] args) {
        Vehicle v = new Scooter();
        System.out.println(v instanceof Vehicle);
    }
}
```
<details><summary>解答・解説</summary>

**コンパイルは通り、実行結果は`true`。**
`Bike`は`non-sealed`と宣言されているため、それ以降の継承(`Scooter extends Bike`)は自由。`permits`の制約は`Vehicle`直下(`Car`, `Bike`)にのみかかり、`Bike`から先は制限が解除されている。
</details>

### 📝 例題6-B(コンパイルエラーになる例)
```java
sealed interface Vehicle permits Car, Bike {}
final class Car implements Vehicle {}
sealed interface Bike extends Vehicle permits Moped {}
final class Moped implements Bike {}

// 以下を追加
final class Scooter implements Vehicle {} // 未宣言のサブタイプ
```
<details><summary>解答・解説</summary>

**コンパイルエラー**。
`Vehicle`の`permits`には`Car`と`Bike`しか列挙されていないため、`Scooter`が`Vehicle`を直接実装することは許可されていない。
`permits`リストに追加するか、`Scooter`自体を削除する必要がある。
</details>

---

## 7. final / private とオーバーライド

- `final`メソッド → オーバーライド禁止
- `private`メソッド → 継承されない(同名でも無関係な別メソッド)
- `static`メソッド → オーバーライドではなく「隠蔽(hiding)」
- オーバーライド時にアクセス修飾子を狭めるとコンパイルエラー
- フィールドはポリモーフィズムが効かない(静的型で決まる)

### 📝 例題7-A
```java
class Parent {
    static String greet() { return "Hello from Parent"; }
}
class Child extends Parent {
    static String greet() { return "Hello from Child"; }
}
public class Q10 {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.greet());
    }
}
```
<details><summary>解答・解説</summary>

```
Hello from Parent
```
`static`メソッドはオーバーライドではなく隠蔽(hiding)。呼び出されるメソッドは**参照変数の静的型(`Parent`)**で決まるため、`Child`のインスタンスであっても`Parent.greet()`が呼ばれる。
(インスタンスメソッドなら実行時のオブジェクトの型で決まり`"Hello from Child"`になっていた点との対比が重要)
</details>

### 📝 例題7-B(コンパイルエラーになる例)
```java
class Base {
    protected void run() { System.out.println("Base run"); }
}
class Derived extends Base {
    private void run() { System.out.println("Derived run"); } // アクセスを狭めている
}
```
<details><summary>解答・解説</summary>

**コンパイルエラー**。
オーバーライドではアクセス修飾子を親より狭めることはできない(`protected` → `private`は不可)。同じか、より広い修飾子(`protected`のまま、または`public`)にする必要がある。
</details>

---

## 8. 本番用チェックリスト(1問ずつ使う)

- [ ] `throw`があれば、検査例外か非検査例外かを先に確認
- [ ] 検査例外なら呼び出し元すべてに`throws`があるか確認
- [ ] `final` / `private` / `static`の修飾子とオーバーライドの矛盾がないか確認
- [ ] 複数の同名メソッド(オーバーロード)があれば、引数の**型**だけで先にどれが呼ばれるか決める
- [ ] `sealed` / `permits`が絡む場合、直接関係か間接関係かを確認
- [ ] `Integer`型の変数に演算子が使われていたら、ボクシング/アンボクシングの往復を疑う
- [ ] String操作は「戻り値を代入しているか」、StringBuilderは「直接変更されているか」を確認
- [ ] `==`が出てきたら「リテラルかnewか」「コンパイル時確定か実行時生成か」を確認
- [ ] `trim()`が出てきたら対象の空白文字が半角か全角かを確認
- [ ] 上記すべてクリアして初めて、実際のロジック(値の流れ)を追う

---

## 9. 頻出の「間違いやすい選択肢」パターン集

- 「非検査例外(RuntimeException)にもthrowsが必須」→ **誤り**(不要)
- 「メソッドBだけにthrowsを付ければコンパイルが通る」→ **誤り**(呼び出し元も必要)
- 「2進数/16進数リテラルは実行時に変換コストがかかる」→ **誤り**(コンパイル時に確定)
- 「privateメソッドはオーバーライドされる」→ **誤り**(継承されないので無関係な別メソッド)
- 「trim()は全角スペースも除去する」→ **誤り**(半角スペース以下の文字のみ)
- 「間接的に継承していればpermitsの要件を満たす」→ **誤り**(直接関係のみ有効)
- 「staticメソッドはオーバーライドされ、実行時の型で決まる」→ **誤り**(隠蔽であり静的型で決まる)
