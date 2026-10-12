import java.util.*;

class Solution {
    static int N;
    static int[][] board;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] input) {
        board = input;
        N = board.length;

        // visited[x][y][0] = 가로 상태
        // visited[x][y][1] = 세로 상태
        
        visited = new boolean[N][N][2];

        return bfs();
    }

    static int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();

        // {x, y, dir, time}
        // 시작 : (0,0), (0,1) 가로 상태
        
        queue.offer(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int dir = current[2];
            int time = current[3];

            // =========================
            // 목적지 도착 확인
            // =========================

            // 가로 상태라면
            // (x,y), (x,y+1)
            
            if (dir == 0) {
                if ((x == N - 1 && y == N - 1) || (x == N - 1 && y + 1 == N - 1)) {
                    return time;
                }
            }

            // 세로 상태라면
            // (x,y), (x+1,y)
            
            else {
                if ((x == N - 1 && y == N - 1) || (x + 1 == N - 1 && y == N - 1)) {

                    return time;
                }
            }

            // =========================
            // 1. 상하좌우 이동
            // =========================

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (canMove(nx, ny, dir)) {
                    if (!visited[nx][ny][dir]) {
                        visited[nx][ny][dir] = true;

                        queue.offer(new int[]{nx, ny, dir, time + 1 });
                    }
                }
            }


            // =========================
            // 2. 회전
            // =========================

            // 현재 가로
            if (dir == 0) {
                
                // 현재 로봇:
                //
                // (x,y) (x,y+1)

                // ---------------------
                // 위쪽으로 회전
                // ---------------------
                
                if (x - 1 >= 0 && board[x - 1][y] == 0 && board[x - 1][y + 1] == 0) {
                
                    // 왼쪽 칸 기준 회전
                    //
                    // (x-1,y)
                    //    |
                    //  (x,y)
                    
                    if (!visited[x - 1][y][1]) {
                        visited[x - 1][y][1] = true;

                        queue.offer(new int[]{ x - 1, y, 1, time + 1 });
                    }

                    // 오른쪽 칸 기준 회전
                    //
                    //        (x-1,y+1)
                    //             |
                    //         (x,y+1)
                
                    if (!visited[x - 1][y + 1][1]) {
                        visited[x - 1][y + 1][1] = true;

                        queue.offer(new int[]{ x - 1, y + 1, 1, time + 1 });
                    }
                }

                // ---------------------
                // 아래쪽으로 회전
                // ---------------------
                
                if (x + 1 < N && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0) {
                    // 왼쪽 칸 기준 회전
                    //
                    // (x,y)
                    //   |
                    // (x+1,y)
    
                    if (!visited[x][y][1]) {
                        visited[x][y][1] = true;

                        queue.offer(new int[]{ x, y, 1, time + 1 });
                    }

                    // 오른쪽 칸 기준 회전
                    //
                    // (x,y+1)
                    //    |
                    // (x+1,y+1)
                    
                    if (!visited[x][y + 1][1]) {
                        visited[x][y + 1][1] = true;

                        queue.offer(new int[]{x, y + 1, 1, time + 1 });
                    }
                }
            }

            // =========================
            // 현재 세로
            // =========================

            else {

                // 현재 로봇:
                //
                // (x,y)
                //   |
                // (x+1,y)

                // ---------------------
                // 왼쪽으로 회전
                // ---------------------

                if (y - 1 >= 0 && board[x][y - 1] == 0 && board[x + 1][y - 1] == 0) {

                    // 위쪽 칸 기준 회전
                    //
                    // (x,y-1) (x,y)

                    if (!visited[x][y - 1][0]) {
                        visited[x][y - 1][0] = true;

                        queue.offer(new int[]{ x, y - 1, 0, time + 1 });
                    }

                    // 아래쪽 칸 기준 회전
                    //
                    // (x+1,y-1) (x+1,y)

                    if (!visited[x + 1][y - 1][0]) {
                        visited[x + 1][y - 1][0] = true;

                        queue.offer(new int[]{ x + 1, y - 1, 0, time + 1 });
                    }
                }

                // ---------------------
                // 오른쪽으로 회전
                // ---------------------

                if (y + 1 < N && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0) {

                    // 위쪽 칸 기준 회전
                    //
                    // (x,y) (x,y+1)

                    if (!visited[x][y][0]) {
                        visited[x][y][0] = true;

                        queue.offer(new int[]{ x, y, 0, time + 1});
                    }

                    // 아래쪽 칸 기준 회전
                    //
                    // (x+1,y) (x+1,y+1)

                    if (!visited[x + 1][y][0]) {
                        visited[x + 1][y][0] = true;
                        
                        queue.offer(new int[]{x + 1, y, 0, time + 1});
                    }
                }
            }
        }
        return -1;
    }

    // ====================================
    // 상하좌우 이동이 가능한지 확인
    // ====================================

    static boolean canMove(int x, int y, int dir) {
        // 가로 상태
        if (dir == 0) {
            if (x < 0 || x >= N || y < 0 || y + 1 >= N) {
                return false;
            }
            if (board[x][y] == 1 || board[x][y + 1] == 1) {
                return false;
            }
        }
        // 세로 상태
        else {
            // (x,y), (x+1,y)
            if (x < 0 || x + 1 >= N || y < 0 || y >= N) {
                return false;
            }
            if (board[x][y] == 1 || board[x + 1][y] == 1) {
                return false;
            }
        }
        return true;
    }
}