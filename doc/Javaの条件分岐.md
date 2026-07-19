# Javaの条件分岐
## 制御構文
- Javaでは、条件分岐を行うために、if文、switch文などの制御構文が用意されています。これらを使用することで、プログラムの実行フローを制御することができます。
## if文
- if文は、指定した条件がtrueの場合に、特定の処理を実行するための構文です。if文の基本的な構文は以下の通りです。
```java
if (条件式) {
    // 条件式がtrueの場合に実行される処理
}
```
- 例:
```java
int number = 10;
if (number > 0) {
    System.out.println("numberは正の数です。");
}
if (number < 0) {
    System.out.println("numberは負の数です。");
}
if (number == 0) {
    System.out.println("numberは0です。");
}
```
## if-else文
- if-else文は、指定した条件がtrueの場合に特定の処理を実行し、falseの場合には別の処理を実行するための構文です。if-else文の基本的な構文は以下の通りです。
```java
if (条件式) {
    // 条件式がtrueの場合に実行される処理
} else {
    // 条件式がfalseの場合に実行される処理
}
```
- 例:
```java
int number = -5;
if (number > 0) {
    System.out.println("numberは正の数です。");
} else {
    System.out.println("numberは正の数ではありません。");
}
```
## if-else if-else文
- if-else if-else文は、複数の条件を順番に評価し、最初にtrueとなった条件の処理を実行するための構文です。if-else if-else文の基本的な構文は以下の通りです。
```java
if (条件式1) {
    // 条件式1がtrueの場合に実行される処理
} else if (条件式2) {
    // 条件式2がtrueの場合に実行される処理
} else {
    // すべての条件式がfalseの場合に実行される処理
}
```
- 例:
```java
int number = 0;
if (number > 0) {
    System.out.println("numberは正の数です。");
} else if (number < 0) {
    System.out.println("numberは負の数です。");
} else {
    System.out.println("numberは0です。");
}
```
## switch文(Java 14から正式に導入)
- switch文は、指定した変数の値に応じて、複数の処理の中から1つを選択して実行するための構文です。switch文の基本的な構文は以下の通りです。
- 指定できるデータ型は、整数型（byte, short, int, long）、文字型（char）、列挙型（enum）、文字列型（String）です。
- 参照型のオブジェクトは、switch文で使用することはできません。
- ラッパークラス（Integer, Long, Character, Stringなど）は、switch文で使用することができます。
```java
switch (変数) {
    case 値1:
        // 変数が値1の場合に実行される処理
        break;
    case 値2:
        // 変数が値2の場合に実行される処理
        break;
    // ...
    default:
        // すべてのcaseに該当しない場合に実行される処理
}
```
- 例:
```java
int day = 3;
switch (day) {
    case 1:
        System.out.println("月曜日");
        break;
    case 2:
        System.out.println("火曜日");
        break;
    case 3:
        System.out.println("水曜日");
        break;
    case 4:
        System.out.println("木曜日");
        break;
    default:
        System.out.println("無効な曜日です。");
}   
```
### Java 14以降のswitch式
- Java 14以降では、switch文に加えてswitch式が導入されました。switch式は、switch文と同様に条件分岐を行いますが、値を返すことができるため、より柔軟な使い方が可能です。switch式の基本的な構文は以下の通りです。
```java
int result = switch (変数) {
    case 値1 -> {
        // 変数が値1の場合に実行される処理
        yield 値1の結果; // yieldを使用して値を返す
    }
    case 値2 -> {
        // 変数が値2の場合に実行される処理
        yield 値2の結果; // yieldを使用して値を返す
    }
    // ...
    default -> {
        // すべてのcaseに該当しない場合に実行される処理
        yield デフォルトの結果; // yieldを使用して値を返す
    }
};
```
- 例:
```java
int day = 3;
String dayName = switch (day) {
    case 1 -> "月曜日";
    case 2 -> "火曜日";
    case 3 -> "水曜日";
    case 4 -> "木曜日";
    default -> "無効な曜日です。";
};
System.out.println(dayName); // 出力: 水曜日
```

#レビュー済み
