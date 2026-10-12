import java.util.*;

class Solution {
    static class Node {
        int r, c, dir, time;
        Node(int r, int c, int dir, int time) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        boolean[][][] visited = new boolean[n][n][2];
        Queue<Node> q = new ArrayDeque<>();
        
        q.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int r = curr.r;
            int c = curr.c;
            int dir = curr.dir;
            int time = curr.time;

            int r2 = (dir == 0) ? r : r + 1;
            int c2 = (dir == 0) ? c + 1 : c;
            
            if ((r == n - 1 && c == n - 1) || (r2 == n - 1 && c2 == n - 1)) {
                return time;
            }

            // 1. 상하좌우 평행 이동
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nr2 = r2 + dr[i];
                int nc2 = c2 + dc[i];

                if (check(nr, nc, n, board) && check(nr2, nc2, n, board)) {
                    if (!visited[nr][nc][dir]) {
                        visited[nr][nc][dir] = true;
                        q.offer(new Node(nr, nc, dir, time + 1));
                    }
                }
            }

            // 2. 회전 이동
            if (dir == 0) { // 가로 -> 세로
                for (int d : new int[]{-1, 1}) {
                    if (check(r + d, c, n, board) && check(r + d, c + 1, n, board)) {
                        if (!visited[r][c][1]) {
                            visited[r][c][1] = true;
                            q.offer(new Node(r, c, 1, time + 1));
                        }
                        if (!visited[r + d][c + 1][1]) {
                            visited[r + d][c + 1][1] = true;
                            q.offer(new Node(r + d, c + 1, 1, time + 1));
                        }
                    }
                }
            } else { // 세로 -> 가로
                for (int d : new int[]{-1, 1}) {
                    if (check(r, c + d, n, board) && check(r + 1, c + d, n, board)) {
                        if (!visited[r][c][0]) {
                            visited[r][c][0] = true;
                            q.offer(new Node(r, c, 0, time + 1));
                        }
                        if (!visited[r + 1][c + d][0]) {
                            visited[r + 1][c + d][0] = true;
                            q.offer(new Node(r + 1, c + d, 0, time + 1));
                        }
                    }
                }
            }
        }
        return 0;
    }

    boolean check(int r, int c, int n, int[][] board) {
        return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == 0;
    }
}