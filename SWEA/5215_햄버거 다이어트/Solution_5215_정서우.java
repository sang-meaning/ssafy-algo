import java.util.*;
import java.io.*;

public class Solution_5215_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, L;
    static int[][] TK;
    static boolean[] vis;
    static int res;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            TK = new int[N][2];
            vis = new boolean[N];
            res = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                TK[i][0] = Integer.parseInt(st.nextToken());
                TK[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0, 0, 0);

            sb.append('#').append(tc).append(' ').append(res).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int depth, int good, int cal, int idx) {
        if (cal <= L) {
            res = Math.max(res, good);
        }

        if (depth == N || cal > L)
            return;

        for (int i = idx; i < N; i++) {
            if (vis[i])
                continue;
            vis[i] = true;

            int good2 = good + TK[i][0];
            int cal2 = cal + TK[i][1];

            dfs(depth + 1, good2, cal2, idx + 1);
            vis[i] = false;
        }
    }
}
