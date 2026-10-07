package swea;

import java.io.*;
import java.util.*;

public class Solution_1251_크루스칼_하상호 {
    static int N;
    static long[] x;
    static long[] y;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    // 대표 노드 찾기
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    // 두 집합 합치기
    static boolean union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        // 이미 같은 집합
        if (rootA == rootB) {
            return false;
        }

        parent[rootB] = rootA;

        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            x = new long[N];
            y = new long[N];

            // X 좌표 입력
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            // Y 좌표 입력
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            // 모든 간선 생성
            ArrayList<Edge> edges = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                for (int j = i + 1; j < N; j++) {

                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long distance = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, distance));
                }
            }

            // 간선 비용 기준 오름차순 정렬
            Collections.sort(edges);

            // Union-Find 초기화
            parent = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            long totalCost = 0;
            int count = 0;

            // Kruskal
            for (Edge edge : edges) {

                if (union(edge.from, edge.to)) {

                    totalCost += edge.cost;
                    count++;

                    // MST는 N-1개의 간선을 가짐
                    if (count == N - 1) {
                        break;
                    }
                }
            }

            long answer = Math.round(totalCost * E);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}