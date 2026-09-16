import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                answer++;
                visited[i] = true;
                int[] com = computers[i];
                queue.offer(com);
                while(!queue.isEmpty()){
                    int[] c = queue.poll();
                    for(int j = 0; j < n; j++){
                        if(j != i && c[j] == 1 && visited[j] == false){
                            int[] nc = computers[j];
                            visited[j] = true;
                            queue.offer(nc);
                        }
                    }
                }

            }
        }
        
        return answer;
    }
}