import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] customer;
    static boolean[] visited;

    static int homeX, homeY;
    static int min;

    static void dfs(int depth, int x, int y, int sum) {

        if (sum >= min) {
            return;
        }

        if (depth == N) {

            int distance = Math.abs(x - homeX)
                         + Math.abs(y - homeY);

            min = Math.min(min, sum + distance);
            return;
        }

        for (int i = 0; i < N; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            int distance = Math.abs(x - customer[i][0])
                         + Math.abs(y - customer[i][1]);

            dfs(depth + 1,
                customer[i][0],
                customer[i][1],
                sum + distance);

            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int companyX = Integer.parseInt(st.nextToken());
            int companyY = Integer.parseInt(st.nextToken());

            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());

            customer = new int[N][2];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
                customer[i][1] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;

            dfs(0, companyX, companyY, 0);

            sb.append("#").append(tc)
              .append(" ").append(min).append("\n");
        }

        System.out.print(sb);
    }
}
