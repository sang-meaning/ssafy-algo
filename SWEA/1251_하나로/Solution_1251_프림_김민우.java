import java.io.*;
import java.util.*;

public class Solution {

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

            boolean[] visited = new boolean[N];
            long[] minDistance = new long[N];

            Arrays.fill(minDistance, Long.MAX_VALUE);
            minDistance[0] = 0;

            long mstCost = 0;

            // N개의 정점을 MST에 포함
            for (int count = 0; count < N; count++) {
                int current = -1;
                long minCost = Long.MAX_VALUE;

                // 아직 방문하지 않은 정점 중 연결 비용이 가장 작은 정점 선택
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && minDistance[i] < minCost) {
                        minCost = minDistance[i];
                        current = i;
                    }
                }

                visited[current] = true;
                mstCost += minCost;

                // 현재 정점을 거쳐 연결하는 비용으로 갱신
                for (int next = 0; next < N; next++) {
                    if (!visited[next]) {
                        long dx = x[current] - x[next];
                        long dy = y[current] - y[next];
                        long distanceSquared = dx * dx + dy * dy;

                        if (distanceSquared < minDistance[next]) {
                            minDistance[next] = distanceSquared;
                        }
                    }
                }
            }

            long result = Math.round(mstCost * E);

            answer.append('#')
                  .append(tc)
                  .append(' ')
                  .append(result)
                  .append('\n');
        }

        System.out.print(answer);
    }
}