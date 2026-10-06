import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
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

            long[] dist = new long[N];
            boolean[] visited = new boolean[N];

            for (int i=0; i<N; i++) dist[i] = Long.MAX_VALUE;
            dist[0] = 0;

            long total = 0;
            for (int i=0; i<N; i++) {
                int now = -1;
                long mini = Long.MAX_VALUE;

                for (int j=0; j<N; j++) {
                    if (visited[j]) continue;
                    if (mini > dist[j]) {
                        mini = dist[j];
                        now = j;
                    }
                }

                visited[now] = true;
                total += dist[now];

                for (int j=0; j<N; j++) {
                    if (visited[j]) continue;
                    long cost = (x[now] - x[j]) * (x[now] - x[j]) + (y[now] - y[j]) * (y[now] - y[j]);

                    if (dist[j] > cost) {
                        dist[j] = cost;
                    }
                }
            }

            long answer = Math.round(total * E);
            sb.append('#').append(test_case).append(' ').append(answer).append('\n');
        }

        System.out.println(sb);
    }
}