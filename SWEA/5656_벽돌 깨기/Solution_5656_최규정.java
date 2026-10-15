import java.util.*;

class Solution {

    static int N, W, H;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            N = sc.nextInt(); // 구슬 개수
            W = sc.nextInt(); // 가로 길이
            H = sc.nextInt(); // 세로 길이

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

        for (int col = 0; col < W; col++) {

            int row = -1;

            for (int r = 0; r < H; r++) {

                if (map[r][col] != 0) {
                    row = r;
                    break;
                }
            }

            if (row == -1) {
                continue;
            }

            int[][] copy = copyMap(map);

            bfs(copy, row, col);

            gravity(copy);

            dfs(depth + 1, copy);

            if (answer == 0) {
                return;
            }
        }
    }

    static void bfs(int[][] map, int startR, int startC) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
            startR, startC, map[startR][startC]
        });

        // 시작 벽돌 제거
        map[startR][startC] = 0;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int power = current[2];

            for (int d = 0; d < 4; d++) {

                for (int k = 1; k < power; k++) {

                    int nr = r + dr[d] * k;
                    int nc = c + dc[d] * k;

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        break;
                    }

                    if (map[nr][nc] == 0) {
                        continue;
                    }

                    int nextPower = map[nr][nc];


                    if (nextPower > 1) {

                        queue.offer(new int[]{nr, nc, nextPower});
                    }

                    map[nr][nc] = 0;
                }
            }
        }
    }

    static void gravity(int[][] map) {

        for (int col = 0; col < W; col++) {

            int write = H - 1;

            for (int row = H - 1; row >= 0; row--) {

                if (map[row][col] != 0) {

                    int brick = map[row][col];

                    map[row][col] = 0;
                    map[write][col] = brick;

                    write--;
                }
            }
        }
    }

    static int[][] copyMap(int[][] map) {

        int[][] copy = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copy[i][j] = map[i][j];
            }
        }

        return copy;
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