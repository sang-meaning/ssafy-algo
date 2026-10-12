import java.io.*;
import java.util.*;

public class Solution_7733_임성진 {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        in.nextToken(); int T = (int) in.nval;
        for (int tc = 1; tc <= T; tc++) {
            in.nextToken(); int N = (int) in.nval;
            int[][] a = new int[N][N];
            int maxH = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    in.nextToken();
                    a[i][j] = (int) in.nval;
                    if (a[i][j] > maxH) maxH = a[i][j];
                }

            int answer = 0;
            for (int day = 0; day < maxH; day++) {   // day일 경과 -> 두께 <= day 는 녹음
                boolean[][] visited = new boolean[N][N];
                int count = 0;
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++) {
                        if (a[i][j] <= day || visited[i][j]) continue;
                        count++;
                        bfs(a, visited, i, j, day, N);
                    }
                if (count > answer) answer = count;
            }
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    static void bfs(int[][] a, boolean[][] visited, int r, int c, int day, int N) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d], nc = cur[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (visited[nr][nc] || a[nr][nc] <= day) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }
    }
}