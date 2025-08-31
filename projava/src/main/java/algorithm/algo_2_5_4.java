package algorithm;

import java.util.Scanner;

public class algo_2_5_4 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // 入力
            int N = sc.nextInt();

            for(int i=2; i<=N; i++){
                if(isPrime(i) == true){
                    if(i>=3)System.out.print(" ");
                    System.out.print(i);
                }
            }
            System.out.println();
        }
    }

    static boolean isPrime(long N){
        // 2以上の整数 Nに対し、Nが素数であればtrue,素数でなければfalseを返す関数
        for(long i = 2; i <= N - 1; i++){
            if(N % i == 0){
                return false;
            }
        }
        return true;

    }
}
