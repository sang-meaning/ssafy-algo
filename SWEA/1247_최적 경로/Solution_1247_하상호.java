package swea;

import java.io.*;
import java.util.*;

public class Solution_1247_하상호 {

    static int N;
    static int[][] pos;
    static int homeX, homeY;

    static class Node implements Comparable<Node> {
        int dist;
        int current;
        int mask;

        Node(int dist, int current, int mask) {
            this.dist = dist;
            this.current = current;
            this.mask = mask;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static int dijkstra(int companyX, int companyY) {

        int maxMask = 1 << N;
        int INF = Integer.MAX_VALUE;

        // distance[방문한 고객 집합][현재 위치]
        int[][] distance = new int[maxMask][N];

        for (int i = 0; i < maxMask; i++) {
            Arrays.fill(distance[i], INF);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 회사에서 각각의 고객으로 출발
        for (int i = 0; i < N; i++) {

            int mask = 1 << i;

            int dist = getDistance(
                    companyX,
                    companyY,
                    pos[i][0],
                    pos[i][1]
            );

            distance[mask][i] = dist;

            pq.offer(new Node(dist, i, mask));
        }

        int answer = INF;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            int curDist = cur.dist;
            int current = cur.current;
            int mask = cur.mask;

            if (curDist > distance[mask][current]) {
                continue;
            }

            // 모든 고객 방문
            if (mask == maxMask - 1) {

                int totalDist =
                        curDist
                        + getDistance(
                            pos[current][0],
                            pos[current][1],
                            homeX,
                            homeY
                        );

                answer = Math.min(answer, totalDist);

                continue;
            }

            // 다음 고객 선택
            for (int next = 0; next < N; next++) {

                // 이미 방문한 고객
                if ((mask & (1 << next)) != 0) {
                    continue;
                }

                int nextMask = mask | (1 << next);

                int nextDist =
                        curDist
                        + getDistance(
                            pos[current][0],
                            pos[current][1],
                            pos[next][0],
                            pos[next][1]
                        );

                if (nextDist < distance[nextMask][next]) {

                    distance[nextMask][next] = nextDist;

                    pq.offer(
                        new Node(
                            nextDist,
                            next,
                            nextMask
                        )
                    );
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            int companyX = Integer.parseInt(st.nextToken());
            int companyY = Integer.parseInt(st.nextToken());

            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());

            pos = new int[N][2];

            for (int i = 0; i < N; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }

            int result = dijkstra(companyX, companyY);

            System.out.println("#" + tc + " " + result);
        }
    }
}