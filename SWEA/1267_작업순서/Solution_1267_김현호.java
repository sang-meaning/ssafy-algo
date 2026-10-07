import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {

            int V = sc.nextInt(); // 정점 수
            int E = sc.nextInt(); // 간선 수

            List<Integer>[] graph = new ArrayList[V + 1];
            int[] indegree = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 입력
            for (int i = 0; i < E; i++) {

                int from = sc.nextInt();
                int to = sc.nextInt();

                graph[from].add(to);

                // to로 들어오는 간선 개수 증가
                indegree[to]++;
            }

            Queue<Integer> q = new LinkedList<>();

            // 선행 작업이 없는 작업부터 시작
            for (int i = 1; i <= V; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            System.out.print("#" + tc + " ");

            while (!q.isEmpty()) {

                int cur = q.poll();

                System.out.print(cur + " ");

                // cur 작업이 끝났으므로
                // cur 다음에 수행할 작업들의 선행조건 제거
                for (int next : graph[cur]) {

                    indegree[next]--;

                    // 모든 선행 작업이 끝났다면
                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            System.out.println();
        }
    }
}