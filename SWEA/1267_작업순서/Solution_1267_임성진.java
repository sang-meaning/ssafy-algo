import java.io.*;
import java.util.*;

public class Solution_1267_임성진 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            in.nextToken(); int V = (int) in.nval;
            in.nextToken(); int E = (int) in.nval;

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= V; i++) adj.add(new ArrayList<Integer>());
            int[] indeg = new int[V + 1];

            for (int i = 0; i < E; i++) {
                in.nextToken(); int a = (int) in.nval;
                in.nextToken(); int b = (int) in.nval;
                adj.get(a).add(b);          // a 작업이 끝나야 b 작업 가능
                indeg[b]++;
            }

            // 결과가 여러 개일 때 사전순 최소를 원하면 PriorityQueue 로 교체
            Deque<Integer> q = new ArrayDeque<>();
            for (int v = 1; v <= V; v++) if (indeg[v] == 0) q.add(v);

            sb.append('#').append(tc);
            while (!q.isEmpty()) {
                int cur = q.poll();
                sb.append(' ').append(cur);
                for (int nxt : adj.get(cur)) {
                    if (--indeg[nxt] == 0) q.add(nxt);
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}