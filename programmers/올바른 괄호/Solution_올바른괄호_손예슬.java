import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<Integer> st = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                if(st.isEmpty()) {
                    answer = false;
                    break;
                }
                st.pop();
            }
            else{
                st.push(1);
            }
        }
        
        if(!st.isEmpty())
            answer = false;
        return answer;
    }
}