import java.util.*;
import java.io.*;

class Solution {
    static int[] parent;
    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        if (parent[rootA] > parent[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }
        parent[rootA] += parent[rootB];
        parent[rootB] = rootA;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            parent = new int[N + 1];
            Arrays.fill(parent, -1);
            output.append("#").append(testCase).append(" ");
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int operation = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (operation == 0) union(a, b);
                else output.append(find(a) == find(b) ? '1' : '0');
            }
            output.append('\n');
        }
        System.out.print(output);
    }
}