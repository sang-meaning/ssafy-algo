import java.util.*;

class Solution {

    static int N;
    static int[][] map;
    static boolean[][][] visit;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r, c, dir, cnt;

        Node(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public int solution(int[][] board) {

        map = board;
        N = map.length;

        visit = new boolean[N][N][2];

        Queue<Node> q = new ArrayDeque<>();

        q.offer(new Node(0, 0, 0, 0));
        visit[0][0][0] = true;

        while(!q.isEmpty()) {

            Node cur = q.poll();

            int r = cur.r;
            int c = cur.c;
            int dir = cur.dir;

            // 도착
            if(dir == 0) {
                if(r == N-1 && c+1 == N-1)
                    return cur.cnt;
            }
            else {
                if(r+1 == N-1 && c == N-1)
                    return cur.cnt;
            }

            // 평행 이동
            for(int d=0; d<4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if(!can(nr, nc, dir)) continue;

                if(!visit[nr][nc][dir]) {
                    visit[nr][nc][dir] = true;
                    q.offer(new Node(nr, nc, dir, cur.cnt+1));
                }
            }

            // 가로 -> 세로 회전
            if(dir == 0) {

                // 위쪽 공간
                if(r-1 >= 0 &&
                   map[r-1][c] == 0 &&
                   map[r-1][c+1] == 0) {

                    add(q, r-1, c, 1, cur.cnt+1);
                    add(q, r-1, c+1, 1, cur.cnt+1);
                }

                // 아래쪽 공간
                if(r+1 < N &&
                   map[r+1][c] == 0 &&
                   map[r+1][c+1] == 0) {

                    add(q, r, c, 1, cur.cnt+1);
                    add(q, r, c+1, 1, cur.cnt+1);
                }
            }

            // 세로 -> 가로 회전
            else {

                // 왼쪽 공간
                if(c-1 >= 0 &&
                   map[r][c-1] == 0 &&
                   map[r+1][c-1] == 0) {

                    add(q, r, c-1, 0, cur.cnt+1);
                    add(q, r+1, c-1, 0, cur.cnt+1);
                }

                // 오른쪽 공간
                if(c+1 < N &&
                   map[r][c+1] == 0 &&
                   map[r+1][c+1] == 0) {

                    add(q, r, c, 0, cur.cnt+1);
                    add(q, r+1, c, 0, cur.cnt+1);
                }
            }
        }

        return -1;
    }

    static boolean can(int r, int c, int dir) {

        if(dir == 0) {
            if(r < 0 || r >= N ||
               c < 0 || c+1 >= N) return false;

            return map[r][c] == 0 &&
                   map[r][c+1] == 0;
        }
        else {
            if(r < 0 || r+1 >= N ||
               c < 0 || c >= N) return false;

            return map[r][c] == 0 &&
                   map[r+1][c] == 0;
        }
    }

    static void add(Queue<Node> q,
                    int r, int c, int dir, int cnt) {

        if(visit[r][c][dir]) return;

        visit[r][c][dir] = true;
        q.offer(new Node(r, c, dir, cnt));
    }
}