import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int answer = 0;

        Deque<Integer> tw = new ArrayDeque<>();

        for (int truck : truck_weights) {
            tw.offerLast(truck);
        }

        List<List<Integer>> tob = new ArrayList<>();

        while (!tw.isEmpty() || !tob.isEmpty()) {

            answer++;

            // 기존 트럭 시간 증가
            for (int i = tob.size() - 1; i >= 0; i--) {
                tob.get(i).set(1, tob.get(i).get(1) + 1);

                // 다리를 다 건넌 트럭 제거
                if (tob.get(i).get(1) >= bridge_length) {
                    tob.remove(i);
                }
            }

            // 현재 다리 위 무게 계산
            int currentWeight = 0;

            for (int i = 0; i < tob.size(); i++) {
                currentWeight += tob.get(i).get(0);
            }

            // 새 트럭 진입
            if (!tw.isEmpty()) {
                if (currentWeight + tw.peekFirst() <= weight) {
                    tob.add(new ArrayList<>(
                        Arrays.asList(tw.pollFirst(), 0)
                    ));
                }
            }
        }

        return answer;
    }
}