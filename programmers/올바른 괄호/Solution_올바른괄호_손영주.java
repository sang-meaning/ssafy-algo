package submission;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	static boolean solution(String s) {
		boolean answer = true;
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == ')') {
				if (stack.isEmpty())
					return false;
				stack.pop();
			} else {
				stack.push(s.charAt(i));
			}

		}

		answer = stack.isEmpty() ? true : false;

		return answer;
	}
}
