# Javaのテストコード

## 対象コード

`projava/src/main/java/algorithm/algo_2_4_1.java`

```java
package algorithm;

import java.util.Scanner;

public class algo_2_4_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(2*N + 3);
        sc.close();
    }
}
```

標準入力から整数 `N` を受け取り、`2*N + 3` を標準出力に出力するプログラム。

---

## テストコード全体

`projava/src/test/java/algorithm/algo_2_4_1Test.java`

```java
package algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class algo_2_4_1Test {

    private String runWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(out));
            algo_2_4_1.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return out.toString().trim();
    }

    @Test void testN0()       { assertEquals("3",   runWithInput("0"));    }
    @Test void testN1()       { assertEquals("5",   runWithInput("1"));    }
    @Test void testN5()       { assertEquals("13",  runWithInput("5"));    }
    @Test void testNegative() { assertEquals("1",   runWithInput("-1"));   }
    @Test void testLargeN()   { assertEquals("203", runWithInput("100")); }
}
```

---

## テストの仕組み

### 標準入出力のすり替え

`algo_2_4_1` は標準入力（`System.in`）から値を読み、標準出力（`System.out`）に結果を書く。
テストコードの中で直接 `Scanner` や `println` を制御できないため、Java の `System.setIn` / `System.setOut` を使って入出力先をすり替える。

```
通常時:  キーボード → System.in → プログラム → System.out → コンソール
テスト時: 文字列     → System.in → プログラム → System.out → ByteArrayOutputStream
```

| クラス | 役割 |
|--------|------|
| `ByteArrayInputStream` | 文字列をバイト列に変換し、`System.in` の代わりに使う |
| `ByteArrayOutputStream` | プログラムの出力をメモリ上に溜める |
| `PrintStream` | `ByteArrayOutputStream` を `System.out` として使えるようにラップする |

### try-finally で元に戻す

`System.setIn` / `System.setOut` は JVM グローバルな状態を変更するため、テスト後に必ず元に戻す必要がある。
`finally` ブロックに書くことで、テストが失敗しても確実に復元される。

```java
InputStream originalIn = System.in;   // 元の値を退避
PrintStream originalOut = System.out;

try {
    System.setIn(...);   // すり替え
    System.setOut(...);
    algo_2_4_1.main(...);
} finally {
    System.setIn(originalIn);   // 必ず復元
    System.setOut(originalOut);
}
```

---

## よくある疑問

### `new String[]{}` は必要か？

不要（`null` や `new String[0]` でも動く）。`args` はこのコードで使われていないため、何を渡しても結果は変わらない。慣習として空配列を渡すことが多い。

```java
algo_2_4_1.main(new String[]{});  // 慣習的な書き方
algo_2_4_1.main(null);            // これでも動く（argsを使わないため）
```

### なぜ `new algo_2_4_1()` でインスタンスを作らなくて良いのか？

`main` メソッドが `static` だから。

```java
public static void main(String[] args) { ... }
```

`static` メソッドはクラスに属するため、インスタンスを作らずクラス名から直接呼べる。

```java
// staticメソッド → インスタンス不要
algo_2_4_1.main(new String[]{});

// インスタンスメソッド（staticなし）→ インスタンスが必要
algo_2_4_1 obj = new algo_2_4_1();
obj.someMethod();
```

### なぜテストコードも `package algorithm;` にするのか？

同じパッケージにすることで **`import` なしで参照できる**。また、`public` でないメンバー（パッケージプライベート）にもアクセスできるようになる。

```java
// 同じパッケージ → importなしで使える
algo_2_4_1.main(new String[]{});

// 別パッケージ → importが必要
import algorithm.algo_2_4_1;
algo_2_4_1.main(new String[]{});
```

`static` 呼び出しができる理由（`static` メソッド）とは別の話。

| 目的 | 手段 |
|------|------|
| インスタンスなしで呼べる | `main` が `static` メソッドだから |
| `import` なしで参照できる | テストコードが同じ `package` だから |

---

## テストケース一覧

| テストメソッド | 入力 N | 期待出力 (`2*N+3`) | 確認内容 |
|----------------|--------|-------------------|---------|
| `testN0` | 0 | 3 | N=0 の境界値 |
| `testN1` | 1 | 5 | 正の最小値 |
| `testN5` | 5 | 13 | 通常の正の値 |
| `testNegative` | -1 | 1 | 負の値 |
| `testLargeN` | 100 | 203 | 大きな値 |
