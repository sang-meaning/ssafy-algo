import java.util.*;

class Solution {

    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];

        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                answer++;

                bfs(i, n, computers, visited);
            }
        }

        return answer;
    }

    static void bfs(int start, int n, int[][] computers,boolean[] visited) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int next = 0; next < n; next++) {
                
                if (computers[current][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}