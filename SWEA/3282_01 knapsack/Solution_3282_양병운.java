import java.util.*;
import java.io.*;

class Solution {
    static int max, N, K;
    static int[] dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            dp = new int[K+1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int volume = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                for(int j=K; j>=volume; j--) dp[j] = Math.max(dp[j], dp[j-volume]+value);
            }
            System.out.println("#" + test_case + " " + dp[K]);
        }
    }
}