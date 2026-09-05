import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        Deque<Integer> q = new ArrayDeque<>();


        for (int i = 0; i < speeds.length; i++) {
            int remain = 100 - progresses[i];
            int day = remain / speeds[i];

            if (remain % speeds[i] != 0) {
                day++;
            }

            q.add(day);
        }

        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            int day = q.poll();
            int cnt = 1;
            while(!q.isEmpty() && q.peek() <= day){
                q.poll();
                cnt++;
            }
            list.add(cnt);
        }
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}