import java.util.Scanner;

public class Solution {

    static int N;
    static int M;
    static int C;
    static int[][] map;

    static int max;
    static int honeyMax;

    static void getHoney(int row, int col, int index, int sum, int profit) {
        if(sum > C) {
            return;
        }

        if(index == M) {
            honeyMax = Math.max(honeyMax, profit);
            return;
        }

        int honey = map[row][col + index];

        getHoney(row, col, index + 1,
                sum + honey,
                profit + honey * honey);

        getHoney(row, col, index + 1, sum, profit);
    }

    static int getProfit(int row, int col) {
        honeyMax = 0;

        getHoney(row, col, 0, 0, 0);

        return honeyMax;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc = 1; tc <= T; tc++) {

            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();

            map = new int[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            max = 0;

            for(int r1 = 0; r1 < N; r1++) {
                for(int c1 = 0; c1 <= N - M; c1++) {

                    int profit1 = getProfit(r1, c1);

                    for(int r2 = r1; r2 < N; r2++) {
                        for(int c2 = 0; c2 <= N - M; c2++) {

                            if(r1 == r2) {
                                if(c2 < c1 + M) {
                                    continue;
                                }
                            }

                            int profit2 = getProfit(r2, c2);

                            max = Math.max(max, profit1 + profit2);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + max);
        }

        sc.close();
    }
}