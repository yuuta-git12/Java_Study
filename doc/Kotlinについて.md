# Kotlinについて
## Kotlinとは？
- KotlinはJetBrainsが開発した言語
- JVM（Java Virtual Machine）上で動くプログラミング言語
- Javaと非常に高い互換性を持っている
- Javaで作られたライブラリをKotlinから利用可
- **Androidアプリを新規開発するならKotlin一択といった状況**

## Javaよりも簡潔にコードを記述できる
- Javaでの記述
```java
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```
- 上記をKotlinで記述した場合
```java
class User(val name: String)
```

## JavaとKotlinの関係
```
                 JVM
                  │
        ┌─────────┴─────────┐
        │                   │
      Java                Kotlin
        │                   │
        └───────┬───────────┘
                │
        Javaライブラリ
        Spring
        各種JVMライブラリ
```
- Kotlinは「Javaの後継」というより、Javaと同じJVMエコシステムを利用できる別の言語
