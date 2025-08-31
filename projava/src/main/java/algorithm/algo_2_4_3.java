// 計算回数:O(N*S)

package algorithm;

import java.util.Scanner;

public class algo_2_4_3 {
    public static void main(String[] args) {
        
        long start = System.nanoTime(); // 計測開始
        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int S = sc.nextInt();

        int cnt = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= S; j++){
                if(i + j <= S){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        sc.close();

        long end = System.nanoTime();   //計測終了
        long duration = end -start;
        double durationSec = duration / 1_000_000_000.0;
        System.out.print(durationSec);
    }
}
