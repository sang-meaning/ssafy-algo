package submission;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


public class Solution{
	public static int[] solution(int[] arr) {
		int N = arr.length;

		Deque<Integer> stack = new ArrayDeque<>();

		for (int num : arr) {
			if (stack.isEmpty() || stack.peek() != num)
				stack.push(num);
		}

		int[] answer = new int[stack.size()];
		int idx = stack.size() - 1;
		while (!stack.isEmpty()) {
			answer[idx--] = stack.pop();
		}

		return answer;
	}
}
