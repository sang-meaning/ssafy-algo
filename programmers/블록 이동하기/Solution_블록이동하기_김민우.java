import java.util.*;

class Solution {

    static class Robot {
        int r;
        int c;
        int dir;
        int time;

        // dir = 0: 가로, dir = 1: 세로
        Robot(int r, int c, int dir, int time) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.time = time;
        }
    }

    static int N;
    static int[][] map;
    static boolean[][][] visited;

    static int[][] delta = {
        {-1, 0},  // 위
        {1, 0},   // 아래
        {0, -1},  // 왼쪽
        {0, 1}    // 오른쪽
    };

    public int solution(int[][] board) {
        N = board.length;
        map = board;

        // visited[행][열][방향]
        visited = new boolean[N][N][2];

        Queue<Robot> queue = new ArrayDeque<>();

        // 시작 상태: (0,0), (0,1)을 차지하는 가로 로봇
        queue.offer(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Robot current = queue.poll();

            int r = current.r;
            int c = current.c;
            int dir = current.dir;
            int time = current.time;

            // 로봇의 두 칸 중 하나가 도착점에 도달
            if (isArrived(r, c, dir)) {
                return time;
            }

            // 상하좌우 이동
            move(queue, current);

            // 회전
            rotate(queue, current);
        }

        return -1;
    }

    // 상하좌우 이동
    static void move(Queue<Robot> queue, Robot current) {
        for (int[] d : delta) {
            int nr = current.r + d[0];
            int nc = current.c + d[1];

            if (!canPlace(nr, nc, current.dir)) {
                continue;
            }

            if (visited[nr][nc][current.dir]) {
                continue;
            }

            visited[nr][nc][current.dir] = true;
            queue.offer(new Robot(
                nr,
                nc,
                current.dir,
                current.time + 1
            ));
        }
    }

    // 회전
    static void rotate(Queue<Robot> queue, Robot current) {
        int r = current.r;
        int c = current.c;
        int time = current.time;

        if (current.dir == 0) {
            // 현재 가로 상태
            // 차지하는 칸: (r,c), (r,c+1)

            // 위쪽 공간을 이용한 회전
            if (isEmpty(r - 1, c) && isEmpty(r - 1, c + 1)) {
                // 왼쪽 칸을 축으로 회전
                addState(queue, r - 1, c, 1, time + 1);

                // 오른쪽 칸을 축으로 회전
                addState(queue, r - 1, c + 1, 1, time + 1);
            }

            // 아래쪽 공간을 이용한 회전
            if (isEmpty(r + 1, c) && isEmpty(r + 1, c + 1)) {
                // 왼쪽 칸을 축으로 회전
                addState(queue, r, c, 1, time + 1);

                // 오른쪽 칸을 축으로 회전
                addState(queue, r, c + 1, 1, time + 1);
            }

        } else {
            // 현재 세로 상태
            // 차지하는 칸: (r,c), (r+1,c)

            // 왼쪽 공간을 이용한 회전
            if (isEmpty(r, c - 1) && isEmpty(r + 1, c - 1)) {
                // 위쪽 칸을 축으로 회전
                addState(queue, r, c - 1, 0, time + 1);

                // 아래쪽 칸을 축으로 회전
                addState(queue, r + 1, c - 1, 0, time + 1);
            }

            // 오른쪽 공간을 이용한 회전
            if (isEmpty(r, c + 1) && isEmpty(r + 1, c + 1)) {
                // 위쪽 칸을 축으로 회전
                addState(queue, r, c, 0, time + 1);

                // 아래쪽 칸을 축으로 회전
                addState(queue, r + 1, c, 0, time + 1);
            }
        }
    }

    static void addState(
        Queue<Robot> queue,
        int r,
        int c,
        int dir,
        int time
    ) {
        if (!canPlace(r, c, dir)) {
            return;
        }

        if (visited[r][c][dir]) {
            return;
        }

        visited[r][c][dir] = true;
        queue.offer(new Robot(r, c, dir, time));
    }

    // 해당 기준 좌표와 방향으로 로봇을 놓을 수 있는지 확인
    static boolean canPlace(int r, int c, int dir) {
        if (dir == 0) {
            // 가로: (r,c), (r,c+1)
            return isEmpty(r, c) && isEmpty(r, c + 1);
        } else {
            // 세로: (r,c), (r+1,c)
            return isEmpty(r, c) && isEmpty(r + 1, c);
        }
    }

    static boolean isEmpty(int r, int c) {
        return r >= 0 && r < N
            && c >= 0 && c < N
            && map[r][c] == 0;
    }

    static boolean isArrived(int r, int c, int dir) {
        if (dir == 0) {
            // 가로: 오른쪽 칸이 도착점인지 확인
            return r == N - 1 && c + 1 == N - 1;
        } else {
            // 세로: 아래쪽 칸이 도착점인지 확인
            return r + 1 == N - 1 && c == N - 1;
        }
    }
}