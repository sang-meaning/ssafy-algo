import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

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

            double E = Double.parseDouble(br.readLine().trim());

            // minEdge[i]: 이미 선택된 MST 집합에서 i번 섬까지 연결하는 최소 거리의 제곱
            long[] minEdge = new long[N];
            Arrays.fill(minEdge, Long.MAX_VALUE);

            boolean[] visited = new boolean[N];

            // 임의의 시작점 (0번 섬) 설정
            minEdge[0] = 0;
            long totalCostSquare = 0;

            for (int i = 0; i < N; i++) {
                // 1. MST에 포함되지 않은 정점 중 최소 비용 정점 찾기
                long min = Long.MAX_VALUE;
                int minVertex = -1;

                for (int j = 0; j < N; j++) {
                    if (!visited[j] && minEdge[j] < min) {
                        min = minEdge[j];
                        minVertex = j;
                    }
                }

                // 연결 불가능한 경우 (정상적인 입력에서는 발생하지 않음)
                if (minVertex == -1) break;

                // 2. 해당 정점을 MST 집합에 추가
                visited[minVertex] = true;
                totalCostSquare += min;

                // 3. 새로 추가된 정점 기준, 아직 방문하지 않은 정점들의 minEdge 갱신
                for (int j = 0; j < N; j++) {
                    if (!visited[j]) {
                        long dx = x[minVertex] - x[j];
                        long dy = y[minVertex] - y[j];
                        long distSquare = dx * dx + dy * dy;

                        if (distSquare < minEdge[j]) {
                            minEdge[j] = distSquare;
                        }
                    }
                }
            }

            // 환경 부담금 계산 및 반올림
            long ans = Math.round(E * totalCostSquare);

            System.out.println("#" + tc + " " + ans);
        }
    }
}