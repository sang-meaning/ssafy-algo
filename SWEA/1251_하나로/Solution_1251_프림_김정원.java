package ssafy.swea.kjw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1251_김정원_프림 {

    static class Vertex {
        long x, y;

        public Vertex(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static Vertex[] vertexList;
    static double E;
    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            vertexList = new Vertex[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                vertexList[n] =
                        new Vertex(Long.parseLong(st.nextToken()), 0);
            }

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                vertexList[n].y = Long.parseLong(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            boolean[] visited = new boolean[N];

            // 현재 트리에서 각 정점으로 연결하는 최소 간선 비용
            long[] minEdge = new long[N];
            Arrays.fill(minEdge, Long.MAX_VALUE);

            // 0번 정점에서 시작
            minEdge[0] = 0;

            long result = 0;

            for (int cnt = 0; cnt < N; cnt++) {

                // 1. 미방문 정점 중 연결 비용이 가장 작은 정점 선택
                int minVertex = -1;
                long min = Long.MAX_VALUE;

                for (int v = 0; v < N; v++) {
                    if (!visited[v] && minEdge[v] < min) {
                        min = minEdge[v];
                        minVertex = v;
                    }
                }

                // 모든 섬 사이에 연결 가능하므로 항상 선택 가능
                // 2. 선택한 정점을 트리에 포함
                visited[minVertex] = true;
                result += min;

                // 3. 새 정점을 통해 다른 정점의 최소 연결 비용 갱신
                for (int v = 0; v < N; v++) {
                    if (visited[v]) continue;

                    long weight =
                            calWeight(vertexList[minVertex], vertexList[v]);

                    if (weight < minEdge[v]) {
                        minEdge[v] = weight;
                    }
                }
            }

            System.out.println(
                    "#" + test_case + " " + Math.round(result * E));
        }
    }

    private static long calWeight(Vertex s, Vertex e) {
        long dx = s.x - e.x;
        long dy = s.y - e.y;
        return dx * dx + dy * dy;
    }
}