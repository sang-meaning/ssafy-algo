import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int r=0; r<N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<N; c++) map[r][c] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;

            for(int k=1; k<=N+1; k++) {
                int cost = k*k + (k-1)*(k-1);

                for(int r=0; r<N; r++) {
                    for(int c=0; c<N; c++) {
                        int cnt = 0;

                        for(int nr=0; nr<N; nr++) {
                            for(int nc=0; nc<N; nc++) {
                                // 맨해튼 거리 < k
                                if(map[nr][nc] == 1 && Math.abs(r-nr) + Math.abs(c-nc) < k) cnt++;
                            }
                        }

                        if(cnt * M >= cost) ans = Math.max(ans, cnt);
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}