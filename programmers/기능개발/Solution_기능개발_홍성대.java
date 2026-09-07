import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();
        
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            days[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
        }
        
        int maxDay = days[0]; 
        int count = 1;        
        
        for (int i = 1; i < days.length; i++) {
            if (days[i] <= maxDay) {
                count++;
            } else {
                answerList.add(count);
                maxDay = days[i];
                count = 1;
            }
        }
        
        answerList.add(count);
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
