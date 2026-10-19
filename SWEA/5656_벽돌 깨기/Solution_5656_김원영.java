
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int N, W, H;
    static int min;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int depth, int[][] map) {
        if (depth == N) {
            int count = 0;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] != 0) {
                        count++;
                    }
                }
            }

            min = Math.min(min, count);
            return;
        }

        for (int j = 0; j < W; j++) {
            int[][] temp = new int[H][W];

            for (int i = 0; i < H; i++) {
                temp[i] = map[i].clone();
            }

            for (int i = 0; i < H; i++) {
                if (temp[i][j] != 0) {
                    bfs(i, j, temp);
                    gravity(temp);
                    break;
                }
            }

            dfs(depth + 1, temp);
        }
    }

    static void bfs(int r, int c, int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int row = now[0];
            int col = now[1];
            int power = map[row][col];

            if (power == 0) {
                continue;
            }

            map[row][col] = 0;

            for (int d = 0; d < 4; d++) {
                for (int k = 1; k < power; k++) {
                    int nr = row + dr[d] * k;
                    int nc = col + dc[d] * k;

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        break;
                    }

                    if (map[nr][nc] != 0) {
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    static void gravity(int[][] map) {
        for (int j = 0; j < W; j++) {
            int bottom = H - 1;

            for (int i = H - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    int num = map[i][j];
                    map[i][j] = 0;
                    map[bottom][j] = num;
                    bottom--;
                }
            }
        }
    }

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

            min = Integer.MAX_VALUE;
            dfs(0, map);

            System.out.println("#" + tc + " " + min);
        }

        sc.close();
    }
}
