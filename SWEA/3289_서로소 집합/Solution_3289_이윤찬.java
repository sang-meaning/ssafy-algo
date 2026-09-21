import java.io.*;
import java.util.*;

public class Solution {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 1. parent 초기화
            parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            StringBuilder sb = new StringBuilder();

            // 2. m개의 명령 처리
            for (int i = 0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 합집합
                if (command == 0) {

                    union(a, b);

                }

                // 같은 집합인지 확인
                else {

                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }

            System.out.println("#" + tc + " " + sb);
        }
    }

    // 대표자 찾기
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
}