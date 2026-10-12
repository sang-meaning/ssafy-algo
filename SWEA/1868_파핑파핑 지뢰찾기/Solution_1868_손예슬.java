import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new char[N][N];
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().toCharArray();
            }

            int answer = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' &&
                        !visited[r][c] &&
                        countMine(r, c) == 0) {

                        bfs(r, c);
                        answer++;
                    }
                }
            }


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

    static void bfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];

            if (countMine(r, c) != 0) continue;

            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (map[nr][nc] == '*') continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;

                if (countMine(nr, nc) == 0) {
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    static int countMine(int r, int c) {
        int count = 0;

        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

            if (map[nr][nc] == '*') {
                count++;
            }
        }

        return count;
    }
}