import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(stack.isEmpty()){
                    answer = false;
                    return answer;
                }
                stack.pop();
                continue;
            }                
            stack.push(s.charAt(i));
        }
        
        if(!stack.isEmpty())
            answer = false;

        return answer;
    }
}