import java.io.*;
import java.util.*;

// SWEA 1251. 하나로 - 크루스칼(Kruskal) 풀이
public class Solution {
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false; // 이미 연결됨 → 사이클
        parent[rb] = ra;
        return true;
    }

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

            // 모든 섬 쌍에 대해 간선 생성 (가중치 = 거리의 제곱)
            int M = N * (N - 1) / 2;
            long[][] edges = new long[M][3]; // {from, to, cost}
            int idx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    edges[idx][0] = i;
                    edges[idx][1] = j;
                    edges[idx][2] = dx * dx + dy * dy;
                    idx++;
                }
            }

            // 가중치 오름차순 정렬
            Arrays.sort(edges, (a, b) -> Long.compare(a[2], b[2]));

            parent = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;

            long total = 0;
            int cnt = 0;
            for (long[] e : edges) {
                if (union((int) e[0], (int) e[1])) {
                    total += e[2];
                    if (++cnt == N - 1) break; // N-1개 간선 선택 시 종료
                }
            }

            sb.append('#').append(tc).append(' ').append(Math.round(total * E)).append('\n');
        }
        System.out.print(sb);
    }
}