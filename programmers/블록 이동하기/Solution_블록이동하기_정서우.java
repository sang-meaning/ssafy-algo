import java.util.*;

class Solution_블록이동하기_정서우 {
    static class Node {
        int r1, c1, r2, c2;
        int time;

        Node(int r1, int c1, int r2, int c2, int time) {
            // 좌표를 항상 앞선 순서로 정렬해 방문 체크 단순화
            if (r1 < r2 || (r1 == r2 && c1 < c2)) {
                this.r1 = r1;
                this.c1 = c1;
                this.r2 = r2;
                this.c2 = c2;
            } else {
                this.r1 = r2;
                this.c1 = c2;
                this.r2 = r1;
                this.c2 = c1;
            }
            this.time = time;
        }

        // 가로(0), 세로(1)
        int getDir() {
            return (r1 == r2) ? 0 : 1;
        }
    }

    // 상, 하, 좌, 우 이동
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board) {
        int n = board.length;

        // 외곽을 1(벽)로 감싼 패딩 배열 생성 (범위 밖 예외 처리 생략 가능)
        int[][] map = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(map[i], 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i + 1][j + 1] = board[i][j];
            }
        }

        // 방문 체크: [r][c][방향] - (r1, c1) 기준
        boolean[][][] visited = new boolean[n + 2][n + 2][2];
        Queue<Node> q = new LinkedList<>();

        // 시작 상태: (1, 1), (1, 2), 가로 방향, 시간 0
        q.offer(new Node(1, 1, 1, 2, 0));
        visited[1][1][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 목표 지점 (n, n) 도달 확인
            if ((cur.r1 == n && cur.c1 == n) || (cur.r2 == n && cur.c2 == n)) {
                return cur.time;
            }

            // 1. 상, 하, 좌, 우 평행 이동
            for (int d = 0; d < 4; d++) {
                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];
                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                if (map[nr1][nc1] == 0 && map[nr2][nc2] == 0) {
                    Node next = new Node(nr1, nc1, nr2, nc2, cur.time + 1);
                    if (!visited[next.r1][next.c1][next.getDir()]) {
                        visited[next.r1][next.c1][next.getDir()] = true;
                        q.offer(next);
                    }
                }
            }

            // 2. 회전 이동
            // 가로 상태 -> 세로로 회전 (위쪽이나 아래쪽 두 칸이 모두 비어 있어야 함)
            if (cur.getDir() == 0) {
                // 아래쪽(행 + 1)으로 회전
                if (map[cur.r1 + 1][cur.c1] == 0 && map[cur.r2 + 1][cur.c2] == 0) {
                    addNext(q, visited, cur.r1, cur.c1, cur.r1 + 1, cur.c1, cur.time + 1);
                    addNext(q, visited, cur.r2, cur.c2, cur.r2 + 1, cur.c2, cur.time + 1);
                }
                // 위쪽(행 - 1)으로 회전
                if (map[cur.r1 - 1][cur.c1] == 0 && map[cur.r2 - 1][cur.c2] == 0) {
                    addNext(q, visited, cur.r1, cur.c1, cur.r1 - 1, cur.c1, cur.time + 1);
                    addNext(q, visited, cur.r2, cur.c2, cur.r2 - 1, cur.c2, cur.time + 1);
                }
            }
            // 세로 상태 -> 가로로 회전 (왼쪽이나 오른쪽 두 칸이 모두 비어 있어야 함)
            else {
                // 오른쪽(열 + 1)으로 회전
                if (map[cur.r1][cur.c1 + 1] == 0 && map[cur.r2][cur.c2 + 1] == 0) {
                    addNext(q, visited, cur.r1, cur.c1, cur.r1, cur.c1 + 1, cur.time + 1);
                    addNext(q, visited, cur.r2, cur.c2, cur.r2, cur.c2 + 1, cur.time + 1);
                }
                // 왼쪽(열 - 1)으로 회전
                if (map[cur.r1][cur.c1 - 1] == 0 && map[cur.r2][cur.c2 - 1] == 0) {
                    addNext(q, visited, cur.r1, cur.c1, cur.r1, cur.c1 - 1, cur.time + 1);
                    addNext(q, visited, cur.r2, cur.c2, cur.r2, cur.c2 - 1, cur.time + 1);
                }
            }
        }

        return 0;
    }

    private void addNext(Queue<Node> q, boolean[][][] visited, int r1, int c1, int r2, int c2, int time) {
        Node next = new Node(r1, c1, r2, c2, time);
        if (!visited[next.r1][next.c1][next.getDir()]) {
            visited[next.r1][next.c1][next.getDir()] = true;
            q.offer(next);
        }
    }
}