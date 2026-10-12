import java.io.*;
import java.util.*;

public class Solution_1868_정서우 {
    static int N;
    static char[][] map;
    static int[][] mineCount;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().trim().toCharArray();
            }

            // 1. 각 빈칸의 주변 지뢰 개수 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        mineCount[i][j] = countAdjacentMines(i, j);
                    }
                }
            }

            int clickCount = 0;

            // 2. 주변 지뢰가 0개인 지점부터 먼저 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j] && mineCount[i][j] == 0) {
                        clickCount++;
                        bfs(i, j);
                    }
                }
            }

            // 3. 아직 열리지 않은 나머지 빈칸 개별 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j]) {
                        clickCount++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(clickCount).append("\n");
        }

        System.out.print(sb);
    }

    static int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isValid(nx, ny) && map[nx][ny] == '*') {
                count++;
            }
        }
        return count;
    }

    static void bfs(int startX, int startY) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isValid(nx, ny) || visited[nx][ny] || map[nx][ny] == '*') {
                    continue;
                }

                visited[nx][ny] = true;

                // 주변 지뢰 수가 0인 칸만 큐에 넣어 탐색을 계속 이어감
                if (mineCount[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}