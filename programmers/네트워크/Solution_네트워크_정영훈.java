import java.util.*;

class Solution {
    static boolean[] visited;
    static Queue<Integer> q=new LinkedList<>();
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited=new boolean[computers.length];
        for(int i=0; i<computers.length; i++){
            if(!visited[i]){
                visited[i]=true;
                q.offer(i);
                answer++;
                dfs(computers);
                
            }
        }
        return answer;
    }
    static void dfs(int[][] computers){
        while(!q.isEmpty()){
            int current=q.poll();
            for(int i=0; i<computers[current].length;i++){
                int next=computers[current][i];
                if(next==1&&!visited[i]){
                    visited[i]=true;
                    q.offer(i);
                    
                }
            }
        }
    }
}