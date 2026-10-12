import java.io.*;
import java.util.*;

public class Solution {

    static class Edge {
        int from;
        int to;
        long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] parent;
    static int[] size;

    static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        if (size[rootA] < size[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        size[rootA] += size[rootB];

        return true;
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = fs.nextInt();

            long[] x = new long[N];
            long[] y = new long[N];

            for (int i = 0; i < N; i++) {
                x[i] = fs.nextLong();
            }

            for (int i = 0; i < N; i++) {
                y[i] = fs.nextLong();
            }

            double E = Double.parseDouble(fs.next());

            List<Edge> edges =
                    new ArrayList<>(N * (N - 1) / 2);

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long weight = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, weight));
                }
            }

            edges.sort(
                    Comparator.comparingLong(edge -> edge.weight)
            );

            parent = new int[N];
            size = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            long total = 0;
            int count = 0;

            for (Edge edge : edges) {
                // N == 1인 경우도 바로 종료
                if (count == N - 1) {
                    break;
                }

                // 이미 연결되어 있으면 제외
                if (!union(edge.from, edge.to)) {
                    continue;
                }

                total += edge.weight;
                count++;
            }

            long answer = Math.round(total * E);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static class FastScanner {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}