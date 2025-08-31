package projava;

public class SwitchSample {
    public static void main(String[] args) {
        int number = 10;
        switch (number) {
            case 1:
            case 2:
                System.out.println("one-two");    
                break;
            case 3:
                System.out.println("three");
                break;
            case 4:
                System.out.println("four");
                break;
            case 5:
                System.out.println("five");
                break;
            default:
                System.out.println("other");
                break;
        }
        System.out.println(switch (number){
            case 1,2 -> "one-two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            default -> "other";
        });
    }
    
}
