import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
    
        Deque<Integer> stack = new ArrayDeque<>();
        
        int answer = 0;
        
        for(int i = 0; i < moves.length; i++){
            int x = moves[i] -1;
            
            for(int j = 0; j < board.length; j++){
                if(board[j][x] != 0){
                    int doll = board[j][x];
                    board[j][x] = 0;
                    
                    if(!stack.isEmpty() && doll == stack.peek()){
                        stack.pop();
                        answer = answer + 2;
                    }else{
                        stack.push(doll);
                    }
                    break;
                }
            }
        }
        
        
        return answer;
    }
}