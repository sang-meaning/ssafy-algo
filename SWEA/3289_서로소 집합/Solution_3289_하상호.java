package swea;

import java.io.*;
import java.util.*;

public class Solution_3289_하상호 {

    static int[] parent;

    // 부모 찾기
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    // 두 집합 합치기
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

            // 자기 자신을 부모로 초기화
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 0 : 합집합
                if (command == 0) {
                    union(a, b);
                }

                // 1 : 같은 집합인지 확인
                else {
                    if (find(a) == find(b)) {
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
