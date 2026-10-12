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
            parents[i] = -1;
        }
    }

    static int find(int a) {
        if (parents[a] < 0) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        if (parents[rootA] > parents[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;

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

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {

                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long dist = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, dist));
                }
            }

            Collections.sort(edges);

            makeSet();

            long mst = 0;
            int count = 0;

            for (Edge edge : edges) {

                if (union(edge.from, edge.to)) {

                    mst += edge.weight;
                    count++;

                    if (count == N - 1) {
                        break;
                    }
                }
            }

            long answer = Math.round(mst * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}