package submission;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

	static public int solution(int[][] board, int[] moves) {

		Deque<Integer> stack = new ArrayDeque<>();
		int count = 0;

		for (int i = 0; i < moves.length; i++) {
			int y = 0;
			int move = moves[i] - 1;
			while (y < board.length - 1 && board[y][move] == 0) {
				y++;
			}

			int target = board[y][move];
			if (target == 0)
				continue;

			board[y][move] = 0;

			if (stack.isEmpty()) {
				stack.push(target);
			} else if (!stack.peek().equals(target)) {
				stack.push(target);
			} else {
				stack.pop();
				count++;
			}
		}
		return count * 2;
	}
}
