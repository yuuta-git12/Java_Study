package org.example;

/**
 * 基本データ型の代入
 */
public class Main_base_data {
    public static void main(String[] args){
        int val1 = 100;
        int val2 = val1;
        val2 = 200;

        Main_base_data m = new Main_base_data();    // オブジェクトの生成
        m.methodA(val2);
        System.out.println("val1：" + val1);
        System.out.println("val2：" + val2);
    }

    public void methodA(int val3){
        val3 += val3;
        System.out.println("val3：" + val3);
    }
}
