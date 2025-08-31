package projava;

import java.util.List;

public class InterfaceSample {

    @FunctionalInterface
    interface Named{
        String name();
        default String greeting(){
            return "Hello, " + name();
        }
    }
    static void message(Named named){ //Namedインターフェースを実装したクラスを引数に取る
        System.out.println("Hello, " + named.name() + named.greeting());
    }

    record Student(String name, int score) implements Named{}
    record Teacher(String name, String subject) implements Named{}
    record Staff(String name, String job) implements Named{}

    public static void main(String[] args){
        var people = List.of(new Student("kis", 80)
        ,new Teacher("hosoya", "Math")
        ,new Staff("kawasaki", "Engineer")
        ,new Passenger());
        
        for(var p: people){
            System.out.println("こんばんは%sさん".formatted(p.name()));
        }
        message(()->"no name"); //ラムダ式でNamedを実装したクラスを作成
        message(new Student("kis", 80)); //Studentクラスを作成
    }

    static class Passenger implements Named{
        @Override
        public String name(){
            return "名無し";
        }
    }
}
