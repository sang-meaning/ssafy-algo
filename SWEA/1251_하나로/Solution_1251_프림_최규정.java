import java.io.*;
import java.util.*;

public class Solution {

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

            boolean[] visited = new boolean[N];

            long[] minDist = new long[N];
            Arrays.fill(minDist, Long.MAX_VALUE);

            minDist[0] = 0;

            long total = 0;

            for (int count = 0; count < N; count++) {

                int current = -1;
                long min = Long.MAX_VALUE;

                for (int i = 0; i < N; i++) {
                    if (!visited[i] && minDist[i] < min) {
                        min = minDist[i];
                        current = i;
                    }
                }

                visited[current] = true;
                total += minDist[current];

                for (int next = 0; next < N; next++) {
                    if (visited[next]) {
                        continue;
                    }

                    long dx = x[current] - x[next];
                    long dy = y[current] - y[next];
                    long distance = dx * dx + dy * dy;

                    if (distance < minDist[next]) {
                        minDist[next] = distance;
                    }
                }
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