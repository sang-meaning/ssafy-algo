package pgms;

import java.util.*;

class Solution_블록이동하기_하상호 {

    static int N;

    static class Robot {
        int r1, c1;
        int r2, c2;
        int dist;

        Robot(int r1, int c1, int r2, int c2, int dist) {

            // 두 좌표의 순서를 항상 일정하게 유지
            if (r1 > r2 || (r1 == r2 && c1 > c2)) {
                int temp;

                temp = r1;
                r1 = r2;
                r2 = temp;

                temp = c1;
                c1 = c2;
                c2 = temp;
            }

            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.dist = dist;
        }

        String key() {
            return r1 + "," + c1 + "," + r2 + "," + c2;
        }
    }

    public int solution(int[][] board) {

        N = board.length;

        Queue<Robot> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        Robot start = new Robot(0, 0, 0, 1, 0);

        queue.offer(start);
        visited.add(start.key());

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            Robot cur = queue.poll();

            // 두 칸 중 하나라도 목적지 도착
            if ((cur.r1 == N - 1 && cur.c1 == N - 1)
                    || (cur.r2 == N - 1 && cur.c2 == N - 1)) {

                return cur.dist;
            }

            // -------------------------
            // 1. 상하좌우 이동
            // -------------------------
            for (int d = 0; d < 4; d++) {

                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];

                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                if (canMove(nr1, nc1, board)
                        && canMove(nr2, nc2, board)) {

                    Robot next = new Robot(
                            nr1, nc1,
                            nr2, nc2,
                            cur.dist + 1
                    );

                    if (!visited.contains(next.key())) {
                        visited.add(next.key());
                        queue.offer(next);
                    }
                }
            }

            // -------------------------
            // 2. 회전
            // -------------------------

            // 가로 상태
            if (cur.r1 == cur.r2) {

                // 위 / 아래 방향 회전
                for (int d : new int[]{-1, 1}) {

                    int nr1 = cur.r1 + d;
                    int nr2 = cur.r2 + d;

                    // 회전하려는 방향의 두 칸이 모두 비어 있어야 함
                    if (canMove(nr1, cur.c1, board)
                            && canMove(nr2, cur.c2, board)) {

                        // 첫 번째 블록 기준 회전
                        Robot next1 = new Robot(
                                cur.r1, cur.c1,
                                nr1, cur.c1,
                                cur.dist + 1
                        );

                        if (!visited.contains(next1.key())) {
                            visited.add(next1.key());
                            queue.offer(next1);
                        }

                        // 두 번째 블록 기준 회전
                        Robot next2 = new Robot(
                                cur.r2, cur.c2,
                                nr2, cur.c2,
                                cur.dist + 1
                        );

                        if (!visited.contains(next2.key())) {
                            visited.add(next2.key());
                            queue.offer(next2);
                        }
                    }
                }
            }

            // 세로 상태
            else {

                // 왼쪽 / 오른쪽 방향 회전
                for (int d : new int[]{-1, 1}) {

                    int nc1 = cur.c1 + d;
                    int nc2 = cur.c2 + d;

                    if (canMove(cur.r1, nc1, board)
                            && canMove(cur.r2, nc2, board)) {

                        // 첫 번째 블록 기준 회전
                        Robot next1 = new Robot(
                                cur.r1, cur.c1,
                                cur.r1, nc1,
                                cur.dist + 1
                        );

                        if (!visited.contains(next1.key())) {
                            visited.add(next1.key());
                            queue.offer(next1);
                        }

                        // 두 번째 블록 기준 회전
                        Robot next2 = new Robot(
                                cur.r2, cur.c2,
                                cur.r2, nc2,
                                cur.dist + 1
                        );

                        if (!visited.contains(next2.key())) {
                            visited.add(next2.key());
                            queue.offer(next2);
                        }
                    }
                }
            }
        }

        return -1;
    }

    static boolean canMove(int r, int c, int[][] board) {

        if (r < 0 || r >= N || c < 0 || c >= N) {
            return false;
        }

        return board[r][c] == 0;
    }
}