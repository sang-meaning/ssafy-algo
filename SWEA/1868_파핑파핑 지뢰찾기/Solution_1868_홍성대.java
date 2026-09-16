import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static int N;
    static char[][] pool;
    static int[][] mineCnt;
    static boolean[][] visited;

    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static void countMines() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pool[i][j] == '*') {
                    mineCnt[i][j] = -1;
                    continue;
                }
                int cnt = 0;
                for (int dir = 0; dir < 8; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (pool[nx][ny] == '*') cnt++;
                    }
                }
                mineCnt[i][j] = cnt;
            }
        }
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{ sx, sy });
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            if (mineCnt[x][y] != 0) continue;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && mineCnt[nx][ny] != -1) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{ nx, ny });
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            pool = new char[N][N];
            mineCnt = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < N; j++) {
                    pool[i][j] = line.charAt(j);
                }
            }

            countMines();

            int clickCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineCnt[i][j] == 0 && !visited[i][j]) {
                        clickCount++;
                        bfs(i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineCnt[i][j] > 0 && !visited[i][j]) {
                        clickCount++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(clickCount).append("\n");
        }
        System.out.print(sb);
    }
}