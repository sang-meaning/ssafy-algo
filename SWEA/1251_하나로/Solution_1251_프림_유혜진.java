import java.io.*;
import java.util.*;

// SWEA 1251. 하나로 - 프림(Prim) 풀이
// 모든 섬이 서로 연결 가능한 완전 그래프이므로 배열 기반 O(N^2) 프림 사용
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            long[] x = new long[N];
            long[] y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) x[i] = Long.parseLong(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) y[i] = Long.parseLong(st.nextToken());
            double E = Double.parseDouble(br.readLine().trim());

            boolean[] visited = new boolean[N];
            long[] minCost = new long[N]; // MST에 연결되는 최소 비용(거리 제곱)
            Arrays.fill(minCost, Long.MAX_VALUE);
            minCost[0] = 0; // 0번 섬에서 시작

            long total = 0;
            for (int k = 0; k < N; k++) {
                // 아직 방문하지 않은 섬 중 연결 비용이 가장 작은 섬 선택
                int cur = -1;
                long min = Long.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && minCost[i] < min) {
                        min = minCost[i];
                        cur = i;
                    }
                }

                visited[cur] = true;
                total += min;

                // 선택한 섬을 기준으로 나머지 섬들의 최소 비용 갱신
                for (int i = 0; i < N; i++) {
                    if (!visited[i]) {
                        long dx = x[cur] - x[i];
                        long dy = y[cur] - y[i];
                        long d = dx * dx + dy * dy;
                        if (d < minCost[i]) minCost[i] = d;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(Math.round(total * E)).append('\n');
        }
        System.out.print(sb);
    }
}