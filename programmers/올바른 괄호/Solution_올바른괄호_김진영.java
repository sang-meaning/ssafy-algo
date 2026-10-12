import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer;

        Deque<Character> q = new ArrayDeque<>();
        
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            
            if(c == '('){
                q.add(c);
            }else{
                if(!q.isEmpty()){
                    if(q.peekLast() == '('){
                        q.poll();
                    }    
                }else{
                    q.add(c);    
                }
            }
        }
        answer = true;
        if(!q.isEmpty()){
            answer = false;
        }

        return answer;
    }
}