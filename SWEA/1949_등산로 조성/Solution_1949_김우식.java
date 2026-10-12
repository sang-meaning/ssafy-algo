package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949 {

    static int T, N, K, MAX_HEIGHT;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];

            MAX_HEIGHT = 0;
            answer = 0;

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {

                    map[i][j] = Integer.parseInt(st.nextToken());

                    MAX_HEIGHT = Math.max(MAX_HEIGHT, map[i][j]);
                }
            }

            // 가장 높은 봉우리에서 DFS 시작
            for (int i = 0; i < N; i++) {

                for (int j = 0; j < N; j++) {

                    if (map[i][j] == MAX_HEIGHT) {

                        visited[i][j] = true;

                        // 시작 지점도 길이에 포함
                        dfs(i, j, 1, false);

                        visited[i][j] = false;
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

    static void dfs(int r, int c, int cnt, boolean cut) {

        // 현재까지 만든 등산로 길이 갱신
        answer = Math.max(answer, cnt);

        for (int dir = 0; dir < 4; dir++) {

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            // 지도 밖
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                continue;
            }

            // 이미 지나온 곳
            if (visited[nr][nc]) {
                continue;
            }

            // 1. 다음 지형이 현재보다 낮으면 그냥 이동
            if (map[nr][nc] < map[r][c]) {

                visited[nr][nc] = true;

                dfs(nr, nc, cnt + 1, cut);

                visited[nr][nc] = false;
            }

            // 2. 다음 지형이 높거나 같은데
            // 아직 공사를 사용하지 않았다면
            else if (!cut) {

                if (map[nr][nc] - K < map[r][c]) {

                    int original = map[nr][nc];

                    // 현재 높이보다 딱 1 낮게 깎기
                    map[nr][nc] = map[r][c] - 1;

                    visited[nr][nc] = true;

                    dfs(nr, nc, cnt + 1, true);

                    visited[nr][nc] = false;

                    // 원래 높이로 복구
                    map[nr][nc] = original;
                }
            }
        }
    }
}