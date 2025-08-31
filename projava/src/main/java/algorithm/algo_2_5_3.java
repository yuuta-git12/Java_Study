package algorithm;

import java.util.Scanner;

public class algo_2_5_3 {
    public static void main(String[] args) {
        // long result = 1;
        // for(int i = 20; i>=1; i--){
        //     result = result * i;
        // }
        // System.out.println(result);

        Scanner sc = new Scanner(System.in);

        // 入力
        long N = sc.nextInt();

        // 答えの計算
        long result = 1;
        for(int i = 1; i<=N; i++){
            result *= i;
        }
        System.out.println(result);

        sc.close();

    }
}
