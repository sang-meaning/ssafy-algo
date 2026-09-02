import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Deque<Integer>[] deq = new ArrayDeque[board.length];
        Deque<Integer> deq6 = new ArrayDeque<>();
        for (int i = 0; i < board.length; i++) {
            deq[i] = new ArrayDeque<>();
            for (int j = 0; j < board[i].length; j++) {
                if(board[j][i] != 0){
                    deq[i].offer(board[j][i]);    
                }
            }
        }
        for(int i = 0; i < moves.length; i++){
            int select = 0;
            if(!deq[moves[i]-1].isEmpty()){
                select = deq[moves[i]-1].pollFirst();    
            }else{
                continue;
            }
            if(!deq6.isEmpty()){
                if(deq6.peekLast() == select){
                    answer += 2;
                    deq6.pollLast();
                }else{
                    deq6.offerLast(select);
                }
            }else{
                    deq6.offerLast(select);
            }
        }
        return answer;
    }
}