package org.example;

/**
 * 参照型の代入3
 * オブジェクトを別の変数に代入した場合も参照渡しになる
 * 2つの変数が同じオブジェクトを参照するため、片方を変更するともう片方にも影響が出る
 */
public class Main_Ref_data3 {
    public static void main(String[] args){
        // オブジェクトを生成してobj1に代入
        Person obj1 = new Person("田中", 20);

        // obj1をobj2に代入 → 同じオブジェクトへの参照が渡される（コピーではない）
        Person obj2 = obj1;

        System.out.println("変更前 obj1：" + obj1.name + "、" + obj1.age + "歳");
        System.out.println("変更前 obj2：" + obj2.name + "、" + obj2.age + "歳");

        // obj2のフィールドを変更する
        obj2.name = "佐藤";
        obj2.age = 30;

        // obj1とobj2は同じオブジェクトを参照しているため、obj1も変更されている
        System.out.println("変更後 obj1：" + obj1.name + "、" + obj1.age + "歳");
        System.out.println("変更後 obj2：" + obj2.name + "、" + obj2.age + "歳");
    }
}

// サンプル用のクラス
class Person {
    String name;
    int age;

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}