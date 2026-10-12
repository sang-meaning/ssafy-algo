import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int current = 0; current < prices.length; current++) {

            while (!stack.isEmpty()
                    && prices[stack.peek()] > prices[current]) {

                int previous = stack.pop();
                answer[previous] = current - previous;
            }

            stack.push(current);
        }

        while (!stack.isEmpty()) {
            int previous = stack.pop();
            answer[previous] = prices.length - 1 - previous;
        }

        return answer;
    }
}