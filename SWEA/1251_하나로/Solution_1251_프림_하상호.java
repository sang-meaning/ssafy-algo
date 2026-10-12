package swea;

import java.io.*;
import java.util.*;

public class Solution_1251_프림_하상호 {
    static int N;
    static long[] x;
    static long[] y;

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            x = new long[N];
            y = new long[N];

            // X 좌표 입력
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            // Y 좌표 입력
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            // MST에 포함되었는지 확인
            boolean[] visited = new boolean[N];

            // 현재 MST와 각 정점을 연결하는 최소 비용
            long[] minEdge = new long[N];

            Arrays.fill(minEdge, Long.MAX_VALUE);

            // 0번 섬부터 시작
            minEdge[0] = 0;

            long totalCost = 0;

            for (int count = 0; count < N; count++) {

                long min = Long.MAX_VALUE;
                int current = -1;

                // 아직 선택하지 않은 섬 중
                // 가장 적은 비용으로 연결 가능한 섬 선택
                for (int i = 0; i < N; i++) {

                    if (!visited[i] && minEdge[i] < min) {

                        min = minEdge[i];
                        current = i;
                    }
                }

                // 선택된 섬 방문 처리
                visited[current] = true;

                // MST 비용 추가
                totalCost += min;

                // current 섬을 기준으로 다른 섬까지의 거리 갱신
                for (int next = 0; next < N; next++) {

                    if (visited[next]) {
                        continue;
                    }

                    long dx = x[current] - x[next];
                    long dy = y[current] - y[next];

                    long distance = dx * dx + dy * dy;

                    if (distance < minEdge[next]) {
                        minEdge[next] = distance;
                    }
                }
            }

            long answer = Math.round(totalCost * E);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}