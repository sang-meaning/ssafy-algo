import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        int count = 0;
        
        int s_length = s.length();
        for(int i=0; i<s_length; i++){
            
            if(s.charAt(i) == '('){
                count++;
            }else{
                count--;
            }
            
            if(count<0){
                return false;
            }
        }
        
        if(count!=0){
            return false;
        }

        

        return answer;
    }
}