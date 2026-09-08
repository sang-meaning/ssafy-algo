import java.util.*;

public class Solution {
    static int rowCount, colCount, requiredLength;
    static int[][] film;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            rowCount = sc.nextInt();
            colCount = sc.nextInt();
            requiredLength = sc.nextInt();

            film = new int[rowCount][colCount];

            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    film[r][c] = sc.nextInt();
                }
            }

            answer = requiredLength;
            dfs(0, 0);

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int row, int count) {
        if (count >= answer) {
            return;
        }

        if (row == rowCount) {
            if (check()) {
                answer = count;
            }
            return;
        }

        dfs(row + 1, count);

        int[] original = film[row].clone();

        Arrays.fill(film[row], 0);
        dfs(row + 1, count + 1);

        Arrays.fill(film[row], 1);
        dfs(row + 1, count + 1);

        film[row] = original;
    }

    static boolean check() {
        if (requiredLength == 1) {
            return true;
        }

        for (int c = 0; c < colCount; c++) {
            int consecutive = 1;
            boolean passed = false;

            for (int r = 1; r < rowCount; r++) {
                if (film[r][c] == film[r - 1][c]) {
                    consecutive++;
                } else {
                    consecutive = 1;
                }

                if (consecutive >= requiredLength) {
                    passed = true;
                    break;
                }
            }

            if (!passed) {
                return false;
            }
        }

        return true;
    }
}