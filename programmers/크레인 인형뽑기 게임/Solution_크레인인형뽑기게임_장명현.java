import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Stack <Integer> s = new Stack<>();
        for (int t=0; t<moves.length; t++) {
            int j = moves[t]-1;
        	int pick = 0;
        	for (int i=0; i<board.length; i++) {
        		if (board[i][j] > 0) {
        			pick = board[i][j];
                    board[i][j] = 0;
        			break;
        		}
        	}
        	
        	if (pick == 0) continue;
            
        	if (!s.isEmpty() && s.peek() == pick) {
        		s.pop();
        		answer+=2;
        	} else {
        		s.add(pick);
        	}
        }
        
        return answer;
    }
}