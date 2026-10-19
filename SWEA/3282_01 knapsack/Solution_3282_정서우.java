import java.io.*;
import java.util.*;

public class Solution_3282_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                for (int w = K; w >= v; w--) {
                    dp[w] = Math.max(dp[w], dp[w - v] + c);
                }
            }

            sb.append("#" + tc + " " + dp[K]).append("\n");
        }

        System.out.println(sb);
    }

}
