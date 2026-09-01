package al_prac.pgms;
import java.util.*;

public class Solution_주식가격_하상호 {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        // 아직 가격이 떨어지지 않은 시점의 인덱스 저장
        Deque<Integer> stack = new ArrayDeque<>();

        for (int current = 0; current < n; current++) {

            // 현재 가격이 이전 가격보다 낮으면
            // 이전 가격이 떨어진 시점
            while (!stack.isEmpty()
                    && prices[stack.peek()] > prices[current]) {

                int previous = stack.pop();

                // previous부터 current까지 걸린 시간
                answer[previous] = current - previous;
            }

            stack.push(current);
        }

        // 끝까지 가격이 떨어지지 않은 경우
        while (!stack.isEmpty()) {
            int previous = stack.pop();
            answer[previous] = n - 1 - previous;
        }

        return answer;
    }
}
