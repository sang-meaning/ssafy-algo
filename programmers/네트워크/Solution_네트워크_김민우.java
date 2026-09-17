import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] connected = new boolean[n];
        Queue<Integer> computer = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            if(connected[i])
                continue;
            computer.offer(i);
            connected[i] = true;
            while(!computer.isEmpty()){
                int cmp = computer.poll();
                for(int j = 0; j < n; j++){
                    if(computers[cmp][j] == 0 || connected[j])
                        continue;
                    computer.offer(j);
                    connected[j] = true;
                }
            }
            answer++;
        }
        
        return answer;
    }
}