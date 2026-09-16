import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1267_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                adj.add(new ArrayList<>());
            }
            int[] inDegree = new int[V + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                inDegree[v]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc);
            while (!q.isEmpty()) {
                int curr = q.poll();
                sb.append(" ").append(curr);

                for (int next : adj.get(curr)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            System..println(sb.toString());
        }
    }
}