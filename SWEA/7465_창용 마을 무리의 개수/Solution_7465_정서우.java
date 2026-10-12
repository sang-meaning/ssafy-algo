import java.io.*;
import java.util.*;

public class Solution_7465_정서우 {
    static List<List<Integer>> adj;
    static boolean[] visited;

    static void dfs(int node) {
        visited[node] = true;
        for (int next : adj.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            adj = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            visited = new boolean[N + 1];
            int groupCount = 0;

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    groupCount++;
                }
            }

            sb.append("#").append(tc).append(" ").append(groupCount).append("\n");
        }

        System.out.print(sb);
    }
}