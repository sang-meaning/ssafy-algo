import java.util.*;

class Solution {

    static int N;
    static int[][] board;
    static boolean[][][] visited;

    // 상 하 좌 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Robot {
        int y;
        int x;
        int dir;
        int count;

        Robot(int y, int x, int dir, int count) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.count = count;
        }
    }

    public int solution(int[][] inputBoard) {

        board = inputBoard;
        N = board.length;

        // [y][x][dir]
        // dir = 0 : 가로
        // dir = 1 : 세로
        visited = new boolean[N][N][2];

        return bfs();
    }

    static int bfs() {

        Queue<Robot> queue = new ArrayDeque<>();

        // 시작
        //
        // (0,0) (0,1)
        //   ■     ■
        //
        // 기준좌표 = (0,0)
        // dir = 0 (가로)

        queue.offer(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {

            Robot cur = queue.poll();

            int y = cur.y;
            int x = cur.x;
            int dir = cur.dir;
            int count = cur.count;

            // 도착 확인
            if (isGoal(y, x, dir)) {
                return count;
            }

            // ==========================
            // 1. 상하좌우 이동
            // ==========================

            for (int d = 0; d < 4; d++) {

                int ny = y + dy[d];
                int nx = x + dx[d];

                // 이동한 위치에 로봇이 들어갈 수 있는지 확인
                if (!isValid(ny, nx, dir)) {
                    continue;
                }

                if (visited[ny][nx][dir]) {
                    continue;
                }

                visited[ny][nx][dir] = true;

                queue.offer(
                    new Robot(ny, nx, dir, count + 1)
                );
            }

            // ==========================
            // 2. 회전
            // ==========================

            if (dir == 0) {

                // 현재 가로
                //
                // (y,x) (y,x+1)
                //   ■      ■
                //

                // --------------------------
                // 위쪽으로 회전
                // --------------------------

                if (y - 1 >= 0
                        && board[y - 1][x] == 0
                        && board[y - 1][x + 1] == 0) {

                    // 왼쪽 칸을 기준으로 회전
                    //
                    // ■
                    // ■ ■
                    //
                    // 새로운 세로 기준 좌표
                    // (y-1, x)

                    addRobot(
                        queue,
                        y - 1,
                        x,
                        1,
                        count + 1
                    );

                    // 오른쪽 칸을 기준으로 회전
                    //
                    //   ■
                    // ■ ■
                    //
                    // 새로운 세로 기준 좌표
                    // (y-1, x+1)

                    addRobot(
                        queue,
                        y - 1,
                        x + 1,
                        1,
                        count + 1
                    );
                }

                // --------------------------
                // 아래쪽으로 회전
                // --------------------------

                if (y + 1 < N
                        && board[y + 1][x] == 0
                        && board[y + 1][x + 1] == 0) {

                    // 왼쪽 칸 기준 회전
                    //
                    // ■ ■
                    // ■
                    //
                    // 세로 기준좌표 = (y,x)

                    addRobot(
                        queue,
                        y,
                        x,
                        1,
                        count + 1
                    );

                    // 오른쪽 칸 기준 회전
                    //
                    // ■ ■
                    //   ■
                    //
                    // 세로 기준좌표 = (y,x+1)

                    addRobot(
                        queue,
                        y,
                        x + 1,
                        1,
                        count + 1
                    );
                }

            } else {

                // 현재 세로
                //
                // (y,x)
                //   ■
                //   ■
                // (y+1,x)

                // --------------------------
                // 왼쪽으로 회전
                // --------------------------

                if (x - 1 >= 0
                        && board[y][x - 1] == 0
                        && board[y + 1][x - 1] == 0) {

                    // 위쪽 칸 기준 회전
                    //
                    // ■ ■
                    //   ■
                    //
                    // 가로 기준 좌표 = (y,x-1)

                    addRobot(
                        queue,
                        y,
                        x - 1,
                        0,
                        count + 1
                    );

                    // 아래쪽 칸 기준 회전
                    //
                    //   ■
                    // ■ ■
                    //
                    // 가로 기준 좌표 = (y+1,x-1)

                    addRobot(
                        queue,
                        y + 1,
                        x - 1,
                        0,
                        count + 1
                    );
                }

                // --------------------------
                // 오른쪽으로 회전
                // --------------------------

                if (x + 1 < N
                        && board[y][x + 1] == 0
                        && board[y + 1][x + 1] == 0) {

                    // 위쪽 칸 기준 회전
                    //
                    // ■ ■
                    // ■
                    //
                    // 가로 기준좌표 = (y,x)

                    addRobot(
                        queue,
                        y,
                        x,
                        0,
                        count + 1
                    );

                    // 아래쪽 칸 기준 회전
                    //
                    // ■
                    // ■ ■
                    //
                    // 가로 기준좌표 = (y+1,x)

                    addRobot(
                        queue,
                        y + 1,
                        x,
                        0,
                        count + 1
                    );
                }
            }
        }

        return -1;
    }

    // =====================================
    // 새로운 상태 Queue에 추가
    // =====================================

    static void addRobot(
            Queue<Robot> queue,
            int y,
            int x,
            int dir,
            int count) {

        if (!isValid(y, x, dir)) {
            return;
        }

        if (visited[y][x][dir]) {
            return;
        }

        visited[y][x][dir] = true;

        queue.offer(
            new Robot(y, x, dir, count)
        );
    }

    // =====================================
    // 현재 상태가 가능한 위치인지 확인
    // =====================================

    static boolean isValid(int y, int x, int dir) {

        // 가로
        if (dir == 0) {

            // 로봇 위치
            //
            // (y,x)
            // (y,x+1)

            if (y < 0 || y >= N) {
                return false;
            }

            if (x < 0 || x + 1 >= N) {
                return false;
            }

            if (board[y][x] == 1) {
                return false;
            }

            if (board[y][x + 1] == 1) {
                return false;
            }

        }

        // 세로
        else {

            // 로봇 위치
            //
            // (y,x)
            // (y+1,x)

            if (x < 0 || x >= N) {
                return false;
            }

            if (y < 0 || y + 1 >= N) {
                return false;
            }

            if (board[y][x] == 1) {
                return false;
            }

            if (board[y + 1][x] == 1) {
                return false;
            }
        }

        return true;
    }

    // =====================================
    // (N-1, N-1)에 도착했는지 확인
    // =====================================

    static boolean isGoal(int y, int x, int dir) {

        if (dir == 0) {

            // ■ ■
            //
            // 오른쪽 칸이 목표 위치

            return y == N - 1
                    && x + 1 == N - 1;

        } else {

            // ■
            // ■
            //
            // 아래쪽 칸이 목표 위치

            return y + 1 == N - 1
                    && x == N - 1;
        }
    }
}