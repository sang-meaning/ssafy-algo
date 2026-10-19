import java.io.*;
import java.util.*;

public class Solution_5656_정서우 {
    static int N, W, H, minBricks;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Point {
        int r, c, range;

        Point(int r, int c, int range) {
            this.r = r;
            this.c = c;
            this.range = range;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minBricks = Integer.MAX_VALUE;
            dfs(0, map);

            System.out.println("#" + tc + " " + minBricks);
        }
    }

    static void dfs(int count, int[][] currentMap) {
        if (count == N) {
            minBricks = Math.min(minBricks, countRemaining(currentMap));
            return;
        }

        for (int col = 0; col < W; col++) {
            int row = 0;
            while (row < H && currentMap[row][col] == 0) {
                row++;
            }

            if (row == H) {
                dfs(count + 1, currentMap);
                continue;
            }

            int[][] nextMap = copyMap(currentMap);

            boom(nextMap, row, col);

            dropBricks(nextMap);

            dfs(count + 1, nextMap);
        }
    }

    static void boom(int[][] curMap, int startR, int startC) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(startR, startC, curMap[startR][startC]));
        curMap[startR][startC] = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int range = p.range;

            if (range == 1)
                continue;

            for (int d = 0; d < 4; d++) {
                for (int dist = 1; dist < range; dist++) {
                    int nr = p.r + dx[d] * dist;
                    int nc = p.c + dy[d] * dist;

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W)
                        break;
                    if (curMap[nr][nc] == 0)
                        continue;

                    q.offer(new Point(nr, nc, curMap[nr][nc]));
                    curMap[nr][nc] = 0;
                }
            }
        }
    }

    static void dropBricks(int[][] curMap) {
        Queue<Integer> q = new ArrayDeque<>();

        for (int c = 0; c < W; c++) {
            for (int r = H - 1; r >= 0; r--) {
                if (curMap[r][c] > 0) {
                    q.offer(curMap[r][c]);
                    curMap[r][c] = 0;
                }
            }

            int r = H - 1;
            while (!q.isEmpty()) {
                curMap[r--][c] = q.poll();
            }
        }
    }

    static int[][] copyMap(int[][] origin) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            System.arraycopy(origin[i], 0, copy[i], 0, W);
        }
        return copy;
    }

    static int countRemaining(int[][] curMap) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (curMap[i][j] > 0)
                    cnt++;
            }
        }
        return cnt;
    }
}