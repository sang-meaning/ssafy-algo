import java.util.*;
import java.io.*;

public class Solution_1494_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T,N;
    static int[] x,y;
    static long totalX, totalY, ans;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        /*
        10개 점 고르는 조합 20C10 완탐
        (고정(머무는) 지렁이 좌표 합) - (출발(움직이는) 좌표 합) = 전체 합 - 2(움직이는 좌표 합)
        */
        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            x = new int[N];
            y = new int[N];
            totalX = 0;
            totalY = 0;

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
                totalX += x[i];
                totalY += y[i];
            }

            ans = Long.MAX_VALUE;
            dfs(0,0,0,0);

            sb.append("#"+t+" "+ans).append("\n");
        }

        System.out.println(sb);
    }

    // idx : 현재 지렁이, cnt : 고른 지렁이 수, sx sy : 
    static void dfs(int idx, int cnt, long sx, long sy) {
        if (cnt == N/2) {
            // 종료 조건
            long tx = totalX - 2*sx;
            long ty = totalY - 2*sy;
            ans = Math.min(ans, tx*tx + ty*ty);
            return;
        }

        if (idx == N) return;
        

        dfs(idx+1, cnt+1, sx+x[idx], sy+y[idx]);
        dfs(idx+1, cnt, sx, sy);
    }
}
