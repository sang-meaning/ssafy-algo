import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        
        Map<String, Integer> r = new HashMap();
        for (int i = 0; i < n; i++){
            r.put(friends[i], i);
        }
        
        int[] giftindex = new int[n];
        int[] next = new int[n];
        
        for (String g : gifts){
            r.put(g, r.getOrDefault(g,0) + 1);
            
            String[] parts = g.split(" ");
            giftindex[r.get(parts[0])]++;
            giftindex[r.get(parts[1])]--;
        }
        
        for (int i = 0; i<n; i++) {
            for(int j = i +1; j < n; j++){
                String A = friends[i];
                String B = friends[j];
                
                int tob = r.getOrDefault(A + " " + B,0);
                int toa = r.getOrDefault(B + " " + A,0);
                
                if (tob > toa){
                    next[i] ++;
                }else if (toa > tob){
                    next[j]++;
                }
                else{
                    if (giftindex[i] > giftindex[j]) next[i]++;
                    else if (giftindex[j] > giftindex[i]) next[j]++;
                }
            }
        }
        for (int count : next){
            answer = Math.max(answer,count);
        }
        return answer;
        
    }
    
    
    
}