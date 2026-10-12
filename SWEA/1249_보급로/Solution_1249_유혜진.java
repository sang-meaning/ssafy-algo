import java.util.*;

// SWEA 1249. 보급로 - 다익스트라
public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            int[][] dist = new int[N][N];
            for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
            dist[0][0] = 0;

            // {복구 시간, 행, 열}
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            pq.offer(new int[]{0, 0, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0], r = cur[1], c = cur[2];
                if (cost > dist[r][c]) continue;
                if (r == N - 1 && c == N - 1) break;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    int next = cost + map[nr][nc];
                    if (next < dist[nr][nc]) {
                        dist[nr][nc] = next;
                        pq.offer(new int[]{next, nr, nc});
                    }
                }
            }

            System.out.println("#" + tc + " " + dist[N - 1][N - 1]);
        }
    }
}