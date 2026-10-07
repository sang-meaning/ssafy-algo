import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static long[] x;
    static long[] y;

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

            long result = 0;

            for (int count = 0; count < N; count++) {

                long min = Long.MAX_VALUE;
                int minVertex = -1;

                for (int i = 0; i < N; i++) {

                    if (!visited[i] && minEdge[i] < min) {
                        min = minEdge[i];
                        minVertex = i;
                    }
                }

                visited[minVertex] = true;
                result += min;

                for (int i = 0; i < N; i++) {

                    if (!visited[i]) {

                        long dx = x[minVertex] - x[i];
                        long dy = y[minVertex] - y[i];

                        long distance = dx * dx + dy * dy;

                        if (distance < minEdge[i]) {
                            minEdge[i] = distance;
                        }
                    }
                }
            }

            long answer = Math.round(result * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}