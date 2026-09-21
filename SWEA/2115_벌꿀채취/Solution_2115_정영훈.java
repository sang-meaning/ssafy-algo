import java.io.*;
import java.util.*;

class Solution {
    static int N, M, C;
    static int[][] honey;
    static int[][] profit;

    static void select(int row, int start, int index, int amount, int earnings) {
        if (amount > C) return;

        if (index == M) {
            profit[row][start] = Math.max(profit[row][start], earnings);
            return;
        }

        int value = honey[row][start + index];

        select(row, start, index + 1,
               amount + value, earnings + value * value);
        select(row, start, index + 1, amount, earnings);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            honey = new int[N][N];
            profit = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    honey[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c + M <= N; c++) {
                    select(r, c, 0, 0, 0);
                }
            }

            int best = 0;

            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 + M <= N; c1++) {
                    for (int r2 = r1; r2 < N; r2++) {
                        for (int c2 = 0; c2 + M <= N; c2++) {
                            if (r1 == r2 && c2 < c1 + M && c1 < c2 + M) {
                                continue;
                            }

                            best = Math.max(best,
                                profit[r1][c1] + profit[r2][c2]);
                        }
                    }
                }
            }

            answer.append('#').append(tc).append(' ').append(best).append('\n');
        }

        System.out.print(answer);
    }
}