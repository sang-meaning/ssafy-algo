import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>(); // 인덱스를 저장할 스택
        
        for (int i = 0; i < prices.length; i++) {
            // 스택의 맨 위 인덱스의 가격보다 현재 가격이 떨어진 경우
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int index = stack.pop();
                answer[index] = i - index; // 기간 계산 (현재 시점 - 해당 시점)
            }
            stack.push(i);
        }
        
        // 끝까지 가격이 떨어지지 않은 나머지 시점들 처리
        while (!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = prices.length - 1 - index;
        }
        
        return answer;
    }
}