# Javaの型変換
## 暗黙の型変換
- Javaでは、異なるデータ型の値を演算する場合、コンパイラが自動的に型変換を行うことがあります。これを暗黙の型変換（implicit type conversion）と呼びます。暗黙の型変換は、主に以下のルールに従って行われます。
- 整数型の昇格: 小さい整数型（byte, short, char）は、演算時にint型に昇格されます。
- 浮動小数点型の昇格: float型は、演算時にdouble型に昇格されます。
- 整数型と浮動小数点型の混合: 整数型と浮動小数点型が混在する場合、整数型は浮動小数点型に昇格されます。
- 例:
```java
byte b = 10;
short s = 20;
int result1 = b + s; // bとsはint型に昇格
float f = 5.5f;
double result2 = f + result1; // fはdouble型に昇格
```
- 例:
```java
int i = 10;
double d = 5.5;
double result = i + d; // iはdouble型に昇格
System.out.println(result); // 出力: 15.5
```
- double型を引数に持つメソッドにint型の値を渡す場合、int型は自動的にdouble型に変換されます。
- 例:
```java
public class Main {
    public static void main(String[] args) {
        int i = 10;
        double d = 5.5;
        double result = add(i, d); // iはdouble型に昇格
        System.out.println(result); // 出力: 15.5
    }
    public static double add(double a, double b) {
        return a + b;
    }
}
```

## 明示的な型変換
- 明示的な型変換（explicit type conversion）は、プログラマが明示的に型変換を行うことを指します。これにはキャスト演算子を使用します。キャスト演算子は、変換したい型を括弧で囲んで指定します。
- 例:
```java
double d = 9.78;
int i = (int) d; // double型をint型にキャスト
System.out.println(i); // 出力: 9
float f = 5.5f;
int j = (int) f; // float型をint型にキャスト
System.out.println(j); // 出力: 5
```
## 型変換の注意点
- 型変換を行う際には、データの損失や精度の低下に注意する必要があります。特に、浮動小数点型から整数型への変換では、小数点以下が切り捨てられるため、値が変わる可能性があります。また、大きな値を小さな型に変換する場合、オーバーフローが発生することがあります。
- 例:
```java
double d = 9.99;
int i = (int) d; // 小数点以下が切り捨て
System.out.println(i); // 出力: 9
int largeValue = 300;
byte b = (byte) largeValue; // オーバーフローが発生
System.out.println(b); // 出力: 44 (300 % 256 = 44)
```
## オートボクシングとアンボクシング
- Javaでは、プリミティブ型と対応するラッパークラス（Integer, Double, Floatなど）との間で自動的に変換が行われることがあります。
- これをオートボクシング（autoboxing）とアンボクシング（unboxing）と呼びます。
- オートボクシングは、プリミティブ型の値をラッパークラスのオブジェクトに自動的に変換することを指します。
- アンボクシングはその逆で、ラッパークラスのオブジェクトをプリミティブ型の値に自動的に変換することを指します。
- 例:
```java
Integer intObj = 10; // オートボクシング: int型からIntegerオブジェクトに変換
int intValue = intObj; // アンボクシング: Integerオブジェクトからint型に変換
System.out.println(intValue); // 出力: 10
```

#レビュー済み