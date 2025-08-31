/*
* 人間クラス
* 人間の名前と年齢を表す
* 人間の国籍や言語を表す
* 人間の地域を表す
*/

package projava;

public class Human {    //Humanクラスを定義
    protected String name;
    protected int age;

    public Human(String name, int age){    //Humanクラスのコンストラクタを定義
        this.name = name;
        this.age = age;
    }

    public void introduce(){    //introduceメソッドを定義
        System.out.println("こんにちは、私は%sです。%d歳です。".formatted(name, age));
    }

    public static void main(String[] args){
        Asian person1 = new Asian("たかし", 30, "日本");    //Asianクラスのインスタンスを作成
        European person2 = new European("マイク", 25, "英語");    //Europeanクラスのインスタンスを作成
        Africa person3 = new Africa("ジョン", 35, "セネガル");    //Africaクラスのインスタンスを作成
    
        person1.introduce();    //introduceメソッドを呼び出す
        person2.introduce();    //introduceメソッドを呼び出す
        person3.introduce();    //introduceメソッドを呼び出す

        person1.culturalBackground();    //culturalBackgroundメソッドを呼び出す
        person2.speakLanguage();    //speakLanguageメソッドを呼び出す
        person3.describeRegion();    //describeRegionメソッドを呼び出す 
    }



}

class Asian extends Human{    //Asianクラスを定義
    private String country;    //countryフィールドを定義

    public Asian(String name, int age, String country){    //Asianクラスのコンストラクタを定義
        super(name, age);    //Humanクラスのコンストラクタを呼び出す
        this.country = country;    //countryフィールドを初期化する
    }

    public void culturalBackground(){    //culturalBackgroundメソッドを定義
        System.out.println(country + "の文化的背景を持っています。");
    }
}

class European extends Human{    //Europeanクラスを定義
    private String language;    //languageフィールドを定義

    public European(String name, int age, String language){    //Europeanクラスのコンストラクタを定義
        super(name, age);    //Humanクラスのコンストラクタを呼び出す
        this.language = language;    //languageフィールドを初期化する
    }

    public void speakLanguage(){    //speakLanguageメソッドを定義
        System.out.println("私は主に" + language + "を話します");
    }
}

class Africa extends Human{    //Africaクラスを定義
    private String region;

    public Africa(String name, int age, String region){    //Africaクラスのコンストラクタを定義
        super(name, age);    //Humanクラスのコンストラクタを呼び出す
        this.region = region;    //regionフィールドを初期化する
    }

    public void describeRegion(){    //describeRegionメソッドを定義
        System.out.println("私はアフリカの" + region + "地域出身です");
    }
}
