import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return;
        }

        parent[pb] = pa;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];

            // make-set
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            // 관계 연결
            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            // 무리 개수
            int cnt = 0;

            for (int i = 1; i <= N; i++) {
                if (find(i) == i) {
                    cnt++;
                }
            }

            System.out.println("#" + tc + " " + cnt);
        }
    }
}