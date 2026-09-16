package swea;

import java.io.*;
import java.util.*;

public class Solution_1227_하상호 {

    static final int N = 100;

    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {

            // 입력으로 테스트 케이스 번호가 주어짐
            int testCase = Integer.parseInt(br.readLine());

            map = new int[N][N];
            visited = new boolean[N][N];

            int startR = 0;
            int startC = 0;

            for (int r = 0; r < N; r++) {

                String line = br.readLine();

                for (int c = 0; c < N; c++) {

                    map[r][c] = line.charAt(c) - '0';

                    if (map[r][c] == 2) {
                        startR = r;
                        startC = c;
                    }
                }
            }

            int answer = bfs(startR, startC);

            System.out.println("#" + testCase + " " + answer);
        }
    }

    static int bfs(int sr, int sc) {

        Queue<Point> queue = new ArrayDeque<>();

        queue.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {

            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {

                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 범위 밖
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                // 이미 방문
                if (visited[nr][nc]) {
                    continue;
                }

                // 벽
                if (map[nr][nc] == 1) {
                    continue;
                }

                // 목적지 발견
                if (map[nr][nc] == 3) {
                    return 1;
                }

                visited[nr][nc] = true;

                queue.offer(new Point(nr, nc));
            }
        }

        // 도착하지 못함
        return 0;
    }
}
