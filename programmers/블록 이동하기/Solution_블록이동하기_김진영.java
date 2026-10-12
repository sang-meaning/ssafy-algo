import java.util.*;

class Solution {

    static int N;
    static int[][] board;
    static boolean[][][][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Robot {
        int r1, c1;
        int r2, c2;
        int cnt;

        Robot(int r1, int c1, int r2, int c2, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
        }
    }

    public int solution(int[][] input) {

        board = input;
        N = board.length;

        visited = new boolean[N][N][N][N];

        Queue<Robot> queue = new ArrayDeque<>();

        queue.offer(new Robot(0, 0, 0, 1, 0));
        visit(0, 0, 0, 1);

        while (!queue.isEmpty()) {

            Robot cur = queue.poll();

            // 도착
            if ((cur.r1 == N - 1 && cur.c1 == N - 1)
                    || (cur.r2 == N - 1 && cur.c2 == N - 1)) {
                return cur.cnt;
            }

            // 1. 상하좌우 이동
            for (int d = 0; d < 4; d++) {

                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];

                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                if (!isValid(nr1, nc1) || !isValid(nr2, nc2)) {
                    continue;
                }

                add(queue, nr1, nc1, nr2, nc2, cur.cnt + 1);
            }


            // 2. 가로 상태
            if (cur.r1 == cur.r2) {

                // 위, 아래 회전
                for (int d : new int[]{-1, 1}) {

                    int nr1 = cur.r1 + d;
                    int nr2 = cur.r2 + d;

                    // 두 칸 모두 비어 있어야 회전 가능
                    if (!isValid(nr1, cur.c1)
                            || !isValid(nr2, cur.c2)) {
                        continue;
                    }

                    // 첫 번째 칸을 축으로 회전
                    add(queue,
                            cur.r1, cur.c1,
                            nr1, cur.c1,
                            cur.cnt + 1);

                    // 두 번째 칸을 축으로 회전
                    add(queue,
                            cur.r2, cur.c2,
                            nr2, cur.c2,
                            cur.cnt + 1);
                }
            }


            // 3. 세로 상태
            else {

                // 왼쪽, 오른쪽 회전
                for (int d : new int[]{-1, 1}) {

                    int nc1 = cur.c1 + d;
                    int nc2 = cur.c2 + d;

                    // 두 칸 모두 비어 있어야 회전 가능
                    if (!isValid(cur.r1, nc1)
                            || !isValid(cur.r2, nc2)) {
                        continue;
                    }

                    // 첫 번째 칸을 축으로 회전
                    add(queue,
                            cur.r1, cur.c1,
                            cur.r1, nc1,
                            cur.cnt + 1);

                    // 두 번째 칸을 축으로 회전
                    add(queue,
                            cur.r2, cur.c2,
                            cur.r2, nc2,
                            cur.cnt + 1);
                }
            }
        }

        return -1;
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < N
                && c >= 0 && c < N
                && board[r][c] == 0;
    }

    static void add(Queue<Robot> queue,
                    int r1, int c1,
                    int r2, int c2,
                    int cnt) {

        if (visited[r1][c1][r2][c2]) {
            return;
        }

        visited[r1][c1][r2][c2] = true;
        visited[r2][c2][r1][c1] = true;

        queue.offer(new Robot(r1, c1, r2, c2, cnt));
    }
}