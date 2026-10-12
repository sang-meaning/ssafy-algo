import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean check[] = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int i=0;i<n;i++){
            if(check[i] == true){
                continue;
            }
            q.add(i);
            check[i] = true;
            answer++;
            
            while(!q.isEmpty()){
                int temp = q.poll();
                
                for(int j=0;j<n;j++){
                    if(computers[temp][j] == 1){
                        if(check[j] == false){
                            check[j] = true;
                            q.add(j);    
                        }
                        
                    }
                }
            }
        }
        return answer;
    }
}