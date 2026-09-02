import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> list = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(')
                list.push(c);
            else{
                if(list.isEmpty())
                    return false;
                list.pop();
            }
            
        }
        
        boolean answer = list.isEmpty();
        return answer;
  
    }
}