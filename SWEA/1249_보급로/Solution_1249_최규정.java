import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int r;
        int c;
        int cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int N;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int dijkstra() {
        int[][] dist = new int[N][N];

        for (int r = 0; r < N; r++) {
            Arrays.fill(dist[r], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));

        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist[current.r][current.c]) {
                continue;
            }

            if (current.r == N - 1 && current.c == N - 1) {
                return current.cost;
            }

            for (int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                int nextCost = current.cost + map[nr][nc];

                if (nextCost < dist[nr][nc]) {
                    dist[nr][nc] = nextCost;
                    pq.offer(new Node(nr, nc, nextCost));
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                String line = br.readLine().trim();

                for (int c = 0; c < N; c++) {
                    map[r][c] = line.charAt(c) - '0';
                }
            }

            sb.append('#').append(tc).append(' ').append(dijkstra()).append('\n');
        }

        System.out.print(sb);
    }
}