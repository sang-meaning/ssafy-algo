import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

    public static int[] parent;

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean isUnion(int u, int v) {
        return find(u) == find(v);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u < v) parent[v] = u;
        else parent[u] = v;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            long[] x = new long[N];
            long[] y = new long[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            ArrayList<double[]> edges = new ArrayList<>();

            for (int i=0; i<N; i++) {
                for (int j=i+1; j<N; j++) {
                    edges.add(new double[] {i, j, (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])});
                }
            }

            Collections.sort(edges, (a,b) -> {
                return Double.compare(a[2], b[2]);
            });

            parent = new int[N];

            for (int i=0; i<N; i++) parent[i] = i;

            double total = 0;
            int cnt = 0;

            for (double[] edge : edges) {
                int u = (int) edge[0];
                int v = (int) edge[1];
                double cost = edge[2];

                if (!isUnion(u, v)) {
                    union(u, v);
                    total += cost;
                    cnt++;

                    if (cnt == N-1) break;
                }
            }

            long answer = Math.round(total * E);
            sb.append('#').append(test_case).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}