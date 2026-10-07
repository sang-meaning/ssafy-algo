import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Solution {
    static class Island {
        long x, y;
        Island(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // 우선순위 큐에 넣을 간선/정점 정보
    static class Edge implements Comparable<Edge> {
        int to;
        long weightSq; // 거리의 제곱

        Edge(int to, long weightSq) {
            this.to = to;
            this.weightSq = weightSq;
        }

        @Override
        public int compareTo(Edge o) {
            // 가중치(거리 제곱) 기준 오름차순 정렬
            return Long.compare(this.weightSq, o.weightSq);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            long[] xCoords = new long[N];
            long[] yCoords = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                xCoords[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                yCoords[i] = Long.parseLong(st.nextToken());
            }

            Island[] islands = new Island[N];
            for (int i = 0; i < N; i++) {
                islands[i] = new Island(xCoords[i], yCoords[i]);
            }

            double E = Double.parseDouble(br.readLine().trim());

            // PQ 기반 Prim 준비
            boolean[] visited = new boolean[N];
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            // 0번 섬부터 시작 (비용 0으로 큐에 삽입)
            pq.offer(new Edge(0, 0));

            long totalDistSq = 0;
            int count = 0; // 선택된 섬의 개수

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                int u = cur.to;

                // 이미 MST에 포함된 섬이면 스킵
                if (visited[u]) continue;

                // MST에 포함 처리
                visited[u] = true;
                totalDistSq += cur.weightSq;
                count++;

                // 모든 섬이 연결되면 조기 종료
                if (count == N) break;

                // 연결되지 않은 모든 섬 v와의 간선을 PQ에 추가
                for (int v = 0; v < N; v++) {
                    if (!visited[v]) {
                        long dx = islands[u].x - islands[v].x;
                        long dy = islands[u].y - islands[v].y;
                        long distSq = dx * dx + dy * dy;

                        pq.offer(new Edge(v, distSq));
                    }
                }
            }

            // 결과 계산 및 반올림
            long ans = Math.round(E * totalDistSq);
            System.out.println("#" + tc + " " + ans);
        }
    }
}