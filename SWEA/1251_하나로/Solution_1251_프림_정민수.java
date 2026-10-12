package algo;

import java.util.*;

public class 하나로_프림 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();

            long[] x = new long[n];
            long[] y = new long[n];

            for (int i = 0; i < n; i++) {
                x[i] = sc.nextLong();
            }

            for (int i = 0; i < n; i++) {
                y[i] = sc.nextLong();
            }

            double e = sc.nextDouble();

            boolean[] visited = new boolean[n];
            long[] minEdge = new long[n];

            Arrays.fill(minEdge, Long.MAX_VALUE);

            minEdge[0] = 0;

            long total = 0;

            for (int i = 0; i < n; i++) {

                int minVertex = -1;
                long min = Long.MAX_VALUE;

                for (int j = 0; j < n; j++) {

                    if (!visited[j] && minEdge[j] < min) {
                        min = minEdge[j];
                        minVertex = j;
                    }
                }

                visited[minVertex] = true;
                total += min;

                for (int j = 0; j < n; j++) {

                    if (visited[j]) {
                        continue;
                    }

                    long dx = x[minVertex] - x[j];
                    long dy = y[minVertex] - y[j];

                    long distance = dx * dx + dy * dy;

                    if (distance < minEdge[j]) {
                        minEdge[j] = distance;
                    }
                }
            }

            long answer = Math.round(total * e);

            System.out.println("#" + test_case + " " + answer);
        }
    }
}