import java.util.*;

public class Solution {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];

            int maxTaste = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    maxTaste = Math.max(maxTaste, map[i][j]);
                }
            }

            int answer = 1;

            for (int day = 1; day <= maxTaste; day++) {
                visited = new boolean[N][N];
                int count = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] > day && !visited[i][j]) {
                            dfs(i, j, day);
                            count++;
                        }
                    }
                }

                answer = Math.max(answer, count);
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int x, int y, int day) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (!visited[nx][ny] && map[nx][ny] > day) {
                dfs(nx, ny, day);
            }
        }
    }
}