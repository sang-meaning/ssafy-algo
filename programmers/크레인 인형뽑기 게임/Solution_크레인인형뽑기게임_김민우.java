import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board[0].length;
        Stack<Integer> basket = new Stack<>();
        
        for(int mv : moves){
            mv--;
            for(int i = 0; i < N; i++){
                if(board[i][mv] != 0){
                    if(!basket.isEmpty()){
                        int top = basket.pop();
                        if(top != board[i][mv]){
                            basket.push(top);
                            basket.push(board[i][mv]);
                        }
                        else
                            answer += 2;
                    }
                    else
                        basket.push(board[i][mv]);
                    board[i][mv] = 0;
                    break;
                }
            } 
        }
        return answer;
    }
}