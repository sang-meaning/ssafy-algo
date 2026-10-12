import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            int days = (remain + speeds[i] - 1) / speeds[i]; 
            
            q.offer(days);
        }

        List<Integer> answerList = new ArrayList<>();

        while (!q.isEmpty()) {
            int prevDay = q.poll(); 
            int count = 1;

            while (!q.isEmpty() && q.peek() <= prevDay) {
                q.poll();
                count++;
            }

            answerList.add(count);
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}