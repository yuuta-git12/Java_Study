## println()
- `System.out.println()`は、指定した文字列や値を標準出力（コンソール）に表示するためのメソッドです。`println`は「print line」の略で、表示後に改行を行います。
- 引数に渡すことができる型は、基本データ型（int, double, booleanなど）や参照型（String, Objectなど）です。
- **引数にvoid型の値を渡すことはできません。void型は値を持たないため、表示することができません。**
- 参照型のオブジェクトを引数に渡した場合
  - オブジェクトのtoString()メソッドが呼び出され、その結果が表示されます。
  - クラスの完全修飾名が表示される場合
    - toString()メソッドがオーバーライドされていない場合、デフォルトのtoString()メソッドが呼び出され、クラスの完全修飾名とハッシュコードが表示されます。
    - 例:
    ```java
    public class Sample {
        public static void main(String[] args) {
            Sample sample = new Sample();
            System.out.println(sample); // Sample@15db9742 のように表示される
        }
    }
    ```
    - 引数に配列を渡した場合
      - 配列のtoString()メソッドが呼び出され、その結果が表示されます。
      - 例:
      ```java
      public class Sample {
          public static void main(String[] args) {
              int[] array = {1, 2, 3};
              System.out.println(array); // [I@15db9742 のように表示される
          }
      }
      ```
      - `[I`は、配列の型を表す記号で、`I`はint型の配列であることを示しています。
        - `[I`：int型の配列
        - `[D`：double型の配列
        - `[Ljava.lang.String;`：String型の配列
        - `[Ljava.lang.Object;`：Object型の配列
        - `[Ljava.lang.Integer;`：Integer型の配列
        - `[Ljava.lang.Double;`：Double型の配列
        - `[Ljava.lang.Character;`：Character型の配列
        - `[Ljava.lang.Boolean;`：Boolean型の配列
        - `[Ljava.lang.Byte;`：Byte型の配列
        - `[Ljava.lang.Short;`：Short型の配列
        - `[Ljava.lang.Long;`：Long型の配列
        - `[Ljava.lang.Float;`：Float型の配列
        - `[Ljava.lang.Void;`：Void型の配列
        - `[Ljava.lang.Class;`：Class型の配列
        - `[Ljava.lang.Enum;`：Enum型の配列
        - `[Ljava.lang.annotation.Annotation;`：Annotation型の配列
        - `[Ljava.lang.reflect.Method;`：Method型の配列
- 