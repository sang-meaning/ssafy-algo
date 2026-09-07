import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Deque<String> deque = new ArrayDeque<>();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            deque.offerLast(Character.toString(s.charAt(i)));
        }
        while(!deque.isEmpty()){
            String first = deque.pollFirst();
            if(first.equals("(")){
                count++;
            }else{
                count--;
            }
            if(count < 0){
                answer = false;
                break;
            }
        }
        if(count != 0){
            answer = false;
        }
        return answer;
    }
}