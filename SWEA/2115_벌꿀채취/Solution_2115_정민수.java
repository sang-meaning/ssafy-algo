import java.util.*;

public class Solution {

    static int N, M, C;
    static int[][] map;
    static int[][] profit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();

            map = new int[N][N];
            profit = new int[N][N - M + 1];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    profit[i][j] = getProfit(i, j);
                }
            }

            int answer = 0;

            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {

                    for (int r2 = r1; r2 < N; r2++) {
                        for (int c2 = 0; c2 <= N - M; c2++) {

                            if (r1 == r2 && c2 < c1 + M) {
                                continue;
                            }

                            if (r1 == r2 && c1 == c2) {
                                continue;
                            }

                            answer = Math.max(
                                answer,
                                profit[r1][c1] + profit[r2][c2]
                            );
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static int getProfit(int row, int col) {
        int max = 0;

        for (int mask = 0; mask < (1 << M); mask++) {
            int sum = 0;
            int value = 0;

            for (int i = 0; i < M; i++) {
                if ((mask & (1 << i)) != 0) {
                    int honey = map[row][col + i];

                    sum += honey;
                    value += honey * honey;
                }
            }

            if (sum <= C) {
                max = Math.max(max, value);
            }
        }

        return max;
    }
}