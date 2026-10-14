package algo_prac;


import java.io.*;
import java.util.*;

class Solution_3307_이승규 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
        	sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            int[] dp = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                dp[i] = 1;
            }

            int answer = 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                answer = Math.max(answer, dp[i]);
            }

            sb.append("#").append(test_case).append(" ").append(answer);
            System.out.println(sb);
        }
    }
}
