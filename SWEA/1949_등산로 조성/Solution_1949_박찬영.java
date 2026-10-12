import java.io.*;
import java.util.*;

public class Solution {
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int maxLen;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            int maxHeight = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            maxLen = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, 1, false);
                        visited[i][j] = false; 
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int r, int c, int len, boolean isCut) {
        maxLen = Math.max(maxLen, len);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
                continue;
            }

            if (map[nr][nc] < map[r][c]) {
                visited[nr][nc] = true;
                dfs(nr, nc, len + 1, isCut);
                visited[nr][nc] = false;
            }
            else if (!isCut && map[nr][nc] - K < map[r][c]) {
                int originalHeight = map[nr][nc]; 

                visited[nr][nc] = true;
                map[nr][nc] = map[r][c] - 1;

                dfs(nr, nc, len + 1, true);

                map[nr][nc] = originalHeight;
                visited[nr][nc] = false;
            }
        }
    }
}