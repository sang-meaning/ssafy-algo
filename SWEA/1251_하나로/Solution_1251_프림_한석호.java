import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            long[] X = new long[N];
            long[] Y = new long[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Y[i] = Long.parseLong(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine().trim());

            // minEdge[i]: 현재 신장 트리에 포함된 정점들로부터 정점 i까지의 최소 거리 제곱
            long[] minEdge = new long[N];
            Arrays.fill(minEdge, Long.MAX_VALUE);
            boolean[] visited = new boolean[N];

            // 0번 정점에서 시작
            minEdge[0] = 0;
            long totalDistSq = 0;

            for (int i = 0; i < N; i++) {
                long min = Long.MAX_VALUE;
                int minVertex = -1;

                // 트리에 포함되지 않은 정점 중 가장 비용이 적은 정점 선택
                for (int j = 0; j < N; j++) {
                    if (!visited[j] && minEdge[j] < min) {
                        min = minEdge[j];
                        minVertex = j;
                    }
                }

                visited[minVertex] = true;
                totalDistSq += min;

                // 새로 추가된 정점을 기준으로 다른 미방문 정점들의 최소 비용 갱신
                for (int j = 0; j < N; j++) {
                    if (!visited[j]) {
                        long distSq = (X[minVertex] - X[j]) * (X[minVertex] - X[j])
                                    + (Y[minVertex] - Y[j]) * (Y[minVertex] - Y[j]);
                        if (distSq < minEdge[j]) {
                            minEdge[j] = distSq;
                        }
                    }
                }
            }

            long ans = Math.round(totalDistSq * E);
            System.out.println("#" + tc + " " + ans);
        }
    }
}