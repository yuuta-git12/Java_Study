package projava;

public class ClassSample {
    static final class Student{
        // finalは変更不可なフィールドを宣言するためのキーワード
        // フィールドはprivateで宣言する
        // コンストラクタでフィールドを初期化する
        // getterでフィールドを取得する
        private final String name;  // 名前 フィールド
        private final int score;    // 点数 フィールド

        Student(String name, int score){    // コンストラクタ
            this.name = name;
            this.score = score;
        }

        public String getName(){    // 名前を取得  getter
            return name;
        }

        public int getScore(){    // 点数を取得  getter
            return score;
        }
    }
}
