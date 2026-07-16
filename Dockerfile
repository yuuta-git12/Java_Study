FROM eclipse-temurin:17-jdk

WORKDIR /app

# Gradle wrapper と設定ファイルをコピー
COPY projava/gradlew .
COPY projava/gradle ./gradle
COPY projava/build.gradle .

# 依存関係を事前にダウンロード（キャッシュ活用）
RUN ./gradlew dependencies --no-daemon

# ソースコードをコピー
COPY projava/src ./src

# テストを実行
CMD ["./gradlew", "test", "--no-daemon"]
