import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        
        // 1. 각 작업마다 완수까지 걸리는 일수 계산하여 큐에 삽입
        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            // 잔여 작업량을 속도로 나누어 올림 처리 (ex. 30% 남고 속도 20 -> 2일 걸림)
            int days = (remain + speeds[i] - 1) / speeds[i];
            queue.offer(days);
        }
        
        List<Integer> result = new ArrayList<>();
        
        // 2. 큐가 빌 때까지 배포 단위 묶기
        while (!queue.isEmpty()) {
            int currentDays = queue.poll(); // 현재 배포 기준이 되는 기능의 소요 일수
            int count = 1; // 배포될 기능 수
            
            // 뒤에 있는 기능의 소요 일수가 현재 기능보다 작거나 같으면 함께 배포
            while (!queue.isEmpty() && queue.peek() <= currentDays) {
                count++;
                queue.poll();
            }
            
            result.add(count);
        }
        
        // 3. List를 int[] 배열로 변환하여 반환
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}