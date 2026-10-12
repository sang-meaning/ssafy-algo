import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    // 간선 정보 클래스
    static class Edge implements Comparable<Edge> {
        int from, to;
        long cost;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.getLines()));
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

            // 모든 섬 쌍 간의 간선 생성
            ArrayList<Edge> edgeList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    long distSquare = dx * dx + dy * dy;
                    edgeList.add(new Edge(i, j, distSquare));
                }
            }

            // 간선 비용(거리의 제곱) 기준 오름차순 정렬
            Collections.sort(edgeList);

            // Union-Find 초기화
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            long totalCostSquare = 0;
            int count = 0;

            // MST 구성
            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) {
                    totalCostSquare += edge.cost;
                    count++;
                    if (count == N - 1) break; // 모든 섬이 연결됨
                }
            }

            // 환경 부담금 계산 및 반올림
            long ans = Math.round(E * totalCostSquare);

            System.out.println("#" + tc + " " + ans);
        }
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]); // 경로 압축
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        }
        return false;
    }
}