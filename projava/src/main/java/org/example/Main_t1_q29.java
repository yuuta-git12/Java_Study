package org.example;

// javac Main_t1_q29.java の場合: コンパイルエラーになる
//
//   Main_t1_q29.java:9: エラー: 複数catchパラメータeに値を代入することはできません
//               e = new NullPointerException("Wrong operator");
//
// 理由:
//   マルチキャッチ（catch (TypeA | TypeB | TypeC e)）の catch パラメータは
//   暗黙的に final として扱われるため、キャッチブロック内で e に再代入するとコンパイルエラーになる。
//   一方、単一の型を指定した catch(Exception exception) の exception は
//   暗黙的final ではないため、11行目の "exception = new Exception(...)" 自体は合法
//   （ただし再代入した値はどこにも使われないので実質無意味）。
public class Main_t1_q29 {
    public static void main(String[] args){
        try{
            String text = args[0].trim();
            int length = text.length();
        }catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e){
            e = new NullPointerException("Wrong operator");
        }catch(Exception exception){
            exception = new Exception("Unknown error");
        }
    }
}
