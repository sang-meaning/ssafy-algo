class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n]; // false
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) { // 안가봤으면
                dfs(computers, visited, i);
                answer++;
            }
        }
        return answer;
    }
    
    public void dfs(int[][] computers, boolean[] visited, int index) {
        visited[index] = true;
        for(int i = 0; i < computers.length; i++) {
            if(visited[i] == false && computers[index][i] == 1) {
                dfs(computers, visited, i);
            }
        }
    }
}