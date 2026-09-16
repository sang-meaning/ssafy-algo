import java.util.Scanner;

public class Solution {

    static int N;
    static char[][] map;
    static int[][] mineCount;
    static boolean[][] visited;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1,  0,  1,-1, 1,-1, 0, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            N = sc.nextInt();

            map = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];

            for (int x = 0; x < N; x++) {
                String line = sc.next();

                for (int y = 0; y < N; y++) {
                    map[x][y] = line.charAt(y);
                }
            }

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {

                    if (map[x][y] == '*') {
                        continue;
                    }

                    int count = 0;

                    for (int d = 0; d < 8; d++) {

                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }

                        if (map[nx][ny] == '*') {
                            count++;
                        }
                    }

                    mineCount[x][y] = count;
                }
            }

            int answer = 0;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {

                    if (map[x][y] == '.'
                            && mineCount[x][y] == 0
                            && !visited[x][y]) {

                        answer++;
                        dfs(x, y);
                    }
                }
            }

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {

                    if (map[x][y] == '.'
                            && !visited[x][y]) {

                        answer++;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int x, int y) {

        visited[x][y] = true;

        if (mineCount[x][y] != 0) {
            return;
        }

        for (int d = 0; d < 8; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (map[nx][ny] == '*') {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            dfs(nx, ny);
        }
    }
}