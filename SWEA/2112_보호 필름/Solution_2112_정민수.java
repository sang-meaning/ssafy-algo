import java.util.Scanner;

class Solution {
    static int D, W, K;
    static int[][] film;
    static int min;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();

            film = new int[D][W];

            for (int i = 0; i < D; i++) {
                for (int j = 0; j < W; j++) {
                    film[i][j] = sc.nextInt();
                }
            }

            if (K == 1 || check()) {
                System.out.println("#" + test_case + " 0");
                continue;
            }

            min = K;

            dfs(0, 0);

            System.out.println("#" + test_case + " " + min);
        }

        sc.close();
    }

    static void dfs(int row, int count) {
        if (count >= min) {
            return;
        }

        if (check()) {
            min = count;
            return;
        }

        if (row == D) {
            return;
        }

        dfs(row + 1, count);

        int[] backup = film[row].clone();

        for (int col = 0; col < W; col++) {
            film[row][col] = 0;
        }

        dfs(row + 1, count + 1);

        for (int col = 0; col < W; col++) {
            film[row][col] = 1;
        }

        dfs(row + 1, count + 1);

        film[row] = backup;
    }

    static boolean check() {
        for (int col = 0; col < W; col++) {
            int count = 1;
            int max = 1;

            for (int row = 1; row < D; row++) {
                if (film[row][col] == film[row - 1][col]) {
                    count++;
                    max = Math.max(max, count);
                } else {
                    count = 1;
                }

                if (max >= K) {
                    break;
                }
            }

            if (max < K) {
                return false;
            }
        }

        return true;
    }
}