package swea;

import java.io.*;
import java.util.*;

public class Solution_2806_하상호 {

    static int N;
    static int answer;
    static boolean[] col;
    static boolean[] diag1;
    static boolean[] diag2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            answer = 0;

            col = new boolean[N];
            diag1 = new boolean[2 * N];
            diag2 = new boolean[2 * N];

            dfs(0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int row) {

        // 모든 행에 퀸을 놓았다면 성공
        if (row == N) {
            answer++;
            return;
        }

        for (int c = 0; c < N; c++) {

            // 같은 열
            if (col[c]) continue;

            // 오른쪽 아래 방향 대각선
            // row + col
            if (diag1[row + c]) continue;

            // 왼쪽 아래 방향 대각선
            // row - col 값이 음수가 될 수 있으므로 +N
            if (diag2[row - c + N]) continue;

            col[c] = true;
            diag1[row + c] = true;
            diag2[row - c + N] = true;

            dfs(row + 1);

            col[c] = false;
            diag1[row + c] = false;
            diag2[row - c + N] = false;
        }
    }
}
