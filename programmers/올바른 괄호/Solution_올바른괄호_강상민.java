import java.util.*;

class Solution {
    boolean solution(String s) {

        Stack<Character> st = new Stack<>();
        
        if (s.charAt(0) == ')') {
            return false;
        } 
        
        for(int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') st.push('(');
            else if (s.charAt(i) == ')') {
                if (st.isEmpty()) return false;
                else if (st.peek() == '(') {
                    st.pop();
                }
            }
        }
        
        if (!st.isEmpty()) return false;
        
        

        return true;
    }
}