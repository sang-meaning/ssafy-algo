import java.util.Queue;
import java.util.LinkedList;
class Solution {
    static int answer;
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        Queue<Integer> bfs = new LinkedList<>();
        visited = new boolean[n];
        answer = 0;
        for(int i = 0; i<n; i++){ // i =0
            if(!visited[i]){ 
            answer++; // 1
            bfs.offer(i); // 0
            visited[i]= true; // 0 true
            while(!bfs.isEmpty()){ 
                int cur =bfs.poll(); // 0 quesize =0
                
                for(int next =0 ; next <n; next++){ // 0 -> [1,1,0]
                    if(!visited[next]&& computers[cur][next]==1){ 
                        bfs.offer(next);
                        visited[next]=true;
                        }
                    }
                }
            }
          
        }
        return answer;
    }
}