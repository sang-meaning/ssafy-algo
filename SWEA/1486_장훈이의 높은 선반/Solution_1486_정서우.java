import java.util.*;
import java.io.*;

public class Solution_1486_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, B;
    static int res;
    static int[] H;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            H = new int[N];
            vis = new boolean[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            res = Integer.MAX_VALUE;

            dfs(0, 0);

            sb.append('#').append(tc).append(' ').append(res - B).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int idx, int sum) {
        if (sum > res)
            return;
        if (sum >= B) {
            res = Math.min(res, sum);
            return;
        }

        for (int i = idx; i < N; i++) {
            if (vis[i])
                continue;

            vis[i] = true;
            dfs(i + 1, sum + H[i]);
            vis[i] = false;
        }

    }
}
