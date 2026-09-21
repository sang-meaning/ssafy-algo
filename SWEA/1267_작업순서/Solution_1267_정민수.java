import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            List<Integer>[] graph = new ArrayList[V + 1];
            int[] indegree = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();

                graph[from].add(to);
                indegree[to]++;
            }

            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 1; i <= V; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");

            while (!queue.isEmpty()) {
                int current = queue.poll();

                sb.append(current).append(" ");

                for (int next : graph[current]) {
                    indegree[next]--;

                    if (indegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            System.out.println(sb);
        }

        sc.close();
    }
}