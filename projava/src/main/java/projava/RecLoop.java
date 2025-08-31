package projava;

public class RecLoop  {
    public static void main(String[] args) {
        loop(3);
    }

    static void loop(int i){
        if(i <= 0){
            return;
        }
        System.out.println(i);
        loop(i-1);
    }
}
