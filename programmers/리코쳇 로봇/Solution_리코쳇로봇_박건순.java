import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	int N, M;
    char[][] map;

    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    public int solution(String[] board) {

        N = board.length;
        M = board[0].length();

        map = new char[N][M];

        int startR = 0;
        int startC = 0;

        for (int r = 0; r < N; r++) {
            map[r] = board[r].toCharArray();

            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'R') {
                    startR = r;
                    startC = c;
                }
            }
        }

        return bfs(startR, startC);
    }

    int bfs(int startR, int startC) {

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int count = cur[2];

            if (map[r][c] == 'G') {
                return count;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r;
                int nc = c;

                while (true) {

                    int nextR = nr + dr[d];
                    int nextC = nc + dc[d];

                    if (nextR < 0 || nextR >= N ||
                        nextC < 0 || nextC >= M ||
                        map[nextR][nextC] == 'D') {
                        break;
                    }

                    nr = nextR;
                    nc = nextC;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc, count + 1});
            }
        }

        return -1;
    }
}