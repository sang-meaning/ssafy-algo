import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            int day = (int) Math.ceil(
                (double) (100 - progresses[i]) / speeds[i]
            );

            queue.offer(day);
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {

            int finished = queue.poll();
            int count = 1;

            while (!queue.isEmpty() && finished >= queue.peek()) {
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