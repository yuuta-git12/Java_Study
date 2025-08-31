package projava;

public class IfSample {
    public static void main(String[] args) {
        // 数値を格納する変数 
        int number = 5;
        
        // 数値が3未満かどうかを判定
        if (number < 3) {
            System.out.println("小さい");
        } else if(number < 7){
            System.out.println("中くらい");
        } else {
            System.out.println("大きい");
        }
    }
}
 