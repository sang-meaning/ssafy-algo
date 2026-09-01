import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int floor = board.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int position : moves){
            for(int index = 0; index<floor;index++){
                if(board[index][position-1]!=0){
                    if(stack.isEmpty()){
                        stack.push(board[index][position-1]);
                    }else{
                        int top = stack.peek();
                        if(top == board[index][position-1]){
                            stack.pop();
                            answer+=2;
                        }else{
                            stack.push(board[index][position-1]);
                        }
                    }
                    board[index][position-1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}