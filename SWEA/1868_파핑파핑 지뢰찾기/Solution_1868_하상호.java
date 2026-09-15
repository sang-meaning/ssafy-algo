package cording_prac;

import java.io.*;
import java.util.*;

public class Solution_1868_하상호 {

    static int N;
    static char[][] map;
    static int[][] mineCnt;
    static boolean[][] visited;

    // 8방향
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            map = new char[N][N];
            mineCnt = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 각 칸 주변의 지뢰 개수 계산
            calculateMineCount();

            int answer = 0;

            // 1. 주변 지뢰가 0인 칸부터 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' &&
                        !visited[r][c] &&
                        mineCnt[r][c] == 0) {

                        bfs(r, c);
                        answer++;
                    }
                }
            }

            // 2. BFS로 열리지 않은 일반 칸들은 각각 클릭해야 함
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' && !visited[r][c]) {
                        answer++;
                    }
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }

    // 모든 빈 칸에 대해 주변 지뢰 개수 계산
    static void calculateMineCount() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                // 지뢰 칸은 계산하지 않음
                if (map[r][c] == '*') {
                    mineCnt[r][c] = -1;
                    continue;
                }

                int count = 0;

                for (int d = 0; d < 8; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!isRange(nr, nc)) {
                        continue;
                    }

                    if (map[nr][nc] == '*') {
                        count++;
                    }
                }

                mineCnt[r][c] = count;
            }
        }
    }

    // 0인 칸을 클릭했을 때 연쇄적으로 열리는 칸 처리
    static void bfs(int startR, int startC) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];

            // 주변 지뢰가 있는 칸이면
            // 해당 칸까지만 열리고 더 이상 확장하지 않음
            if (mineCnt[r][c] != 0) {
                continue;
            }

            for (int d = 0; d < 8; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isRange(nr, nc)) {
                    continue;
                }

                if (map[nr][nc] == '*') {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
