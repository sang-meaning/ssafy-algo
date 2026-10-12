import java.io.*;
import java.util.*;

public class Solution {

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
        public int compareTo(Edge other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    static int[] parent;

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
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            long[] x = new long[N];
            long[] y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            // 모든 섬 사이의 간선 생성
            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    long distanceSquared = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, distanceSquared));
                }
            }

            Collections.sort(edges);

            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            long mstDistanceSquared = 0;
            int selectedCount = 0;

            for (Edge edge : edges) {
                if (union(edge.from, edge.to)) {
                    mstDistanceSquared += edge.cost;
                    selectedCount++;

                    if (selectedCount == N - 1) {
                        break;
                    }
                }
            }

            long result = Math.round(mstDistanceSquared * E);

            answer.append('#')
                  .append(tc)
                  .append(' ')
                  .append(result)
                  .append('\n');
        }

        System.out.print(answer);
    }
}