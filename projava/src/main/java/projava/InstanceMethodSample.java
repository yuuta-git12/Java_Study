package projava;

public class InstanceMethodSample {
    record Student(String name, int englishScore, int mathScore) {
        int average(){
            return (this.englishScore() + this.mathScore()) / 2;
        }

        String showResult(){
            return (this.name + "さんの平均点は" + this.average() + "です");
        }
    }

    public static void main(String[] args) {
        var kis = new Student("kis", 60, 80);
        var rook = new Student("rook", 100, 80);
        var a = kis.average();
        var str = kis.showResult();
        var str2 = rook.showResult();
        System.out.println(str);
        System.out.println(str2);
        System.out.println("平均点は%dです".formatted(a));
    }
}
