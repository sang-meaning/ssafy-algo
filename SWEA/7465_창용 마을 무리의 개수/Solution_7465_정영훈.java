import java.io.*;
import java.util.*;

class Solution {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) parent[i] = i;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                union(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
                );
            }

            int groups = 0;
            for (int i = 1; i <= N; i++) {
                if (find(i) == i) groups++;
            }

            answer.append('#').append(tc).append(' ').append(groups).append('\n');
        }

        System.out.print(answer);
    }
}