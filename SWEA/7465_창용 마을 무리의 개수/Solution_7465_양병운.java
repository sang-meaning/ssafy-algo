import java.util.*;
import java.io.*;

class Solution {
    static int[] parent;
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }c
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) parent[i] = i;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (find(i) == i) {
                    cnt++;
                }
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}