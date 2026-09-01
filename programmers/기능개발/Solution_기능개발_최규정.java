import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < progresses.length;i++){
            int remain = 100 - progresses[i];
            int day = remain / speeds[i];
            
            if(remain % speeds[i] != 0){
                day++;
            }
            
            queue.offer(day);
        }
        
        
        while (!queue.isEmpty()) {
            int base = queue.poll();
            int count = 1;

            while (!queue.isEmpty() && queue.peek() <= base) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        
        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}