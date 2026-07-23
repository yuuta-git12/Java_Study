package org.example;
import java.util.Arrays;

/**
 * 参照型の代入1
 * 配列は参照型で配列要素の値を保持する配列を参照している
 * 片方の配列の中身が変更されれば、他の配列に影響が発生する
 */
public class Main_Ref_data1 {
    public static void main(String[] args){
        // int型の配列を初期化（{1, 2, 3} の3要素）
        int[] array = {1,2,3};

        // インスタンスを生成してメソッドを呼び出す
        Main_Ref_data1 obj = new Main_Ref_data1();

        // arrayの参照をmethodBに渡す（値のコピーではなく参照渡し）
        obj.methodB(array);

        // methodB内でaryを変更したため、同じ配列を指すarrayも変更されている
        System.out.println("main() array：" + Arrays.toString(array));
    }

    // 引数として受け取った配列の参照を通じて、元の配列を直接変更する
    public void methodB(int[] ary){
        // ary はmain()のarrayと同じ配列を参照しているため、変更が元の配列に反映される
        ary[1] = 5;
        System.out.println("methodB ary：" + Arrays.toString(ary));
    }
}
