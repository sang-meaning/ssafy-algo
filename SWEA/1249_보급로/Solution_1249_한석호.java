import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Solution_1249_한석호 {

    // 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 우선순위 큐에 넣을 노드 클래스
    static class Node implements Comparable<Node> {
        int weight;
        int x;
        int y;

        public Node(int weight, int x, int y) {
            this.weight = weight;
            this.x = x;
            this.y = y;
        }

        // 가중치 오름차순 정렬 기준 설정
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] graph = new int[N][N];

            // 그래프 입력 받기
            for (int i = 0; i < N; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < N; j++) {
                    graph[i][j] = line.charAt(j) - '0';
                }
            }

            // 다익스트라 실행 및 출력
            dijkstra(tc, N, graph);
        }
    }

    public static void dijkstra(int tc, int N, int[][] graph) {
        // visited 배열을 큰 값으로 초기화 (Infinity 역할)
        int[][] visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        visited[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int w = current.weight;
            int x = current.x;
            int y = current.y;

            // 이미 처리된 최단 거리보다 더 큰 가중치를 가진 노드라면 스킵 (최적화)
            if (w > visited[x][y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 경계 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newW = w + graph[nx][ny];

                    if (newW < visited[nx][ny]) {
                        visited[nx][ny] = newW;
                        pq.offer(new Node(newW, nx, ny));
                    }
                }
            }
        }

        System.out.println("#" + tc + " " + visited[N - 1][N - 1]);
    }
}