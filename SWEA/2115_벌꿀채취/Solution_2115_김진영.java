import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, C;
    static int[][] map;
    static int[][] profit;

    static int maxProfit;

    static void dfs(int r, int c, int idx, int sum, int money) {

        // 채취 가능한 꿀의 양 초과
        if (sum > C) {
            return;
        }

        // M개의 벌통을 모두 확인
        if (idx == M) {
            maxProfit = Math.max(maxProfit, money);
            return;
        }

        int honey = map[r][c + idx];

        // 현재 벌통 선택
        dfs(r, c, idx + 1,
                sum + honey,
                money + honey * honey);

        // 현재 벌통 선택 X
        dfs(r, c, idx + 1,
                sum,
                money);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            profit = new int[N][N];

            for (int r = 0; r < N; r++) {

                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 1. 각 구간의 최대 수익 계산
            for (int r = 0; r < N; r++) {
                for (int c = 0; c <= N - M; c++) {

                    maxProfit = 0;

                    dfs(r, c, 0, 0, 0);

                    profit[r][c] = maxProfit;
                }
            }

            // 2. 두 일꾼의 구간 선택
            int answer = 0;

            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {

                    // 같은 행에서 두 번째 일꾼 선택
                    for (int c2 = c1 + M; c2 <= N - M; c2++) {

                        answer = Math.max(answer,
                                profit[r1][c1] + profit[r1][c2]);
                    }

                    // 다음 행에서 두 번째 일꾼 선택
                    for (int r2 = r1 + 1; r2 < N; r2++) {
                        for (int c2 = 0; c2 <= N - M; c2++) {

                            answer = Math.max(answer,
                                    profit[r1][c1] + profit[r2][c2]);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}