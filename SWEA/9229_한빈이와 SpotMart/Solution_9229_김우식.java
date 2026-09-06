import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int[] snacks;
    static int max;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            snacks = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }

            max = -1;

            dfs(0, 0, 0);

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(max)
                    .append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int idx, int count, int sum) {

        // 과자를 정확히 2개 골랐으면
        if (count == 2) {

            if (sum <= M) {
                max = Math.max(max, sum);
            }

            return;
        }

        // 모든 과자를 다 봤으면 종료
        if (idx == N) {
            return;
        }

        // 현재 과자 선택
        dfs(idx + 1, count + 1, sum + snacks[idx]);

        // 현재 과자 선택 안 함
        dfs(idx + 1, count, sum);
    }
}