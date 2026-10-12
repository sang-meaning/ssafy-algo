import java.io.*;
import java.util.*;

public class Solution {
    static int N, B;
    static int[] heights;
    static int minDiff; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 점원 수
            B = Integer.parseInt(st.nextToken()); // 선반 높이

            heights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            minDiff = Integer.MAX_VALUE;

            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int idx, int sum) {

        if (sum >= B) {
            minDiff = Math.min(minDiff, sum - B);
            return;
        }

        if (sum - B >= minDiff) {
            return;
        }

        if (idx == N) {
            return;
        }

        dfs(idx + 1, sum + heights[idx]);

        dfs(idx + 1, sum);
    }
}