import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static long[] x, y;
    static long totalX, totalY, ans;

    static void dfs(int start, int cnt, long sx, long sy) {
        if(cnt == N / 2) {
            long vx = 2 * sx - totalX;
            long vy = 2 * sy - totalY;

            ans = Math.min(ans, vx * vx + vy * vy);
            return;
        }

        // 남은 개수로 N/2개를 채울 수 없으면 종료
        if(N - start < N / 2 - cnt) return;

        for(int i=start; i<N; i++) {
            dfs(i + 1, cnt + 1, sx + x[i], sy + y[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            x = new long[N];
            y = new long[N];

            totalX = 0;
            totalY = 0;

            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                x[i] = Long.parseLong(st.nextToken());
                y[i] = Long.parseLong(st.nextToken());

                totalX += x[i];
                totalY += y[i];
            }

            ans = Long.MAX_VALUE;

            dfs(0, 0, 0, 0);

            sb.append("#").append(tc).append(" ")
              .append(ans).append("\n");
        }

        System.out.print(sb);
    }
}