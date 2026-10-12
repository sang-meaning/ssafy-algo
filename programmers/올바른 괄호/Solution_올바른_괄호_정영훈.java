import java.util.*;

class Solution_올바른_괄호_정영훈 {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack=new Stack<>();
        for(int i=0; i<s.length(); i++){
            Character c=s.charAt(i);
            if(stack.size()==0){
                stack.push(c);
                continue;
            }else if(stack.peek()=='(' && c==')'){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        answer=stack.size()==0?true:false;

        

        return answer;
    }
}