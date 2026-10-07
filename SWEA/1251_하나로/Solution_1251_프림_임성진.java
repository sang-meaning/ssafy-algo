import java.io.*;
import java.util.*;

public class Solution_1251_프림_임성진 {
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");

    static String next() throws IOException {
        while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
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

            long[] minEdge = new long[N];
            boolean[] visited = new boolean[N];
            Arrays.fill(minEdge, Long.MAX_VALUE);
            minEdge[0] = 0;

            long total = 0;
            for (int cnt = 0; cnt < N; cnt++) {
                int u = -1;
                long min = Long.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && minEdge[i] < min) {
                        min = minEdge[i];
                        u = i;
                    }
                }
                visited[u] = true;
                total += min;

                for (int v = 0; v < N; v++) {
                    if (visited[v]) continue;
                    long dx = x[u] - x[v], dy = y[u] - y[v];
                    long d = dx * dx + dy * dy;
                    if (d < minEdge[v]) minEdge[v] = d;
                }
            }

            sb.append('#').append(tc).append(' ').append(Math.round(total * E)).append('\n');
        }
        System.out.print(sb);
    }
}