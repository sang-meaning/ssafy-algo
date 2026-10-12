import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Node {
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
        Queue<Node> q = new LinkedList<>();
        
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!q.isEmpty()) {
            Node curr = q.poll();
            
            if (curr.dir == 0 && curr.r == n - 1 && curr.c + 1 == n - 1) return curr.time;
            if (curr.dir == 1 && curr.r + 1 == n - 1 && curr.c == n - 1) return curr.time;
            
            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                
                if (isValid(nr, nc, curr.dir, board, n) && !visited[nr][nc][curr.dir]) {
                    visited[nr][nc][curr.dir] = true;
                    q.add(new Node(nr, nc, curr.dir, curr.time + 1));
                }
            }
            
            if (curr.dir == 0) {
                if (curr.r - 1 >= 0 && board[curr.r - 1][curr.c] == 0 && board[curr.r - 1][curr.c + 1] == 0) {
                    if (!visited[curr.r - 1][curr.c][1]) {
                        visited[curr.r - 1][curr.c][1] = true;
                        q.add(new Node(curr.r - 1, curr.c, 1, curr.time + 1));
                    }
                    if (!visited[curr.r - 1][curr.c + 1][1]) {
                        visited[curr.r - 1][curr.c + 1][1] = true;
                        q.add(new Node(curr.r - 1, curr.c + 1, 1, curr.time + 1));
                    }
                }
                if (curr.r + 1 < n && board[curr.r + 1][curr.c] == 0 && board[curr.r + 1][curr.c + 1] == 0) {
                    if (!visited[curr.r][curr.c][1]) {
                        visited[curr.r][curr.c][1] = true;
                        q.add(new Node(curr.r, curr.c, 1, curr.time + 1));
                    }
                    if (!visited[curr.r][curr.c + 1][1]) {
                        visited[curr.r][curr.c + 1][1] = true;
                        q.add(new Node(curr.r, curr.c + 1, 1, curr.time + 1));
                    }
                }
            } else {
                if (curr.c - 1 >= 0 && board[curr.r][curr.c - 1] == 0 && board[curr.r + 1][curr.c - 1] == 0) {
                    if (!visited[curr.r][curr.c - 1][0]) {
                        visited[curr.r][curr.c - 1][0] = true;
                        q.add(new Node(curr.r, curr.c - 1, 0, curr.time + 1));
                    }
                    if (!visited[curr.r + 1][curr.c - 1][0]) {
                        visited[curr.r + 1][curr.c - 1][0] = true;
                        q.add(new Node(curr.r + 1, curr.c - 1, 0, curr.time + 1));
                    }
                }
                if (curr.c + 1 < n && board[curr.r][curr.c + 1] == 0 && board[curr.r + 1][curr.c + 1] == 0) {
                    if (!visited[curr.r][curr.c][0]) {
                        visited[curr.r][curr.c][0] = true;
                        q.add(new Node(curr.r, curr.c, 0, curr.time + 1));
                    }
                    if (!visited[curr.r + 1][curr.c][0]) {
                        visited[curr.r + 1][curr.c][0] = true;
                        q.add(new Node(curr.r + 1, curr.c, 0, curr.time + 1));
                    }
                }
            }
        }
        return 0;
    }
    
    boolean isValid(int r, int c, int dir, int[][] board, int n) {
        if (dir == 0) {
            if (r < 0 || r >= n || c < 0 || c + 1 >= n) return false;
            return board[r][c] == 0 && board[r][c + 1] == 0;
        } else {
            if (r < 0 || r + 1 >= n || c < 0 || c >= n) return false;
            return board[r][c] == 0 && board[r + 1][c] == 0;
        }
    }
}