import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int u, v;
        long distSq;

        public Edge(int u, int v, long distSq) {
            this.u = u;
            this.v = v;
            this.distSq = distSq;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.distSq, o.distSq);
        }
    }

    static int[] parent;

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            long[] X = new long[N];
            long[] Y = new long[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine().trim());

            // 모든 가능한 간선 생성 (N*(N-1)/2 개)
            int totalEdges = N * (N - 1) / 2;
            Edge[] edges = new Edge[totalEdges];
            int idx = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long distSq = (X[i] - X[j]) * (X[i] - X[j])
                                + (Y[i] - Y[j]) * (Y[i] - Y[j]);
                    edges[idx++] = new Edge(i, j, distSq);
                }
            }

            // 간선 오름차순 정렬
            Arrays.sort(edges);

            // Disjoint Set 초기화
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            int count = 0;
            long totalDistSq = 0;

            for (Edge edge : edges) {
                if (union(edge.u, edge.v)) {
                    totalDistSq += edge.distSq;
                    count++;
                    if (count == N - 1) break; // 트리 완성
                }
            }

            long ans = Math.round(totalDistSq * E);
            System.out.println("#" + tc + " " + ans);
        }
    }
}