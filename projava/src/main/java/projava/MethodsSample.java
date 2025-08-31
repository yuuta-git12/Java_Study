package projava;

public class MethodsSample {
    public static void main(String[] args) {
        var result = twice(3);
        System.out.println(result);
    }

    static int twice(int x){
        return x*2;
    }
}
