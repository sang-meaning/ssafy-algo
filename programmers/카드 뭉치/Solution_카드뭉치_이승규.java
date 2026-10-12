import java.util.ArrayDeque;
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        ArrayDeque<String> queue1 = new ArrayDeque<>();
        ArrayDeque<String> queue2 = new ArrayDeque<>();
        // stack을 2개.
        
        for(int i = 0; i < cards1.length; i++) {
            queue1.add(cards1[i]);
        }
        
        for(int i = 0; i < cards2.length; i++) {
            queue2.add(cards2[i]);
        }
        
        for(int i = 0; i < goal.length; i++) {
            if(goal[i].equals(queue1.peek())){
                queue1.poll();
            } else if (goal[i].equals(queue2.peek())) {
                queue2.poll();
            } else {
                answer = "No";
                break;
            }
        }
        
        return answer;
    }
}