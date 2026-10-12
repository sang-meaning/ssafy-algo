import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, C;
    static int[][] map;
    static int[][] profit;

    static int maxProfit;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            profit = new int[N][N];

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c <= N - M; c++) {

                    maxProfit = 0;

                    dfs(r, c, 0, 0, 0);

                    profit[r][c] = maxProfit;
                }
            }

            int answer = 0;

            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {

                    for (int r2 = r1; r2 < N; r2++) {
                        for (int c2 = 0; c2 <= N - M; c2++) {

                            if (r1 == r2 && c1 == c2) {
                                continue;
                            }

                            if (r1 == r2) {

                                boolean overlap =
                                        !(c1 + M <= c2 || c2 + M <= c1);

                                if (overlap) {
                                    continue;
                                }
                            }

                            int sum = profit[r1][c1] + profit[r2][c2];

                            answer = Math.max(answer, sum);
                        }
                    }
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int r, int c, int idx, int sum, int value) {
        if (sum > C) {
            return;
        }

        if (idx == M) {
            maxProfit = Math.max(maxProfit, value);
            return;
        }

        int honey = map[r][c + idx];

        dfs(
            r,
            c,
            idx + 1,
            sum + honey,
            value + honey * honey
        );

        dfs(
            r,
            c,
            idx + 1,
            sum,
            value
        );
    }
}