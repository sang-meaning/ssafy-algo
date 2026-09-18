import java.util.*;

class Solution {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Robot {
        int r1;
        int c1;
        int r2;
        int c2;
        int cnt;

        Robot(int r1, int c1, int r2, int c2, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
        }
    }

    public int solution(int[][] board) {

        int N = board.length;

        ArrayDeque<Robot> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][N][N][N];

        // 시작 상태
        q.offer(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;

        while (!q.isEmpty()) {

            Robot cur = q.poll();

            // 도착 확인
            if ((cur.r1 == N - 1 && cur.c1 == N - 1)
                    || (cur.r2 == N - 1 && cur.c2 == N - 1)) {
                return cur.cnt;
            }

            // =========================
            // 1. 상하좌우 이동
            // =========================
            for (int d = 0; d < 4; d++) {

                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];

                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                // 범위 밖
                if (nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= N) continue;
                if (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= N) continue;

                // 벽
                if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;

                // 이미 방문
                if (visited[nr1][nc1][nr2][nc2]) continue;

                visited[nr1][nc1][nr2][nc2] = true;

                q.offer(new Robot(
                        nr1, nc1,
                        nr2, nc2,
                        cur.cnt + 1
                ));
            }

            // =========================
            // 2. 회전
            // =========================

            // 가로 상태
            if (cur.r1 == cur.r2) {

                int r = cur.r1;

                // -------------------------
                // 위로 회전
                // -------------------------
                if (r - 1 >= 0
                        && board[r - 1][cur.c1] == 0
                        && board[r - 1][cur.c2] == 0) {

                    // 첫 번째 점 기준 회전
                    if (!visited[r - 1][cur.c1][r][cur.c1]) {

                        visited[r - 1][cur.c1][r][cur.c1] = true;

                        q.offer(new Robot(
                                r - 1, cur.c1,
                                r, cur.c1,
                                cur.cnt + 1
                        ));
                    }

                    // 두 번째 점 기준 회전
                    if (!visited[r - 1][cur.c2][r][cur.c2]) {

                        visited[r - 1][cur.c2][r][cur.c2] = true;

                        q.offer(new Robot(
                                r - 1, cur.c2,
                                r, cur.c2,
                                cur.cnt + 1
                        ));
                    }
                }

                // -------------------------
                // 아래로 회전
                // -------------------------
                if (r + 1 < N
                        && board[r + 1][cur.c1] == 0
                        && board[r + 1][cur.c2] == 0) {

                    // 첫 번째 점 기준 회전
                    if (!visited[r][cur.c1][r + 1][cur.c1]) {

                        visited[r][cur.c1][r + 1][cur.c1] = true;

                        q.offer(new Robot(
                                r, cur.c1,
                                r + 1, cur.c1,
                                cur.cnt + 1
                        ));
                    }

                    // 두 번째 점 기준 회전
                    if (!visited[r][cur.c2][r + 1][cur.c2]) {

                        visited[r][cur.c2][r + 1][cur.c2] = true;

                        q.offer(new Robot(
                                r, cur.c2,
                                r + 1, cur.c2,
                                cur.cnt + 1
                        ));
                    }
                }
            }

            // 세로 상태
            else {

                int c = cur.c1;

                // -------------------------
                // 왼쪽으로 회전
                // -------------------------
                if (c - 1 >= 0
                        && board[cur.r1][c - 1] == 0
                        && board[cur.r2][c - 1] == 0) {

                    // 첫 번째 점 기준 회전
                    if (!visited[cur.r1][c - 1][cur.r1][c]) {

                        visited[cur.r1][c - 1][cur.r1][c] = true;

                        q.offer(new Robot(
                                cur.r1, c - 1,
                                cur.r1, c,
                                cur.cnt + 1
                        ));
                    }

                    // 두 번째 점 기준 회전
                    if (!visited[cur.r2][c - 1][cur.r2][c]) {

                        visited[cur.r2][c - 1][cur.r2][c] = true;

                        q.offer(new Robot(
                                cur.r2, c - 1,
                                cur.r2, c,
                                cur.cnt + 1
                        ));
                    }
                }

                // -------------------------
                // 오른쪽으로 회전
                // -------------------------
                if (c + 1 < N
                        && board[cur.r1][c + 1] == 0
                        && board[cur.r2][c + 1] == 0) {

                    // 첫 번째 점 기준 회전
                    if (!visited[cur.r1][c][cur.r1][c + 1]) {

                        visited[cur.r1][c][cur.r1][c + 1] = true;

                        q.offer(new Robot(
                                cur.r1, c,
                                cur.r1, c + 1,
                                cur.cnt + 1
                        ));
                    }

                    // 두 번째 점 기준 회전
                    if (!visited[cur.r2][c][cur.r2][c + 1]) {

                        visited[cur.r2][c][cur.r2][c + 1] = true;

                        q.offer(new Robot(
                                cur.r2, c,
                                cur.r2, c + 1,
                                cur.cnt + 1
                        ));
                    }
                }
            }
        }

        return 0;
    }
}