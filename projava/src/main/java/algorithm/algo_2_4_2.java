// 1からNまでの整数のうち、Xの倍数またはYの倍数であるものの個数を数えるプログラム
// 入力：N, X, Y
// 出力：1からNまでの整数のうち、Xの倍数またはYの倍数であるものの個数
// 計算回数：O(N)

package algorithm;

import java.util.Scanner;

public class algo_2_4_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        int Y = sc.nextInt();

        int cnt = 0;
        for(int i=1; i<=N; i++){
            if(i % X == 0 ||  i % Y == 0){
                cnt++;
            }
        }

        System.out.println(cnt);
        sc.close();
    }
}

