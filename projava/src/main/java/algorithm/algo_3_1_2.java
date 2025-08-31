/*
 * 素因数分解を行うプログラム
 */
package algorithm;

import java.util.Scanner;

public class algo_3_1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 素因数分解する数Nを入力
        long N = sc.nextInt();

        // 最初の素因数を出力する際にスペースを入れないためのフラグ
        boolean flag = false;

        // 2から√Nまでの数で割っていく
        for(long i=2; i*i<=N; i++){
            // iで割り切れる限り、その数を素因数として出力
            while(N % i == 0){
                // 2つ目以降の素因数の前にスペースを入れる
                if(flag == true) System.out.print(" ");
                flag = true;
                N /= i;
                System.out.print(i);
            }
        }

        // 最後に残った数が2以上なら、それも素因数として出力
        if(N >= 2){
            if(flag == true) System.out.print(" ");
            flag = true;
            System.out.print(N);
        }

        System.out.println();

        sc.close();
    }
}
