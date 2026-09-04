import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        
        int N = elements.length;
        int[] arr = new int[N*2];
        HashSet<Integer> q = new HashSet<>();
        
        for(int i=0;i<N;i++){
            arr[i] = elements[i];
            arr[N+i] = elements[i];
        }
 
        for(int i=0;i<N;i++){
            int sum = 0;
            
            for(int j=0;j<N;j++){
                
                sum += arr[i+j];
                q.add(sum);
                
            }
        }
        
        
        return q.size();
    }
}