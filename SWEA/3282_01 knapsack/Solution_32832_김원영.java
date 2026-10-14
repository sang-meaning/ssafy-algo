import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            int[][] dp = new int[N+1][K+1];

            for(int i=1; i<=N; i++) {
                int V = sc.nextInt();
                int C = sc.nextInt();

                for(int j=0; j<=K; j++) {
                    if(j < V) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-V]+C);
                    }
                }
            }

            System.out.println("#"+tc+" "+dp[N][K]);
        }
    }
}