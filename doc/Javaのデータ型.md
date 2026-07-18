# Javaのデータ型
## リテラル
### 文字列リテラル
#### エスケープシーケンス
- 改行やタブ、バックスペースを出力するためのもの

| シーケンス | 意味 |
|:---:|:---|
| `\n` | 改行 |
| `\t` | 水平タブ |
| `\b` | バックスペース |
| `\r` | キャリッジリターン |
| `\\` | バックスラッシュ |
| `\'` | シングルクォート |
| `\"` | ダブルクォート |

## データ型
- Javaはコンパイル時に型が決定する静的型付け言語
- 変数宣言を行う際にはデータ型を明示的に記載しておく必要がある
- 以下の２種類が存在する
  - プリミティブ型（基本データ型）
  - 参照型
### 基本データ型の宣言
```java
データ型 変数名;
// 初期値とセットで宣言する場合
データ型 変数名 = 初期値;
```
### 定数の宣言
```java
final データ型 定数名 = 初期値;
```
- 変数宣言に`final`修飾子を付けると、一度初期化した値の変更ができなくなる
- 定数名はすべて大文字で記述する。複数の単語を使用する場合は`_`で単語を区切る

### 参照型の宣言
- データ型名の最初の1文字が大文字になる
- 例
```java
String man = "男性";
String female = "女性";
```
- 基本データ型とデータの持ち方が違う
  - 基本データ型：変数宣言でデータ型に応じたメモリを確保しそこに値を格納
  - 参照型：変数宣言でデータ型に応じたメモリを確保し別の場所に値を格納、変数には参照先を格納
- Stringのオブジェクトはイミュータブルのため保持する文字列の変更ができない

### 配列の宣言
```java
// 基本構文
データ型[] 配列名 = new データ型[要素数];

// 例
int[] ary = new int[3];
int ary[] = new int[3];
int[] ary = new int[]{8,11,17};
int[] ary = {8,11,17};
String[] ary_str = {"太郎","次郎","三郎"};
```
- 要素をデフォルト値で初期化していない場合、データ型によってデフォルトの初期値が代入される
- データ型に応じた配列の初期値

|データ型|値|
|:---:|:---:|
|byte,short,int,long|0|
|float,double|0.0|
|char|¥u0000|
|boolean|false|
|参照型|null|

## コマンドライン引数によるJavaの実行
- `java クラス名 引数1 引数2` のように実行時に引数を渡せる
- mainメソッドの`String[] args`に格納され、`args[0]`、`args[1]`のようにインデックスでアクセスする
- 引数はすべてString型として渡される

```java
public class Main {
  public static void main(String[] args) {
    System.out.println(args[0]); // 第1引数を出力
    System.out.println(args[1]); // 第2引数を出力
  }
}
```

## Stringクラスのメソッド

| メソッド | 説明 |
|:---|:---|
| `length()` | 文字列の長さを返す |
| `charAt(int index)` | 指定インデックスの文字を返す |
| `substring(int begin, int end)` | 部分文字列を返す（endは含まない） |
| `indexOf(String str)` | 指定文字列の最初のインデックスを返す（見つからない場合は-1） |
| `contains(String str)` | 指定文字列を含むか判定する |
| `equals(String str)` | 文字列が等しいか判定する（大文字・小文字を区別） |
| `equalsIgnoreCase(String str)` | 大文字・小文字を区別せず等しいか判定する |
| `toUpperCase()` | 文字列を大文字に変換する |
| `toLowerCase()` | 文字列を小文字に変換する |
| `trim()` | 先頭・末尾の空白を削除する |
| `replace(String old, String new)` | 文字列を置換する |
| `split(String regex)` | 正規表現で分割しString配列を返す |

## StringBuilderクラス
- Stringクラスと異なりミュータブル（可変）なオブジェクト
- 初期容量16文字分を確保（初期値を何も設定していない場合）
### StringBuilderクラスのメソッド

| メソッド | 説明 |
|:---|:---|
| `append(String str)` | 末尾に文字列を追加する |
| `insert(int offset, String str)` | 指定インデックスに文字列を挿入する |
| `delete(int start, int end)` | 指定範囲の文字列を削除する（endは含まない） |
| `replace(int start, int end, String str)` | 指定範囲の文字列を置換する |
| `reverse()` | 文字列を逆順にする |
| `substring(int start, int end)` | 部分文字列をString型で返す |
| `toString()` | StringBuilderの内容をString型に変換する |
| `length()` | 文字列の長さを返す |

### StringBuilderとStringの併用時の注意
```java
public class Main{
  public static void main(String[] args){
    StringBuilder sb = new StringBuilder();
    sb.append("佐藤").append("太郎"); // sbの中身 佐藤太郎
    sb.insert(4," ").delete(4,5); // sbの中身　佐藤 太郎　→　佐藤太郎
    
    sb.substring(0,4); //戻り値がString型となるので、sbとは違うオブジェクトが生成される
    System.out.println(sb); // ⬅️ 佐藤太郎
  }
}
```

## メソッドチェーン
- メソッドの戻り値に対して続けてメソッドを呼び出す記法
- コードを簡潔に記述できる

```java
// メソッドチェーンなし
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" World");

// メソッドチェーンあり（同じ結果）
StringBuilder sb = new StringBuilder();
sb.append("Hello").append(" World");
```

## テキストブロック
- 開始`"""`の直後に文字列は書けない
- 終了`"""`の直前に文字列は書ける
- エスケープはしてもしなくても問題ない
- テキストブロック内の改行をエスケープしたい場合は`\`
- `System.out.println()`中でもテキストブロックは使用できる

```java
// テキストブロックの例
String text = """
    Hello,
    World!
    """;
System.out.println(text);
```

#レビュー済み
