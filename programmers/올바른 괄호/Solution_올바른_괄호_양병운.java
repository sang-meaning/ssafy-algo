import java.util.*;

class Solution {
    boolean solution(String s) {
        String[] splits = s.split("");
        if(splits[0].equals(")")) return false;
        if(splits[splits.length-1].equals("(")) return false;
        if(splits.length%2!=0) return false;
        
        Stack<String> stack = new Stack<>();
        for(String sp: splits) {
            if(sp.equals(")")){
                if(!stack.isEmpty() && stack.peek().equals("(")) stack.pop();
                else return false;
            }else stack.add("(");
        }
        
        if(stack.size() > 0) return false;       
        
        return true;
    }
}