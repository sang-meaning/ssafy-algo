import java.util.*;

class Solution {
    boolean solution(String s) {

        
        
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length();i++){
            char ch = s.charAt(i);
        
        
        if(ch == '('){
            stack.push(ch);
        }else{
            if (stack.isEmpty()) {
                return false;
            } else {
                stack.pop();
            }
        }
        }

        boolean answer = stack.isEmpty();
        
        return answer;
    }
}