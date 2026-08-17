## インタフェース
- `interface`キーワードを使用して宣言する
- アクセス修飾子は`public`かパッケージプライベートを使用
- `extends`で他のインタフェースを継承することも可能
- インターフェースはインスタンス化できない 
  - 実装(処理内容)を持たない契約だから
    - インターフェースはメソッドの「シグネチャ(名前・引数・戻り値)」だけを定義し、具体的な処理内容(メソッドボディ)を持ちません。newでインスタンスを作るということは、そのメソッドが呼ばれたときに実際に動く処理が必要ですが、インターフェース自体にはその中身がないため、オブジェクトとして成立しません。 
    - 「何をするか」ではなく「何ができるべきか」を定めるものだからインターフェースは実装クラスに対して「このメソッドを持つことを保証しなさい」という約束(契約)を示すだけです。実体を持つのは、それを implements したクラスの役目です。
- インスタンス変数は持たない

```java
// 構文
[修飾子] interface インタフェース名 [extends インタフェース]{}

// 例
public interface Test {
  // 定数は全てpublic static finalが暗黙的に付く
  int EXCELLENT = 100;
  public int VERY_GOOD = 90;
  static int GOOD = 80;
  final int AVERAGE = 70;
  
  // メソッドは全て暗黙的にpublic abstractが付く
  void foo();
  public int bar();
  abstract boolean baz();
}
```

### インタフェース内に定義できるもの
- 抽象メソッド
- 定数
- `default`メソッド
- `static`メソッド
- `private`メソッド

### インタフェースの実装
- `implements`キーワードを使用してクラスにインタフェースを実装する
- 1つのクラスで複数のインタフェースを実装できる（多重実装）
- インタフェースの抽象メソッドは全て実装（オーバーライド）する必要がある。実装しない場合は、そのクラスを抽象クラスとして宣言する必要がある
```java
// 構文 ➡️ [修飾子] class クラス名 implements インタフェース名1, インタフェース名2 {}

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }
}
```
### インターフェースの継承
- インターフェースは他のインターフェースを継承できる
- `extends`キーワードを使用する
- 1つのインターフェースが複数のインターフェースを継承することも可能（多重継承）
```java
interface Animal {
    void eat();
}

interface Bird extends Animal {
    void fly();
}

interface Machien {
    void data_load();
}

interface Robot extends Bird, Machien {
    void work();
}

public class RoboBird implements Robot {
    @Override
    public void eat() {
        System.out.println("RoboBird is eating");
    }

    @Override
    public void fly() {
        System.out.println("RoboBird is flying");
    }

    @Override
    public void data_load() {
        System.out.println("RoboBird is loading data");
    }

    @Override
    public void work() {
        System.out.println("RoboBird is working");
    }
}
```
### インターフェースのdefaultメソッド
- `default`キーワードを使用して、インターフェース内に具象メソッドを定義できる
- インターフェースの具象メソッドは、サブクラスでオーバーライドすることが可能
- インターフェースの具象メソッドは、サブクラスでオーバーライドしない場合、サブクラスはそのまま具象メソッドを利用できる
- インターフェースの具象メソッドは、サブクラスでオーバーライドする場合、`@Override`アノテーションを付与することが推奨される
```java
interface Animal {
    default void eat() {
        System.out.println("Animal is eating");
    }
}
interface Bird extends Animal {
    default void fly() {
        System.out.println("Bird is flying");
    }
}
class RoboBird implements Bird {
    @Override
    public void eat() {
        System.out.println("RoboBird is eating");
    }

    @Override
    public void fly() {
        System.out.println("RoboBird is flying");
    }
}
```
### インターフェースのstaticメソッド
- `static`キーワードを使用して、インターフェース内に静的メソッドを定義できる
- インターフェースの静的メソッドは、サブクラスでオーバーライドすることはできない
- インターフェースの静的メソッドは、インターフェース名を使用して呼び出すことができる
```java
interface Animal {
    static void sleep() {
        System.out.println("Animal is sleeping");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal.sleep(); // Animal is sleeping
    }
}
```
### インターフェースのprivateメソッド
- `private`キーワードを使用して、インターフェース内にプライベートメソッドを定義できる
- インターフェースのプライベートメソッドは、同じインターフェース内の他のメソッドからのみ呼び出すことができる
- インターフェースのプライベートメソッドは、サブクラスから呼び出すことはできない
```java
interface Animal {
    default void eat() {
        System.out.println("Animal is eating");
        sleep(); // 同じインターフェース内のprivateメソッドを呼び出す
    }
    
    private void sleep() {
        System.out.println("Animal is sleeping");
    }
}

public class Bird implements Animal {
    public void fly() {
        System.out.println("Bird is flying");
    }
}
public class Main {
    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.fly(); // Bird is flying
        // bird.sleep(); // コンパイルエラー: sleep()はprivateメソッド
        bird.eat(); // Animal is eating
    }
}
```
## シールクラス
### シールクラスの作成と使用
- `sealed`キーワードを使用して、クラスをシールクラスとして宣言する
- シールクラスは、特定のサブクラスのみが継承できるように制限することができる
- シールクラスは、`permits`キーワードを使用して、継承を許可するサブクラスを指定する
- シールクラスは、`non-sealed`キーワードを使用して、継承を許可するサブクラスを指定することもできる
```java
// シールクラスの例
public sealed class Animal permits Dog, Cat {
    public void eat() {
        System.out.println("Animal is eating");
    }
}
public final class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }
}
public non-sealed class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("Cat is eating");
    }
}
public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.eat(); // Dog is eating
        animal = new Cat();
        animal.eat(); // Cat is eating
    }
}
```
### シールクラスの利点
- シールクラスを使用することで、クラスの継承関係を制御することができ、意図しない継承を防ぐことができる
- シールクラスを使用することで、コードの可読性と保守性を向上させることができる
- シールクラスを使用することで、型安全性を向上させることができる
- シールクラスを使用することで、パターンマッチングの際に、すべてのサブクラスを網羅していることを保証できる
- シールクラスを使用することで、将来的に新しいサブクラスが追加される場合でも、既存のコードが影響を受けないようにすることができる
- シールクラスを使用することで、クラスの設計をより明確にすることができ、意図しない継承や拡張を防ぐことができる

### インターフェースのシール化
- インターフェースもシール化することができる
- `sealed`キーワードを使用して、インターフェースをシール化する
- シール化されたインターフェースは、特定のサブインターフェースのみが実装できるように制限することができる
- シール化されたインターフェースは、`permits`キーワードを使用して、実装を許可するサブインターフェースを指定する
```java
// シール化されたインターフェースの例
public sealed interface Animal permits Dog, Cat {
    void eat();
}
public final class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }
}
public non-sealed class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("Cat is eating");
    }
}
public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.eat(); // Dog is eating
        animal = new Cat();
        animal.eat(); // Cat is eating
    }
}
```