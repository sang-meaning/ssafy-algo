import java.util.*;

class Solution {
    static class State {
        int r, c, direction, distance;

        State(int r, int c, int direction, int distance) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.distance = distance;
        }
    }

    int n;
    int[][] board;
    boolean[][][] visited;
    Queue<State> queue;

    boolean empty(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == 0;
    }

    void add(int r, int c, int direction, int distance) {
        int r2 = r + (direction == 1 ? 1 : 0);
        int c2 = c + (direction == 0 ? 1 : 0);

        if (!empty(r, c) || !empty(r2, c2)) return;
        if (visited[r][c][direction]) return;

        visited[r][c][direction] = true;
        queue.add(new State(r, c, direction, distance));
    }

    public int solution(int[][] board) {
        this.board = board;
        this.n = board.length;
        visited = new boolean[n][n][2];
        queue = new ArrayDeque<>();

        add(0, 0, 0, 0);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            State now = queue.poll();

            int otherR = now.r + (now.direction == 1 ? 1 : 0);
            int otherC = now.c + (now.direction == 0 ? 1 : 0);

            if ((now.r == n - 1 && now.c == n - 1)
                    || (otherR == n - 1 && otherC == n - 1)) {
                return now.distance;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                add(now.r + dr[i], now.c + dc[i],
                    now.direction, now.distance + 1);
            }

            if (now.direction == 0) {
                // 가로 → 세로: 위쪽 또는 아래쪽 두 칸이 모두 비어 있어야 함
                for (int d : new int[]{-1, 1}) {
                    if (empty(now.r + d, now.c)
                            && empty(now.r + d, now.c + 1)) {
                        int newR = Math.min(now.r, now.r + d);
                        add(newR, now.c, 1, now.distance + 1);
                        add(newR, now.c + 1, 1, now.distance + 1);
                    }
                }
            } else {
                // 세로 → 가로: 왼쪽 또는 오른쪽 두 칸이 모두 비어 있어야 함
                for (int d : new int[]{-1, 1}) {
                    if (empty(now.r, now.c + d)
                            && empty(now.r + 1, now.c + d)) {
                        int newC = Math.min(now.c, now.c + d);
                        add(now.r, newC, 0, now.distance + 1);
                        add(now.r + 1, newC, 0, now.distance + 1);
                    }
                }
            }
        }

        return -1;
    }
}