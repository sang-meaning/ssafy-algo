import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int size = prices.length;
        int[] result = new int[size];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<size; i++){
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int index = stack.pop();
            result[index] = size - index - 1;
        }
        return result;
    }
}