package swea;

import java.io.*;
import java.util.*;

public class Solution_7733_하상호 {

    static int N;
    static int[][] cheese;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int day;

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

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            cheese = new int[N][N];

            int maxDay = 0;

            for (int r = 0; r < N; r++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {

                    cheese[r][c] = Integer.parseInt(st.nextToken());

                    maxDay = Math.max(maxDay, cheese[r][c]);
                }
            }

            int answer = 1;

            // 0일째부터 확인
            for (day = 0; day <= maxDay; day++) {

                visited = new boolean[N][N];

                int count = 0;

                for (int r = 0; r < N; r++) {

                    for (int c = 0; c < N; c++) {

                        // 아직 먹히지 않은 치즈면서
                        // 아직 방문하지 않은 경우
                        if (cheese[r][c] > day && !visited[r][c]) {

                            bfs(r, c);

                            // BFS 한 번 = 치즈 한 덩어리
                            count++;
                        }
                    }
                }

                answer = Math.max(answer, count);
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void bfs(int sr, int sc) {

        Queue<Point> queue = new ArrayDeque<>();

        queue.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {

            Point cur = queue.poll();

            for (int d = 0; d < 4; d++) {

                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 범위를 벗어남
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                // 이미 방문
                if (visited[nr][nc]) {
                    continue;
                }

                // 이미 먹힌 치즈
                if (cheese[nr][nc] <= day) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new Point(nr, nc));
            }
        }
    }
}
