import java.util.Scanner;

class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            K = sc.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];

            int maxHeight = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            answer = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, 1, false);
                        visited[i][j] = false;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }

        sc.close();
    }

    static void dfs(int r, int c, int length, boolean cutUsed) {
        answer = Math.max(answer, length);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                continue;
            }

            if (visited[nr][nc]) {
                continue;
            }

            if (map[nr][nc] < map[r][c]) {
                visited[nr][nc] = true;
                dfs(nr, nc, length + 1, cutUsed);
                visited[nr][nc] = false;
            } else if (!cutUsed && map[nr][nc] - K < map[r][c]) {
                int original = map[nr][nc];

                map[nr][nc] = map[r][c] - 1;
                visited[nr][nc] = true;

                dfs(nr, nc, length + 1, true);

                visited[nr][nc] = false;
                map[nr][nc] = original;
            }
        }
    }
}