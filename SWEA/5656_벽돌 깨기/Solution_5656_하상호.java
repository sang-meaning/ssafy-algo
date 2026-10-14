package cording_prac;


import java.io.*;
import java.util.*;

public class Solution_5656_하상호 {

    static int N, W, H;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[][] copy(int[][] board) {
        int[][] next = new int[H][W];

        for (int r = 0; r < H; r++) {
            next[r] = board[r].clone();
        }

        return next;
    }

    static int explode(int[][] board, int col) {
        int row = 0;

        while (row < H && board[row][col] == 0) {
            row++;
        }

        if (row == H) return 0;

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
            row, col, board[row][col]
        });

        board[row][col] = 0;
        int removed = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int power = cur[2];

            for (int d = 0; d < 4; d++) {
                for (int dist = 1; dist < power; dist++) {

                    int nr = r + dr[d] * dist;
                    int nc = c + dc[d] * dist;

                    if (nr < 0 || nr >= H ||
                        nc < 0 || nc >= W) {
                        break;
                    }

                    if (board[nr][nc] == 0) continue;

                    queue.offer(new int[]{
                        nr, nc, board[nr][nc]
                    });

                    board[nr][nc] = 0;
                    removed++;
                }
            }
        }

        return removed;
    }

    static void gravity(int[][] board) {

        for (int c = 0; c < W; c++) {
            int write = H - 1;

            for (int r = H - 1; r >= 0; r--) {

                if (board[r][c] != 0) {
                    board[write][c] = board[r][c];
                    write--;
                }
            }

            while (write >= 0) {
                board[write][c] = 0;
                write--;
            }
        }
    }

    static void dfs(int depth, int remain, int[][] board) {

        if (remain == 0) {
            answer = 0;
            return;
        }

        if (depth == N) {
            answer = Math.min(answer, remain);
            return;
        }

        for (int col = 0; col < W; col++) {

            if (answer == 0) return;

            int row = 0;

            while (row < H && board[row][col] == 0) {
                row++;
            }

            if (row == H) continue;

            int[][] next = copy(board);

            int removed = explode(next, col);

            gravity(next);

            dfs(depth + 1, remain - removed, next);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] board = new int[H][W];
            int remain = 0;

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < W; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());

                    if (board[r][c] != 0) {
                        remain++;
                    }
                }
            }

            answer = remain;

            dfs(0, remain, board);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
