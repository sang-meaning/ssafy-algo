import java.io.*;
import java.util.*;

public class Solution_2115_정서우 {
    static int N, M, C;
    static int[][] map;
    static int[][] profit;
    static int maxHoneyProfit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 각 위치에서 시작하는 M개 벌통의 최대 이익 계산
            profit = new int[N][N - M + 1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    maxHoneyProfit = 0;
                    subset(i, j, 0, 0, 0);
                    profit[i][j] = maxHoneyProfit;
                }
            }

            // 두 일꾼이 겹치지 않게 구간을 선택해 합의 최댓값 탐색
            int ans = 0;
            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {
                    for (int r2 = r1; r2 < N; r2++) {
                        // 같은 행이면 c1 + M 이후부터 탐색, 다른 행이면 0부터 탐색
                        int startC2 = (r1 == r2) ? c1 + M : 0;
                        for (int c2 = startC2; c2 <= N - M; c2++) {
                            int total = profit[r1][c1] + profit[r2][c2];
                            if (total > ans) {
                                ans = total;
                            }
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    // M개 중에서 합이 C를 넘지 않으면서 제곱의 합이 최대가 되는 경우 탐색
    static void subset(int r, int c, int idx, int sum, int sumSq) {
        if (sum > C) return;

        if (idx == M) {
            if (sumSq > maxHoneyProfit) {
                maxHoneyProfit = sumSq;
            }
            return;
        }

        int val = map[r][c + idx];

        // 1. 현재 벌통 선택
        subset(r, c, idx + 1, sum + val, sumSq + (val * val));
        // 2. 현재 벌통 건너뜀
        subset(r, c, idx + 1, sum, sumSq);
    }
}