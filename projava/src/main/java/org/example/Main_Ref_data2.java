package org.example;
import java.util.Arrays;

/**
 * 参照型の代入2
 * メソッドを使わず変数に配列を代入した場合も参照渡しになる
 * 2つの変数が同じ配列を参照するため、片方を変更するともう片方にも影響が出る
 */
public class Main_Ref_data2 {
    public static void main(String[] args){
        // int型の配列を初期化
        int[] array1 = {1, 2, 3};

        // array1をarray2に代入 → 値のコピーではなく、同じ配列への参照が渡される
        int[] array2 = array1;

        System.out.println("変更前 array1：" + Arrays.toString(array1));
        System.out.println("変更前 array2：" + Arrays.toString(array2));

        // array2の要素を変更する
        array2[1] = 99;

        // array1とarray2は同じ配列を参照しているため、array1も変更されている
        System.out.println("変更後 array1：" + Arrays.toString(array1));
        System.out.println("変更後 array2：" + Arrays.toString(array2));
    }
}