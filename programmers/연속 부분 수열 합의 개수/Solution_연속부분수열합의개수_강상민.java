import java.util.*;


class Solution {
    
    static HashSet<Integer> set = new HashSet<>();
    
    public int solution(int[] elements) {        
        // 7 9 1 1 4 7 9 1 1 4 에서 
        // 1~5길이의 수열의 합을 set에 넣기
        
        int n = elements.length;
        int[] e = new int[2*n];
        
        for (int i=0; i<n; i++) {
            e[i] = elements[i];
            e[i+n] = elements[i];
        }
        
        for (int i=0; i<n; i++) {
            int sum = 0;
            
            for (int j=0; j<n; j++) {
                int idx = i+j;
                
                sum += e[idx];
                
                set.add(sum);
            }
        }
    
        
        
        return set.size();
    }
    
}