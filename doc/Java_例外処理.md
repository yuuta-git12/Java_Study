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
### 例外オブジェクト：
- 例外が発生したときに生成されるオブジェクト
- 例外オブジェクトには、例外の種類や発生場所などの情報が含まれる
- `Throwableクラス`：
    - 例外のスーパークラス
    - Throwableクラスを継承したクラスが例外クラスとなる
    - Throwableクラスには、ErrorクラスとExceptionクラスがある
- `Exceptionクラス`：
    - チェック例外と非チェック例外の両方を表すクラス
    - Exceptionクラスを継承したクラスがチェック例外となる
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
### 例外の種類：
- チェック例外(Checked Exception)：
    - コンパイル時にチェックされる例外
    - 例外処理を行わないとコンパイルエラーになる
    - 例：IOException, SQLExceptionなど
- 非チェック例外(Unchecked Exception)：
    - コンパイル時にチェックされない例外
    - 実行時に発生する可能性がある例外
    - 例：NullPointerException, ArrayIndexOutOfBoundsExceptionなど
### 例外処理をしないと起こること
- プログラムの突然の終了
- データの破損や不整合

## カスタム例外