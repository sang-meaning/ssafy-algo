import java.util.Scanner;

public class Solution {
    static int N, cnt;
    static boolean[] col, subDiagonal, mainDiagonal;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            cnt = 0;

            col = new boolean[N + 1];
            mainDiagonal = new boolean[2 * N + 1];
            subDiagonal = new boolean[2 * N + 1];

            setQueen(1);

            System.out.println("#" + tc + " " + cnt);
        }

        sc.close();
    }

    static void setQueen(int row) {
        if (row > N) {
            cnt++;
            return;
        }

        for (int c = 1; c <= N; c++) {
            if (col[c] || mainDiagonal[row - c + N] || subDiagonal[row + c]) {
                continue;
            }

            col[c] = true;
            mainDiagonal[row - c + N] = true;
            subDiagonal[row + c] = true;

            setQueen(row + 1);

            col[c] = false;
            mainDiagonal[row - c + N] = false;
            subDiagonal[row + c] = false;
        }
    }
}