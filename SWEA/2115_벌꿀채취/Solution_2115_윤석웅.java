import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, C;
    static int[][] map, profit;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            profit = new int[N][N-M+1];

            for(int r=0; r<N; r++) {

                st = new StringTokenizer(br.readLine());

                for(int c=0; c<N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 각 M칸 구간별 최대 수익 계산
            for(int r=0; r<N; r++) {
                for(int c=0; c<=N-M; c++) {

                    int max = 0;

                    for(int mask=0; mask<(1<<M); mask++) {

                        int sum = 0;
                        int money = 0;

                        for(int i=0; i<M; i++) {

                            if((mask & (1<<i)) == 0) continue;

                            int honey = map[r][c+i];

                            sum += honey;
                            money += honey * honey;
                        }

                        if(sum <= C) {
                            max = Math.max(max, money);
                        }
                    }

                    profit[r][c] = max;
                }
            }

            int ans = 0;

            // 두 구간 선택
            for(int r1=0; r1<N; r1++) {
                for(int c1=0; c1<=N-M; c1++) {

                    for(int r2=r1; r2<N; r2++) {
                        for(int c2=0; c2<=N-M; c2++) {

                            if(r1 == r2) {

                                // 완전히 같은 구간 포함, 겹치는 경우 제외
                                if(!(c1 + M <= c2 ||
                                     c2 + M <= c1))
                                    continue;
                            }

                            ans = Math.max(
                                ans,
                                profit[r1][c1] + profit[r2][c2]
                            );
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ")
              .append(ans).append("\n");
        }

        System.out.print(sb);
    }
}