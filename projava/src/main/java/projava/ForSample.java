package projava;

public class ForSample {
    public static void main(String[] args) {
        for(int i = 0; i < 9; i++){
            // if(i >= 3 && i <= 6){
            //     continue;
            // }
            if(i==2){
                System.out.println("finish");
                break;
            }
            System.out.println(i);
        }
    }
}
