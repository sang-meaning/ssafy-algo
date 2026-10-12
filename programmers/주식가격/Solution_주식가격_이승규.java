import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) { // 만일! stack 맨위가 (방금전 가격이) 지금 가격보다 높다면... (떨어졌다면)
                int index = stack.pop();
                answer[index] = i - index; // 지금부터 ~~ 그게 들어왔을떄까지
            }
            stack.push(i);
        }
        // 0 1 3 4 -> 4 3 1 0
        while(!stack.isEmpty()) {
            int temp = stack.pop();
            answer[temp] = prices.length - temp - 1;
        }
        
        
        return answer;
    }
}
