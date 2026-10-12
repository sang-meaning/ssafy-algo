import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static final int SIZE = 100;
    static int[][] map = new int[SIZE][SIZE];
    static boolean[][] visited = new boolean[SIZE][SIZE];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) break;
            int tcNum = Integer.parseInt(line.trim());

            int startX = -1, startY = -1;

            for (int i = 0; i < SIZE; i++) {
                String row = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = row.charAt(j) - '0';
                    visited[i][j] = false;

                    if (map[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            int result = bfs(startX, startY) ? 1 : 0;
            System.out.println("#" + tcNum + " " + result);
        }
    }

    static boolean bfs(int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (map[x][y] == 3) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                    if (map[nx][ny] != 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return false;
    }
}