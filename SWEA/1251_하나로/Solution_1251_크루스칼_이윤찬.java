import java.io.*;
import java.util.*;

public class 하나로 {

    static int T;
    static int N;

    static class Island {
        long x;
        long y;

        Island(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // 간선
    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static Island[] lands;

    // Union-Find
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            lands = new Island[N];

            long[] x = new long[N];
            long[] y = new long[N];

            // X 좌표
            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            // Y 좌표
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            // 섬 생성
            for (int i = 0; i < N; i++) {
                lands[i] = new Island(x[i], y[i]);
            }

            // 환경 부담 세율
            double E = Double.parseDouble(br.readLine());


            // ===========================
            // 1. 모든 간선 만들기
            // ===========================

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                for (int j = i + 1; j < N; j++) {

                    long dx = lands[i].x - lands[j].x;
                    long dy = lands[i].y - lands[j].y;

                    // 거리의 제곱
                    long distance = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, distance));
                }
            }


            // ===========================
            // 2. 간선 비용순 정렬
            // ===========================

            Collections.sort(edges);


            // ===========================
            // 3. Union-Find 초기화
            // ===========================

            parent = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }


            // ===========================
            // 4. 크루스칼
            // ===========================

            long sum = 0;

            // 선택한 간선 개수
            int count = 0;

            for (Edge edge : edges) {

                int from = edge.from;
                int to = edge.to;

                // 두 섬이 아직 연결되지 않았다면
                if (find(from) != find(to)) {

                    // 연결
                    union(from, to);

                    // 비용 추가
                    sum += edge.cost;

                    count++;

                    // MST는 N-1개의 간선만 필요
                    if (count == N - 1) {
                        break;
                    }
                }
            }


            // 환경 부담금
            long answer = Math.round(sum * E);

            System.out.println("#" + tc + " " + answer);
        }
    }


    // ===========================
    // Union-Find
    // ===========================

    static int find(int x) {

        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }


    static void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}