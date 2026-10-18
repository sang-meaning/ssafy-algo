import java.io.*;
import java.util.*;

class Solution {
    static int N, W, H, answer;
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = fs.nextInt();
            W = fs.nextInt();
            H = fs.nextInt();

            int[][] board = new int[H][W];
            int count = 0;

            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    board[r][c] = fs.nextInt();
                    if (board[r][c] != 0) count++;
                }
            }

            answer = count;
            dfs(0, count, board);

            sb.append('#').append(tc).append(' ')
              .append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int depth, int remaining, int[][] board) {
        if (answer == 0) return;

        if (remaining == 0) {
            answer = 0;
            return;
        }

        if (depth == N) {
            answer = Math.min(answer, remaining);
            return;
        }

        for (int col = 0; col < W; col++) {
            int row = 0;
            while (row < H && board[row][col] == 0) {
                row++;
            }

            if (row == H) continue;

            int[][] next = copy(board);
            int broken = explode(next, row, col);

            applyGravity(next);
            dfs(depth + 1, remaining - broken, next);

            if (answer == 0) return;
        }
    }

    static int explode(int[][] board, int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
            startR, startC, board[startR][startC]
        });

        board[startR][startC] = 0;
        int broken = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int power = current[2];

            for (int d = 0; d < 4; d++) {
                for (int distance = 1; distance < power; distance++) {
                    int nr = r + DR[d] * distance;
                    int nc = c + DC[d] * distance;

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        break;
                    }

                    if (board[nr][nc] == 0) continue;

                    int nextPower = board[nr][nc];
                    board[nr][nc] = 0;
                    broken++;

                    if (nextPower > 1) {
                        queue.offer(new int[]{nr, nc, nextPower});
                    }
                }
            }
        }

        return broken;
    }

    static void applyGravity(int[][] board) {
        for (int c = 0; c < W; c++) {
            int writeRow = H - 1;

            for (int r = H - 1; r >= 0; r--) {
                if (board[r][c] != 0) {
                    int value = board[r][c];
                    board[r][c] = 0;
                    board[writeRow--][c] = value;
                }
            }

            while (writeRow >= 0) {
                board[writeRow--][c] = 0;
            }
        }
    }

    static int[][] copy(int[][] board) {
        int[][] result = new int[H][W];

        for (int r = 0; r < H; r++) {
            result[r] = board[r].clone();
        }

        return result;
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int value = 0;
            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }
            return value;
        }
    }
}