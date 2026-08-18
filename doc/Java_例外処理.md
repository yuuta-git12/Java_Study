## 例外発生の仕組み・例外処理
### 例外：
- プログラムの実行中に発生する異常な状態のこと
- 例外が発生すると、通常の処理の流れが中断され、例外処理が行われる
### 例外処理：
- 例外が発生した場合に、プログラムが異常終了するのを防ぐための処理
- 例外処理を行うことで、プログラムの安定性を向上させることができる
### 例外がスロー(throw)される
- 例外が発生すると、Java仮想マシン(JVM)は例外オブジェクトを生成し、スローする
- スローされた例外は、呼び出し元のメソッドに伝播される
### 例外の種類：
- チェック例外(Checked Exception)：
    - コンパイル時にチェックされる例外
    - **必須の例外処理を行う必要がある**
    - 例外処理を行わないとコンパイルエラーになる
    - 例：IOException, SQLExceptionなど
- 非チェック例外(Unchecked Exception)：
    - コンパイル時にチェックされない例外
    - **必須の例外処理を行う必要はない**
    - 実行時に発生する可能性がある例外
    - 例：NullPointerException, ArrayIndexOutOfBoundsExceptionなど
### 例外処理をしないと起こること
- プログラムの突然の終了
- データの破損や不整合
### 例外オブジェクト：
- 例外が発生したときに生成されるオブジェクト
- 例外オブジェクトには、例外の種類や発生場所などの情報が含まれる
- `Throwableクラス`：
    - 例外のスーパークラス
    - Throwableクラスを継承したクラスが例外クラスとなる
    - Throwableクラスには、ErrorクラスとExceptionクラスがある
- `Exceptionクラス`：
    - チェック例外と非チェック例外の両方を表すクラス
    - Exceptionクラスを継承したクラスのうちRuntimeExceptionとそのサブクラス以外はチェック例外となる
- `Errorクラス`：
    - システムエラーや仮想マシンのエラーなど、プログラムでは処理できない重大なエラーを表すクラス
    - メモリ不足やスタックオーバーフローなどのエラーが含まれる
    - Errorクラスを継承したクラスがエラーとなる
- `java.lang.Throwable`クラスの階層
    - Throwable
        - Error
            - VirtualMachineError
                - OutOfMemoryError
                - StackOverflowError
            - AssertionError
        - Exception
            - IOException
            - SQLException
            - RuntimeException
                - NullPointerException
                - ArrayIndexOutOfBoundsException
---
## カスタム例外
### 構文
```java
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
```
- Exceptionクラスを継承してカスタム例外クラスを作成する
- 呼び出し時の使用例
```java
public class Main {
    public static void main(String[] args) {
        try {
            throw new CustomException("カスタム例外が発生しました");
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
```
---
## 例外処理の構文
### try-catch文
- 例外が発生する可能性のある処理をtryブロックに記述し、例外が発生した場合の処理をcatchブロックに記述する
- 構文：
```java
try {
    // 例外が発生する可能性のある処理
} catch (ExceptionType e) {
    // 例外が発生した場合の処理
}
```
- 例：
```java
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // 例外が発生する可能性のある処理
        } catch (ArithmeticException e) {
            System.out.println("0で割ることはできません");
        }
    }
}
```
### try-catchによる例外のキャッチパターン
- try-catch
- try-catch-finally
- try-catch-finally 複数のcatchブロック
#### try-catch-finally文
- tryブロックで例外が発生した場合、catchブロックで例外をキャッチし、finallyブロックで必ず実行される処理
- finallyブロックの特徴
  - catchブロックの有無に関わらず、例外の有無に関わらず必ず実行される
  - リソースの解放や後処理などに使用される
  - catchブロックにreturn文があっても、finallyブロックは実行される
- 構文：
```java
try {
    // 例外が発生する可能性のある処理
} catch (ExceptionType e) {
    // 例外が発生した場合の処理
} finally {
    // 例外の有無に関わらず必ず実行される処理
}
```
- 例：
```java
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // 例外が発生する可能性のある処理
        } catch (ArithmeticException e) {
            System.out.println("0で割ることはできません");
        } finally {
            System.out.println("処理が終了しました");
        }
    }
}
```
### multi-catch文
- 複数の例外を1つのcatchブロックで処理することができる
- multi-catch文の場合,例外の参照変数`e`は,finalとして扱われるため、catchブロック内で再代入することはできない
- 構文：
```java
try {
    // 例外が発生する可能性のある処理
} catch (ExceptionType1 | ExceptionType2 e) {
    // 例外が発生した場合の処理
}
```
- 例：
```java
public class Main {
    public static void main(String[] args) {
        try {
            int[] array = new int[5];
            array[10] = 1; // ArrayIndexOutOfBoundsExceptionが発生
            String str = null;
            str.length(); // NullPointerExceptionが発生
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("配列の範囲外アクセスまたはnull参照が発生しました");
        }
    }
}
```
### try-with-resources文
- リソースを自動的にクローズするための構文
  - リソース：
    - ファイル、ネットワーク接続、データベース接続などの外部リソース
- AutoCloseableインターフェースを実装したリソースをtryブロックで宣言することで、tryブロックの終了時に自動的にクローズされる
- 事前に生成したリソースをtryブロックで使用する場合は、try-with-resources文を使用することで、リソースのクローズ処理を簡潔に記述できる
- tryで使用する変数は、自動的にfinalとして扱われるため、再代入はできない
- 構文：
```java
try (ResourceType resource = new ResourceType()) {
    // リソースを使用する処理
} catch (ExceptionType e) {
    // 例外が発生した場合の処理
}
```
- 例：
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("ファイルの読み込みに失敗しました");
        }
    }
}
```
### try-with-resourcesのキャッチパターン
- try
- try-catch
- try-catch-finally
- try-with-resources

### try-with-resourcesの例外処理の流れ
#### 例外が発生しない場合
1. tryブロック内の処理が正常に終了する
2. tryブロックを抜けた直後に、リソースが自動的にクローズされる
3. catchブロックは実行されない
4. finallyブロックがあれば、finallyブロックが実行される
#### 例外が発生した場合
1. tryブロック内の処理で例外が発生する
2. 例外がスローされる
3. tryブロックを抜けた直後に、リソースが自動的にクローズされる
4. catchブロックが実行される
5. finallyブロックがあれば、finallyブロックが実行される

- ※：複数のリソースを使用している場合は、リソースのクローズ処理はtryブロックを抜けた直後に、宣言された順番の逆順で行われる
  - リソースに依存関係がある場合、依存関係の順番に注意する必要がある