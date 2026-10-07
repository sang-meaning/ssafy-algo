import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[][] map, dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r, c, cost;
        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int dijkstra() {
        dist = new int[N][N];
        for(int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.cost, b.cost));

        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            // 이미 더 짧은 경로가 존재
            if(cur.cost != dist[cur.r][cur.c]) continue;

            if(cur.r == N-1 && cur.c == N-1) return cur.cost;

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                int nCost = cur.cost + map[nr][nc];

                if(nCost < dist[nr][nc]) {
                    dist[nr][nc] = nCost;
                    pq.offer(new Node(nr, nc, nCost));
                }
            }
        }
        return dist[N-1][N-1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int r=0; r<N; r++) {
                String s = br.readLine();
                for(int c=0; c<N; c++) map[r][c] = s.charAt(c) - '0';
            }

            sb.append("#").append(tc).append(" ")
              .append(dijkstra()).append("\n");
        }

        System.out.print(sb);
    }
}