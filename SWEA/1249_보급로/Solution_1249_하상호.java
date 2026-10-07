package swea;

import java.io.*;
import java.util.*;

public class Solution_1249_하상호 {

    static final int INF = 100_000_000;

    static int N;
    static int[][] graph;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int dist;
        int r;
        int c;

        Node(int dist, int r, int c) {
            this.dist = dist;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int dijkstra() {

        int[][] distance = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], INF);
        }

        distance[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            int curDist = current.dist;
            int currR = current.r;
            int currC = current.c;

            // 이미 더 짧은 경로를 찾았다면 넘어감
            if (curDist > distance[currR][currC]) {
                continue;
            }

            // 목적지에 도착했다면 종료
            if (currR == N - 1 && currC == N - 1) {
                return curDist;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {

                int ny = currR + dy[i];
                int nx = currC + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < N) {

                    int newDist = curDist + graph[ny][nx];

                    if (newDist < distance[ny][nx]) {
                        distance[ny][nx] = newDist;
                        pq.offer(new Node(newDist, ny, nx));
                    }
                }
            }
        }

        return distance[N - 1][N - 1];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine().trim());

            graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine().trim();

                for (int j = 0; j < N; j++) {
                    graph[i][j] = line.charAt(j) - '0';
                }
            }

            int result = dijkstra();

            System.out.println("#" + tc + " " + result);
        }
    }
}
