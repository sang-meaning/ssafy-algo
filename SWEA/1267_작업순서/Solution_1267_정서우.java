import java.io.*;
import java.util.*;

public class Solution_1267_정서우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) break;

            StringTokenizer st = new StringTokenizer(line);
            int V = Integer.parseInt(st.nextToken()); // 정점 개수
            int E = Integer.parseInt(st.nextToken()); // 간선 개수

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                adj.add(new ArrayList<>());
            }

            int[] inDegree = new int[V + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj.get(from).add(to);
                inDegree[to]++;
            }

            // 진입 차수가 0인 정점을 큐에 삽입
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            sb.append("#").append(t).append(" ");

            // 위상 정렬
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                sb.append(cur).append(" ");

                for (int next : adj.get(cur)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}