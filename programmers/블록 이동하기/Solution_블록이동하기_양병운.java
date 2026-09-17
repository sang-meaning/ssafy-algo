import java.util.*;

class Solution {

    int N;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    boolean[][][][] visited;

    public int solution(int[][] board) {
        N = board.length;
        visited = new boolean[N][N][N][N];
        int[] robot = {0, 0, 0, 1, 0};
        visited[0][0][0][1] = true;
        return bfs(robot, board);
    }

    public int bfs(int[] robot, int[][] board) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(robot);
        while (!queue.isEmpty()) {
            robot = queue.poll();
            if (isEndPoint(robot)) return robot[4];
            int x1 = robot[0];
            int y1 = robot[1];
            int x2 = robot[2];
            int y2 = robot[3];
            int time = robot[4];

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];
                if (canMove(nx1, ny1, nx2, ny2, board)) {
                    addState(queue,nx1, ny1,nx2, ny2,time + 1);
                }
            }

            // 가로
            if (x1 == x2) {
                for (int d : new int[]{-1, 1}) {
                    int nx = x1 + d;
                    if (nx < 0 || nx >= N) continue;
                    if (board[nx][y1] == 0 && board[nx][y2] == 0) {
                        addState(queue, x1, y1,nx, y1,time + 1);
                        addState(queue, x2, y2, nx, y2, time + 1);
                    }
                }
            }

            // 세로
            else {
                for (int d : new int[]{-1, 1}) {
                    int ny = y1 + d;
                    if (ny < 0 || ny >= N) continue;
                    if (board[x1][ny] == 0 && board[x2][ny] == 0) {
                        addState(queue, x1, y1, x1, ny, time + 1);
                        addState(queue, x2, y2, x2, ny, time + 1);
                    }
                }
            }
        }
        return -1;
    }

    public void addState(Queue<int[]> queue,int x1, int y1, int x2, int y2, int time) {
        if (x1 > x2 || (x1 == x2 && y1 > y2)) {
            int tx = x1;
            int ty = y1;
            x1 = x2;
            y1 = y2;
            x2 = tx;
            y2 = ty;
        }
        if (visited[x1][y1][x2][y2]) return;
        visited[x1][y1][x2][y2] = true;
        queue.add(new int[]{x1, y1, x2, y2, time});
    }

    public boolean isEndPoint(int[] robot) {
        return (robot[0] == N - 1 && robot[1] == N - 1) || (robot[2] == N - 1 && robot[3] == N - 1);
    }

    public boolean canMove(int r1, int c1, int r2, int c2, int[][] board) {
        if (r1 < 0 || r1 >= N || c1 < 0 || c1 >= N) return false;
        if (r2 < 0 || r2 >= N || c2 < 0 || c2 >= N) return false;
        return board[r1][c1] == 0 && board[r2][c2] == 0;
    }
}