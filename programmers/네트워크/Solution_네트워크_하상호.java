package pgms;

class Solution_네트워크_하상호 {

    static boolean[] visited;

    public int solution(int n, int[][] computers) {

        visited = new boolean[n];

        int answer = 0;

        for (int i = 0; i < n; i++) {

            // 아직 방문하지 않은 컴퓨터 발견
            if (!visited[i]) {

                dfs(i, n, computers);

                // DFS 한 번 = 네트워크 한 개
                answer++;
            }
        }

        return answer;
    }

    static void dfs(int cur, int n, int[][] computers) {

        visited[cur] = true;

        for (int next = 0; next < n; next++) {

            // 연결되어 있고 아직 방문하지 않은 경우
            if (computers[cur][next] == 1
                    && !visited[next]) {

                dfs(next, n, computers);
            }
        }
    }
}
