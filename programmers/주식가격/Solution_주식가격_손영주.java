package _submission;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {

	// 가장 최근에 들어온 상태를 먼저 처리
	// 처리되지 않은 상태를 기억
	// 들어가는 타이밍의 시간을 기억하기
	// 나오는 타이밍의 시간 - 들어간 타이밍의 시간

	static public int[] solution(int[] prices) {

		Deque<Integer> stack = new ArrayDeque<>();

		int[] answer = new int[prices.length];

		for (int sec = 0; sec < prices.length; sec++) {
			if (stack.isEmpty()) {
				stack.push(sec); // sec은 들어간 시간이자 price 인덱스
			} else {
				// 이 값으로 인해 하한선 갱신되는 값을 전부 제거, 나오는 타이밍의 시간 - 들어간 타이밍의 시간을 기록
				while (!stack.isEmpty() && prices[stack.peek()] > prices[sec]) {
					int pop = stack.pop();
					answer[pop] = sec - pop;
				}
				stack.push(sec);
			}
		}

		// 끝까지 남은 값 기록
		while (!stack.isEmpty()) {
			int pop = stack.pop();
			answer[pop] = prices.length - 1 - pop;
		}

		return answer;
	}

}
