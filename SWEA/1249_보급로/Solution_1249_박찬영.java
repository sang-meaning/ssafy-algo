import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] map;
    static int[][] dist;

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int dijkstra() {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];

            if (cost > dist[r][c]) {
                continue;
            }

            if (r == N - 1 && c == N - 1) {
                return cost;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N ||
                    nc < 0 || nc >= N) {
                    continue;
                }

                int newCost = cost + map[nr][nc];

                if (newCost < dist[nr][nc]) {

                    dist[nr][nc] = newCost;

                    pq.offer(new int[]{nr, nc, newCost});
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {

                String line = br.readLine();

                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            int answer = dijkstra();

            sb.append("#").append(tc)
              .append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
