class Solution {
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n]; 

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++; 
                dfs(i, n, computers); 
            }
        }

        return answer;
    }

    static void dfs(int current, int n, int[][] computers) {
        visited[current] = true;
        for (int next = 0; next < n; next++) {
            if (computers[current][next] == 1 && !visited[next]) {
                dfs(next, n, computers);
            }
        }
    }
}