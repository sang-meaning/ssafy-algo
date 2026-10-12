import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networkCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, computers, visited, n);
                networkCount++;
            }
        }

        return networkCount;
    }

    private void bfs(int start, int[][] computers, boolean[] visited, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int i = 0; i < n; i++) {
                // 연결되어 있고 아직 방문하지 않았다면 큐에 추가
                if (computers[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}