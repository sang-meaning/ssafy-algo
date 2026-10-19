import java.io.*;
import java.util.*;

public class Solution_5656_김현호 {
    static int shots, width, height, best;
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder answer = new StringBuilder();
        int T = fs.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            shots = fs.nextInt(); width = fs.nextInt(); height = fs.nextInt();
            int[][] board = new int[height][width];
            int remaining = 0;
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    board[r][c] = fs.nextInt();
                    if (board[r][c] != 0) remaining++;
                }
            }
            best = remaining;
            dfs(0, board, remaining);
            answer.append('#').append(tc).append(' ').append(best).append('\n');
        }
        System.out.print(answer);
    }
    static void dfs(int depth, int[][] board, int remaining) {
        best = Math.min(best, remaining);
        if (best == 0 || depth == shots) return;
        for (int c = 0; c < width; c++) {
            int r = 0;
            while (r < height && board[r][c] == 0) r++;
            if (r == height) continue;
            int[][] copy = new int[height][];
            for (int row = 0; row < height; row++) copy[row] = board[row].clone();
            int removed = explode(copy, r, c);
            gravity(copy);
            dfs(depth + 1, copy, remaining - removed);
            if (best == 0) return;
        }
    }
    static int explode(int[][] board, int row, int col) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col, board[row][col]});
        board[row][col] = 0;
        int removed = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int d = 0; d < 4; d++) {
                for (int step = 1; step < current[2]; step++) {
                    int r = current[0] + DR[d] * step;
                    int c = current[1] + DC[d] * step;
                    if (r < 0 || r >= height || c < 0 || c >= width) break;
                    if (board[r][c] == 0) continue;
                    queue.offer(new int[]{r, c, board[r][c]});
                    // 큐에 넣을 때 제거해 중복 폭발을 막는다.
                    board[r][c] = 0; removed++;
                }
            }
        }
        return removed;
    }
    static void gravity(int[][] board) {
        for (int c = 0; c < width; c++) {
            int write = height - 1;
            for (int r = height - 1; r >= 0; r--) {
                if (board[r][c] != 0) {
                    int value = board[r][c];
                    board[r][c] = 0;
                    board[write--][c] = value;
                }
            }
        }
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr, len;
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer); ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, value = 0, sign = 1;
            do { c = read(); } while (c <= 32 && c != -1);
            if (c == -1) throw new EOFException();
            if (c == '-') { sign = -1; c = read(); }
            while (c > 32 && c != -1) {
                value = value * 10 + c - '0'; c = read();
            }
            return value * sign;
        }
    }
}
