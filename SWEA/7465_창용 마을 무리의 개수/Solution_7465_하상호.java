package swea;

import java.io.*;
import java.util.*;

public class Solution_7465_하상호 {

    static int[] parent;

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

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            // 관계 입력
            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            // root의 개수 계산
            Set<Integer> set = new HashSet<>();

            for (int i = 1; i <= N; i++) {
                set.add(find(i));
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(set.size())
              .append("\n");
        }

        System.out.print(sb);
    }
}
