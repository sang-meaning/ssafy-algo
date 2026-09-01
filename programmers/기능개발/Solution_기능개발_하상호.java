package al_prac.pgms;
import java.util.*;

public class Solution_기능개발_하상호 {

    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new ArrayDeque<>();

        // 각 기능이 완료되기까지 필요한 날짜 계산
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i];

            // 올림 나눗셈
            int days = (remainingProgress + speeds[i] - 1) / speeds[i];

            queue.offer(days);
        }

        List<Integer> answerList = new ArrayList<>();

        while (!queue.isEmpty()) {
            // 현재 배포 기준 날짜
            int releaseDay = queue.poll();
            int count = 1;

            // 기준 날짜 안에 완료되는 기능을 함께 배포
            while (!queue.isEmpty() && queue.peek() <= releaseDay) {
                queue.poll();
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
