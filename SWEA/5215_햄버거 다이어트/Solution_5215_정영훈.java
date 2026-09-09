
import java.util.*;
import java.io.*;

class Solution_5215_정영훈 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int totalCalorie = Integer.parseInt(st.nextToken());
            int[] scores = new int[n];
            int[] calories = new int[n];
            int[] dp = new int[totalCalorie + 1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < n; i++) {
                int score = scores[i];
                int cal = calories[i];
                for (int j = totalCalorie; j >= cal; j--) {
                    dp[j] = Math.max(dp[j], dp[j - cal] + score);

                }

            }
            int result = 0;
            for (int i : dp) {
                result = Math.max(result, i);
            }
            sb.append("#" + test_case + " " + result + "\n");

        }
        System.out.println(sb.toString());
    }

}