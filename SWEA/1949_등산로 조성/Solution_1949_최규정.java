import java.util.*;

public class Solution {
    static int N, K, answer;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            K = sc.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];
            answer = 0;

            int maxHeight = 0;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    map[x][y] = sc.nextInt();
                    maxHeight = Math.max(maxHeight, map[x][y]);
                }
            }

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == maxHeight) {
                        visited[x][y] = true;
                        dfs(x, y, 1, false);
                        visited[x][y] = false;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int x, int y, int length, boolean usedCut) {
        answer = Math.max(answer, length);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            if (map[nx][ny] < map[x][y]) {
                visited[nx][ny] = true;

                dfs(nx, ny, length + 1, usedCut);

                visited[nx][ny] = false;
            }

            else if (!usedCut) {
                int targetHeight = map[x][y] - 1;
                int cutAmount = map[nx][ny] - targetHeight;

                if (cutAmount <= K) {
                    int originalHeight = map[nx][ny];

                    map[nx][ny] = targetHeight;
                    visited[nx][ny] = true;

                    dfs(nx, ny, length + 1, true);

                    visited[nx][ny] = false;
                    map[nx][ny] = originalHeight;
                }
            }
        }
    }
}