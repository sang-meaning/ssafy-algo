import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goals) {
        String answer = "No";
        Deque<String> card1 = new ArrayDeque<>();
        Deque<String> card2 = new ArrayDeque<>();
        Deque<String> goal = new ArrayDeque<>();
        for(int i = 0; i < cards1.length; i++){
            card1.offerLast(cards1[i]);
        }
        for(int i = 0; i < cards2.length; i++){
            card2.offerLast(cards2[i]);
        }
        for(int i = 0; i < goals.length; i++){
            goal.offerLast(goals[i]);
        }
        while(!goal.isEmpty()){
            boolean change = false;
            if(!card1.isEmpty()){
                if(card1.peekFirst().equals(goal.peekFirst())){
                    card1.pollFirst();
                    goal.pollFirst();
                    change = true;
                }
            }
            if(!card2.isEmpty()){
                if(card2.peekFirst().equals(goal.peekFirst())){
                    card2.pollFirst();
                    goal.pollFirst();
                    change = true;
                }
            }
            if(!change){
                break;
            }
        }
        if(goal.isEmpty()){
            answer = "Yes";
        }
        return answer;
    }
}