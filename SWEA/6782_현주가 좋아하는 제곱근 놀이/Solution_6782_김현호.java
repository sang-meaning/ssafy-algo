import java.util.*;

class Solution {

    static int N;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Robot {
        int r1, c1;
        int r2, c2;
        int time;

        Robot(int r1, int c1, int r2, int c2, int time) {

            // 좌표 순서를 항상 일정하게 유지
            if (r1 > r2 || (r1 == r2 && c1 > c2)) {
                this.r1 = r2;
                this.c1 = c2;
                this.r2 = r1;
                this.c2 = c1;
            } else {
                this.r1 = r1;
                this.c1 = c1;
                this.r2 = r2;
                this.c2 = c2;
            }

            this.time = time;
        }

        String key() {
            return r1 + "," + c1 + "," + r2 + "," + c2;
        }
    }

    public int solution(int[][] board) {

        map = board;
        N = board.length;

        Queue<Robot> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Robot start = new Robot(0, 0, 0, 1, 0);

        q.offer(start);
        visited.add(start.key());

        while (!q.isEmpty()) {

            Robot cur = q.poll();

            // 두 칸 중 하나가 목적지 도착
            if ((cur.r1 == N - 1 && cur.c1 == N - 1)
                    || (cur.r2 == N - 1 && cur.c2 == N - 1)) {

                return cur.time;
            }

            // =====================================
            // 1. 상하좌우 이동
            // =====================================
            for (int d = 0; d < 4; d++) {

                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];

                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                if (canMove(nr1, nc1) && canMove(nr2, nc2)) {

                    Robot next = new Robot(
                            nr1, nc1,
                            nr2, nc2,
                            cur.time + 1
                    );

                    if (!visited.contains(next.key())) {
                        visited.add(next.key());
                        q.offer(next);
                    }
                }
            }

            // =====================================
            // 2. 회전
            // =====================================

            // 가로 상태
            if (cur.r1 == cur.r2) {

                // 위 / 아래 회전
                for (int dir : new int[]{-1, 1}) {

                    int nr = cur.r1 + dir;

                    // 위 또는 아래 두 칸이 모두 비어 있어야 회전 가능
                    if (canMove(nr, cur.c1)
                            && canMove(nr, cur.c2)) {

                        // 첫 번째 칸을 축으로 회전
                        Robot next1 = new Robot(
                                cur.r1, cur.c1,
                                nr, cur.c1,
                                cur.time + 1
                        );

                        if (!visited.contains(next1.key())) {
                            visited.add(next1.key());
                            q.offer(next1);
                        }

                        // 두 번째 칸을 축으로 회전
                        Robot next2 = new Robot(
                                cur.r2, cur.c2,
                                nr, cur.c2,
                                cur.time + 1
                        );

                        if (!visited.contains(next2.key())) {
                            visited.add(next2.key());
                            q.offer(next2);
                        }
                    }
                }
            }

            // 세로 상태
            else {

                // 왼쪽 / 오른쪽 회전
                for (int dir : new int[]{-1, 1}) {

                    int nc = cur.c1 + dir;

                    // 왼쪽 또는 오른쪽 두 칸이 모두 비어 있어야 회전 가능
                    if (canMove(cur.r1, nc)
                            && canMove(cur.r2, nc)) {

                        // 첫 번째 칸을 축으로 회전
                        Robot next1 = new Robot(
                                cur.r1, cur.c1,
                                cur.r1, nc,
                                cur.time + 1
                        );

                        if (!visited.contains(next1.key())) {
                            visited.add(next1.key());
                            q.offer(next1);
                        }

                        // 두 번째 칸을 축으로 회전
                        Robot next2 = new Robot(
                                cur.r2, cur.c2,
                                cur.r2, nc,
                                cur.time + 1
                        );

                        if (!visited.contains(next2.key())) {
                            visited.add(next2.key());
                            q.offer(next2);
                        }
                    }
                }
            }
        }

        return -1;
    }

    static boolean canMove(int r, int c) {

        return r >= 0 && r < N
                && c >= 0 && c < N
                && map[r][c] == 0;
    }
}