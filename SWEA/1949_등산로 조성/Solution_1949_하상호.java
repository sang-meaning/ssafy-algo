package swea;

import java.io.*;
import java.util.*;

public class Solution_1949_하상호 {

    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            int maxHeight = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[r][c]);
                }
            }

            answer = 0;

            // 가장 높은 봉우리 모두에서 시작
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == maxHeight) {

                        visited = new boolean[N][N];
                        visited[r][c] = true;

                        dfs(r, c, 1, false);
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(int r, int c, int length, boolean usedCut) {

        answer = Math.max(answer, length);

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (visited[nr][nc]) continue;

            // 그냥 내려갈 수 있는 경우
            if (map[nr][nc] < map[r][c]) {

                visited[nr][nc] = true;

                dfs(nr, nc, length + 1, usedCut);

                visited[nr][nc] = false;
            }

            // 공사를 아직 사용하지 않았을 경우
            else if (!usedCut) {

                // 현재 높이보다 딱 1 낮게 만들기 위해 필요한 양
                int needCut = map[nr][nc] - map[r][c] + 1;

                if (needCut <= K) {

                    int originalHeight = map[nr][nc];

                    // 현재 높이보다 1 낮게 깎음
                    map[nr][nc] = map[r][c] - 1;

                    visited[nr][nc] = true;

                    dfs(nr, nc, length + 1, true);

                    visited[nr][nc] = false;

                    // 원상복구
                    map[nr][nc] = originalHeight;
                }
            }
        }
    }
}