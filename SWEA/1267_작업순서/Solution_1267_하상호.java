package swea;

import java.io.*;
import java.util.*;

public class Solution_1267_하상호 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 문제에서 테스트 케이스는 10개
        for (int tc = 1; tc <= 10; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<Integer>[] graph = new ArrayList[V + 1];

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 진입 차수
            int[] indegree = new int[V + 1];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < E; i++) {

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);

                // to로 들어오는 간선 개수 증가
                indegree[to]++;
            }

            Queue<Integer> queue = new ArrayDeque<>();

            // 선행 작업이 없는 작업
            for (int i = 1; i <= V; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            sb.append("#").append(tc).append(" ");

            while (!queue.isEmpty()) {

                int cur = queue.poll();

                sb.append(cur).append(" ");

                for (int next : graph[cur]) {

                    // cur 작업을 완료했으므로
                    // next의 선행 작업 하나가 끝남
                    indegree[next]--;

                    // 모든 선행 작업이 끝났다면
                    if (indegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}