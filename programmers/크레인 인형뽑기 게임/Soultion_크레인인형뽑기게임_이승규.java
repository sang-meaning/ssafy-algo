import java.util.ArrayDeque;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        // 가져올놈 가져오기. 가져온데 0으로
        // stack에서 peak. 다르다면, 가져온놈 push. 같다면, pop 이후 answer++
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < moves.length; i++) {
            int target = moves[i] - 1;
            int doll = 0;
            
            for(int j = 0; j < board.length; j++) {
                if(board[j][target] == 0) 
                    continue;
                else {
                    doll = board[j][target];
                    board[j][target] = 0;
                    break;
                }
            }
            
            if(doll == 0) {
                continue;
            }
            
            if(stack.isEmpty()){
                stack.push(doll);
            } else {
                if(stack.peek() == doll) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(doll);
                }
            }
        }
        
        return answer;
    }
}