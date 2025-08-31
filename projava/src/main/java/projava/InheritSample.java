package projava;

import java.util.List;

public class InheritSample {
    static abstract class User {
        String name;    //UserクラスのnameというフィールドをString型で定義

        User(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }

        abstract String profile();

        @Override
        public String toString(){
            return profile();
        }
    }

    static class Student extends User{
        int score;

        Student(String name, int score){
            super(name);    //Userクラスのnameを引数で受け取る
            this.score = score;
        }

        public int getScore(){
            return score;
        }

        @Override
        String profile(){
            return "学生 %s, %d点".formatted(getName(),getScore());
        }
    }

    static class Teacher extends User {
        String subject;

        Teacher(String name, String subject){
            super(name);    //Userクラスのnameを引数で受け取る
            this.subject = subject;
        }

        public String getSubject(){
            return subject;
        }

        @Override
        String profile(){
            return "先生 %s, %s".formatted(getName(),getSubject());
        }
    }

    public static void main(String[] args){
        List<User> people = List.of(    //Userクラスを継承したStudentとTeacherクラスをListに格納
            new Student("kis",80),
            new Teacher("hosoya","Math"));
        for(var p: people){
            System.out.println("こんにちは%sさん".formatted(p.getName()));
            System.out.println(p);
        }
    }
}
