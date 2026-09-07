import java.util.*;
class Solution {
    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        while (progresses.length > 0) {
            // 매일 작업 진행
            for (int i = 0; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            // 배포 가능한 기능 개수 세기
            int count = 0;
            int i = 0;
            while (i < progresses.length && progresses[i] >= 100) {
                count++;
                i++;
            }

            if (count > 0) {
                result.add(count);

                // 배포된 부분 잘라내기
                progresses = Arrays.copyOfRange(progresses, count, progresses.length);
                speeds = Arrays.copyOfRange(speeds, count, speeds.length);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}