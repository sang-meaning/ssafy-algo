import java.io.*;
import java.util.*;

class Solution {
    static int N, ans;
    static int sx, sy, ex, ey;
    static int[] x, y;
    static boolean[] visited;

    static void dfs(int cx, int cy, int cnt, int sum) {
        if(sum >= ans) return; // 가지치기

        if(cnt == N) {
            sum += Math.abs(cx - ex) + Math.abs(cy - ey);
            ans = Math.min(ans, sum);
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;

            visited[i] = true;

            int dist = Math.abs(cx - x[i]) + Math.abs(cy - y[i]);
            dfs(x[i], y[i], cnt + 1, sum + dist);

            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            x = new int[N];
            y = new int[N];
            visited = new boolean[N];

            for(int i=0; i<N; i++) {
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;

            dfs(sx, sy, 0, 0);

            sb.append("#").append(tc).append(" ")
              .append(ans).append("\n");
        }

        System.out.print(sb);
    }
}