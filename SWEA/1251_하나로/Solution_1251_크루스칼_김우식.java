import java.io.*;
import java.util.*;

public class Solution {

    static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static int N;
    static long[] x, y;
    static int[] parents;

    static void makeSet() {
        parents = new int[N];

        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            x = new long[N];
            y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            Edge[] edges = new Edge[N * (N - 1) / 2];

            int idx = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {

                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long dist = dx * dx + dy * dy;

                    edges[idx++] = new Edge(i, j, dist);
                }
            }

            Arrays.sort(edges);

            makeSet();

            long totalDistance = 0;
            int cnt = 0;
            
            for (Edge edge : edges) {

                if (union(edge.from, edge.to)) {

                    totalDistance += edge.weight;
                    cnt++;

                    if (cnt == N - 1) {
                        break;
                    }
                }
            }

            long answer = Math.round(totalDistance * E);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}