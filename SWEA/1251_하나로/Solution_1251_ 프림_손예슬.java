import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static long[] x, y;

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

            boolean[] visited = new boolean[N];

            long[] minEdge = new long[N];

            Arrays.fill(minEdge, Long.MAX_VALUE);

            minEdge[0] = 0;

            long mst = 0;

            for (int c = 0; c < N; c++) {

                long min = Long.MAX_VALUE;
                int current = -1;

                for (int i = 0; i < N; i++) {

                    if (!visited[i] && minEdge[i] < min) {
                        min = minEdge[i];
                        current = i;
                    }
                }

                visited[current] = true;
                mst += min;

                for (int next = 0; next < N; next++) {

                    if (visited[next]) continue;

                    long dx = x[current] - x[next];
                    long dy = y[current] - y[next];

                    long dist = dx * dx + dy * dy;

                    if (dist < minEdge[next]) {
                        minEdge[next] = dist;
                    }
                }
            }

            long answer = Math.round(mst * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}