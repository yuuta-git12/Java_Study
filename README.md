# Java Study

## コンテナの起動方法

```bash
# イメージをビルド
docker build -t java-study .

# コンテナを起動（テスト実行）
docker run --rm java-study
```

## Javaコードの実行方法

```bash
# コンテナ内でシェルを起動
docker run --rm -it java-study bash

# コンテナ内でコンパイル＆実行
./gradlew run --no-daemon
```

特定のクラスを実行する場合は `build.gradle` に `mainClass` を指定して実行してください。

```groovy
// build.gradle に追記
application {
    mainClass = 'projava.SwitchSample'
}
```

## テストコードの実行方法

```bash
# テストを実行（レポートをホストに出力）
docker run --rm -v "$(pwd)/reports:/app/build/reports" java-study

# レポートをブラウザで確認
open reports/tests/test/index.html
```

`reports/tests/test/index.html` にHTMLレポートが出力されます。

ローカル環境で実行する場合（`projava/` ディレクトリで）:

```bash
cd projava
./gradlew test --no-daemon
# レポート: projava/build/reports/tests/test/index.html
```
