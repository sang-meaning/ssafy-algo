import java.util.*;

class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        
        visited = new boolean[n];
        
        int cnt = 0;
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(i, n, computers);
                cnt++;
            }
        }
        
        return cnt;
    }
    
    static void bfs(int start, int n, int[][] computers){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        q.offer(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i = 0;i < n; i++){
                if(computers[cur][i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}