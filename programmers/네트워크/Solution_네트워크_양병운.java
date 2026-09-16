import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        List<Integer>[] graph = new List[n];
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j) continue;
                if(computers[i][j] == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        int network = 0;
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            queue.add(i);
            network++;
            while(!queue.isEmpty()){
                int t = queue.poll();
                List<Integer> conns = graph[t];
                for(int conn : conns){
                    if(visited[conn]) continue;
                    visited[conn] = true;
                    queue.add(conn);
                }
            }
        }
        return network;
    }
}