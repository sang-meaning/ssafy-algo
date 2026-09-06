import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int[] days = new int[progresses.length];

        // 각 기능이 완료될 때까지 필요한 날짜 계산
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i];

            days[i] = remainingProgress / speeds[i];

            // 나머지가 있으면 하루가 더 필요
            if (remainingProgress % speeds[i] != 0) {
                days[i]++;
            }
        }

        int maxDay = days[0];
        int count = 0;

        // 앞 기능과 함께 배포할 수 있는 기능 개수 계산
        for (int day : days) {
            if (day <= maxDay) {
                count++;
            } else {
                answer.add(count);
                count = 1;
                maxDay = day;
            }
        }

        // 마지막 배포 묶음 추가
        answer.add(count);

        // List<Integer>를 int[]로 변환
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}