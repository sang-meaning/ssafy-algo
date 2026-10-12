import java.io.*;
import java.util.*;

public class Solution_1227_임성진 {
    static final int N = 100;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {
            int tc = Integer.parseInt(br.readLine().trim());

            char[][] map = new char[N][];
            int sr = 0, sc = 0;
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++)
                    if (map[i][j] == '2') { sr = i; sc = j; }
            }

            boolean[][] visited = new boolean[N][N];
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{sr, sc});
            visited[sr][sc] = true;

            int result = 0;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (map[cur[0]][cur[1]] == '3') { result = 1; break; }
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d], nc = cur[1] + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                    if (visited[nr][nc] || map[nr][nc] == '1') continue;
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.print(sb);
    }
}