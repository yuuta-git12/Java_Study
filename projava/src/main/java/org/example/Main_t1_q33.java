package org.example;

public class Main_t1_q33 {
    public static void main(String[] args){
        System.out.println("HelloWorld");

        Main_t1_q33 instance = new Main_t1_q33();
        instance.e();   // javac OK: インスタンスを生成すれば非staticメソッドを呼び出せる。可変長引数のみなので引数なしでも呼び出し可能
    }
    public void a(String s1,String... s2){}               // javac OK: 可変長引数が最後の引数になっている
    public void b(String... s,int i){}                    // javac NG: 可変長引数(s)の後に通常の引数(i)が続いている（可変長引数は最後でなければならない）
    public void c(var x, var... y){}                       // javac NG: varはローカル変数の型推論専用で、メソッドの仮引数の型には使用できない
    public void d(int... i, int ...j){}                    // javac NG: 可変長引数は1つのメソッドにつき1つまでしか宣言できない
    public void e(String... s){}                           // javac OK: 可変長引数のみで、引数なしでも呼び出せる
    public void f(double d1, double... d2){}               // javac OK: 可変長引数が最後の引数になっている
}

