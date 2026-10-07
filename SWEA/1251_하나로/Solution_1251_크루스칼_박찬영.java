import java.io.*;
import java.util.*;

public class Solution {

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

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        parent[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

            ArrayList<Edge> edges = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {

                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long cost = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, cost));
                }
            }

            Collections.sort(edges);

            parent = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            long result = 0;
            int count = 0;

            for (Edge edge : edges) {

                if (union(edge.from, edge.to)) {

                    result += edge.cost;
                    count++;

                    if (count == N - 1) {
                        break;
                    }
                }
            }

            long answer = Math.round(result * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}