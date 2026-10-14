import java.util.*;

public class Solution {

    static int N, W, H, answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            int[][] map = new int[H][W];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            answer = Integer.MAX_VALUE;

            dfs(0, map);

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int depth, int[][] map) {

        int count = countBricks(map);

        if (count == 0) {
            answer = 0;
            return;
        }

        if (depth == N) {
            answer = Math.min(answer, count);
            return;
        }

        for (int c = 0; c < W; c++) {

            if (answer == 0) {
                return;
            }

            int[][] copy = new int[H][W];

            for (int i = 0; i < H; i++) {
                copy[i] = map[i].clone();
            }

            int r = 0;

            while (r < H && copy[r][c] == 0) {
                r++;
            }

            if (r == H) {
                dfs(depth + 1, copy);
                continue;
            }

            bfs(r, c, copy);

            gravity(copy);

            dfs(depth + 1, copy);
        }
    }

    static void bfs(int r, int c, int[][] map) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{r, c, map[r][c]});
        map[r][c] = 0;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];
            int power = cur[2];

            for (int d = 0; d < 4; d++) {

                for (int k = 1; k < power; k++) {

                    int nx = x + dr[d] * k;
                    int ny = y + dc[d] * k;

                    if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
                        continue;
                    }

                    if (map[nx][ny] == 0) {
                        continue;
                    }

                    queue.offer(new int[]{
                        nx, ny, map[nx][ny]
                    });

                    map[nx][ny] = 0;
                }
            }
        }
    }

    static void gravity(int[][] map) {

        for (int c = 0; c < W; c++) {

            int idx = H - 1;

            for (int r = H - 1; r >= 0; r--) {

                if (map[r][c] != 0) {

                    int value = map[r][c];

                    map[r][c] = 0;
                    map[idx][c] = value;

                    idx--;
                }
            }
        }
    }

    static int countBricks(int[][] map) {

        int count = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {

                if (map[i][j] != 0) {
                    count++;
                }
            }
        }

        return count;
    }
}