import java.io.*;
import java.util.*;

public class Solution_1251_크루스칼_임성진 {
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static int[] parent;

    static String next() throws IOException {
        while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(cost, o.cost);
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        parent[rb] = ra;
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(next());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(next());
            long[] x = new long[N], y = new long[N];
            for (int i = 0; i < N; i++) x[i] = Long.parseLong(next());
            for (int i = 0; i < N; i++) y[i] = Long.parseLong(next());
            double E = Double.parseDouble(next());

            Edge[] edges = new Edge[N * (N - 1) / 2];
            int idx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j], dy = y[i] - y[j];
                    edges[idx++] = new Edge(i, j, dx * dx + dy * dy);
                }
            }
            Arrays.sort(edges);

            parent = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;

            long total = 0;
            int picked = 0;
            for (Edge e : edges) {
                if (union(e.from, e.to)) {
                    total += e.cost;
                    if (++picked == N - 1) break;
                }
            }

            sb.append('#').append(tc).append(' ').append(Math.round(total * E)).append('\n');
        }
        System.out.print(sb);
    }
}