import java.util.*;

class Solution_기능개발_정서우 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();
        int[] answer;

        for (int i = 0; i < progresses.length; i++) {
            int a = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0)
                a++;
            q.add(a);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            int cnt = 1;
            while (!q.isEmpty() && q.peek() <= cur) {
                q.poll();
                cnt++;
            }
            list.add(cnt);
        }

        answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}