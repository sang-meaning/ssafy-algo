import java.util.*;

public class Solution {

    static int V;
    static int E;

    static ArrayList<Integer>[] graph;
    static int[] indegree;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {

            V = sc.nextInt();
            E = sc.nextInt();

            graph = new ArrayList[V + 1];
            indegree = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {

                int from = sc.nextInt();
                int to = sc.nextInt();

                graph[from].add(to);

                indegree[to]++;
            }

            Deque<Integer> queue = new ArrayDeque<>();

            for (int i = 1; i <= V; i++) {

                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            StringBuilder sb = new StringBuilder();

            while (!queue.isEmpty()) {

                int current = queue.poll();

                sb.append(current).append(" ");

                for (int i = 0; i < graph[current].size(); i++) {

                    int next = graph[current].get(i);

                    indegree[next]--;

                    if (indegree[next] == 0) {

                        queue.offer(next);
                    }
                }
            }

            System.out.println("#" + tc + " " + sb);
        }

        sc.close();
    }
}