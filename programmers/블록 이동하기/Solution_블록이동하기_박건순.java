import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    static class Robot {
        int r;
        int c;
        int dir;    // 0: 가로, 1: 세로
        int count;

        Robot(int r, int c, int dir, int count) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.count = count;
        }
    }

    static int N;
    static int[][] board;
    static boolean[][][] visited;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] input) {

        board = input;
        N = board.length;

        // visited[r][c][dir]
        // 가로 : (r,c)가 왼쪽 칸
        // 세로 : (r,c)가 위쪽 칸
        visited = new boolean[N][N][2];

        return bfs();
    }

    static int bfs() {

        Queue<Robot> queue = new ArrayDeque<>();

        // 시작 상태
        // (0,0), (0,1)을 차지하므로 가로
        queue.offer(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {

            Robot cur = queue.poll();

            int r = cur.r;
            int c = cur.c;
            int dir = cur.dir;
            int count = cur.count;

            // 목표 도착 확인
            if (isGoal(r, c, dir)) {
                return count;
            }

            // =========================
            // 1. 상하좌우 이동
            // =========================
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!canPlace(nr, nc, dir)) {
                    continue;
                }

                if (visited[nr][nc][dir]) {
                    continue;
                }

                visited[nr][nc][dir] = true;

                queue.offer(
                    new Robot(nr, nc, dir, count + 1)
                );
            }

            // =========================
            // 2. 회전
            // =========================

            if (dir == 0) {
                // 현재 가로 상태
                //
                // (r,c) (r,c+1)

                // ---------- 위쪽 회전 ----------
                //
                // □ □
                // A B
                //
                if (r - 1 >= 0
                        && board[r - 1][c] == 0
                        && board[r - 1][c + 1] == 0) {

                    // A를 축으로 회전
                    // □
                    // A
                    //
                    // 세로 기준점은 위쪽 칸
                    addQueue(
                        queue,
                        r - 1,
                        c,
                        1,
                        count + 1
                    );

                    // B를 축으로 회전
                    //
                    //   □
                    //   B
                    addQueue(
                        queue,
                        r - 1,
                        c + 1,
                        1,
                        count + 1
                    );
                }

                // ---------- 아래쪽 회전 ----------
                //
                // A B
                // □ □
                //
                if (r + 1 < N
                        && board[r + 1][c] == 0
                        && board[r + 1][c + 1] == 0) {

                    // A를 축으로 회전
                    //
                    // A
                    // □
                    addQueue(
                        queue,
                        r,
                        c,
                        1,
                        count + 1
                    );

                    // B를 축으로 회전
                    //
                    //   B
                    //   □
                    addQueue(
                        queue,
                        r,
                        c + 1,
                        1,
                        count + 1
                    );
                }

            } else {

                // 현재 세로 상태
                //
                // A
                // B

                // ---------- 왼쪽 회전 ----------
                //
                // □ A
                // □ B
                //
                if (c - 1 >= 0
                        && board[r][c - 1] == 0
                        && board[r + 1][c - 1] == 0) {

                    // A를 축으로 회전
                    //
                    // □ A
                    addQueue(
                        queue,
                        r,
                        c - 1,
                        0,
                        count + 1
                    );

                    // B를 축으로 회전
                    //
                    // □ B
                    addQueue(
                        queue,
                        r + 1,
                        c - 1,
                        0,
                        count + 1
                    );
                }

                // ---------- 오른쪽 회전 ----------
                //
                // A □
                // B □
                //
                if (c + 1 < N
                        && board[r][c + 1] == 0
                        && board[r + 1][c + 1] == 0) {

                    // A를 축으로 회전
                    //
                    // A □
                    addQueue(
                        queue,
                        r,
                        c,
                        0,
                        count + 1
                    );

                    // B를 축으로 회전
                    //
                    // B □
                    addQueue(
                        queue,
                        r + 1,
                        c,
                        0,
                        count + 1
                    );
                }
            }
        }

        return -1;
    }

    // 해당 상태로 로봇을 놓을 수 있는지 확인
    static boolean canPlace(int r, int c, int dir) {

        if (dir == 0) {
            // 가로
            // (r,c), (r,c+1)

            if (r < 0 || r >= N) {
                return false;
            }

            if (c < 0 || c + 1 >= N) {
                return false;
            }

            return board[r][c] == 0
                    && board[r][c + 1] == 0;

        } else {
            // 세로
            // (r,c), (r+1,c)

            if (r < 0 || r + 1 >= N) {
                return false;
            }

            if (c < 0 || c >= N) {
                return false;
            }

            return board[r][c] == 0
                    && board[r + 1][c] == 0;
        }
    }

    // 방문하지 않은 상태면 Queue에 추가
    static void addQueue(
            Queue<Robot> queue,
            int r,
            int c,
            int dir,
            int count
    ) {

        if (!canPlace(r, c, dir)) {
            return;
        }

        if (visited[r][c][dir]) {
            return;
        }

        visited[r][c][dir] = true;

        queue.offer(
            new Robot(r, c, dir, count)
        );
    }

    // 로봇의 두 칸 중 하나가 (N-1, N-1)이면 도착
    static boolean isGoal(int r, int c, int dir) {

        if (dir == 0) {
            // 가로
            return r == N - 1
                    && c + 1 == N - 1;

        } else {
            // 세로
            return r + 1 == N - 1
                    && c == N - 1;
        }
    }
}