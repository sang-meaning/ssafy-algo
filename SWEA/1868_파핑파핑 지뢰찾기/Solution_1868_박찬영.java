import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static char[][] board;
    static int[][] countMine;
    static boolean[][] visited;
    
    // 8방향 탐색 델타 배열 (상하좌우 + 대각선 4개)
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            board = new char[n][n];

            for (int i = 0; i < n; i++) {
                board[i] = br.readLine().toCharArray();
            }

            countMine = new int[n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (board[r][c] == '.') {
                        int count = 0;
                        for (int d = 0; d < 8; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == '*') {
                                count++;
                            }
                        }
                        countMine[r][c] = count;
                    }
                }
            }

            visited = new boolean[n][n];
            int clicks = 0;

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (board[r][c] == '.' && !visited[r][c] && countMine[r][c] == 0) {
                        clicks++;
                        bfs(r, c);
                    }
                }
            }

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (board[r][c] == '.' && !visited[r][c]) {
                        clicks++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(clicks).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int startR, int startC) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(startR * 1000 + startC);
        visited[startR][startC] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            int r = curr / 1000;
            int c = curr % 1000;

            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == '.' && !visited[nr][nc]) {
                    visited[nr][nc] = true; 

                    if (countMine[nr][nc] == 0) {
                        q.offer(nr * 1000 + nc);
                    }
                }
            }
        }
    }
}