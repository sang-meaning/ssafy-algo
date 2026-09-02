import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> stocks = new LinkedList<>();
        
        // 큐 안 쓴 거
        // for(int i= 0; i < prices.length-1; i++){
        //     int stock = prices[i];
        //     int time = 0;
        //     for(int j = i+1; j < prices.length; j++){
        //         time++;
        //         if(prices[j] < stock){
        //             break;
        //         }
        //     }
        //     answer[i] = time;
        // }
        
        for(int i = 0; i < prices.length-1; i++){
            stocks.offer(prices[i]);
            for(int j = i+1; j < prices.length; j++){
                stocks.offer(prices[j]);
                if(prices[j] < stocks.peek())
                    break;
            }
            answer[i] = stocks.size()-1;
            stocks.clear();
        }
        
        answer[answer.length-1] = 0;
        return answer;
    }
}