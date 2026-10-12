import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
		long sum1 = 0;
		long sum2 = 0;
		long middleSum = 0;
		Queue<Integer> que1 = new ArrayDeque<>();
		Queue<Integer> que2 = new ArrayDeque<>();

		for (int i = 0; i < queue1.length; i++) {
			que1.offer(queue1[i]);
			sum1 += queue1[i];
		}

		for (int i = 0; i < queue2.length; i++) {
			que2.offer(queue2[i]);
			sum2 += queue2[i];
		}
        if ((sum1+ sum2) % 2 != 0) {
            return -1;
        }
		middleSum = (sum1 + sum2) / 2;
        int count = 0;
        int maxCount = queue1.length * 4;
		while (sum1 != middleSum && sum2 != middleSum && count< maxCount) {
            
			if (sum1 > middleSum) {
				int num1 = que1.poll();
				que2.offer(num1);
				sum1 -= num1;
				sum2 += num1;
			} else if(sum2 > middleSum) {
				int num2 = que2.poll();
				que1.offer(num2);
				sum1 += num2;
				sum2 -= num2;
			}
            count++;
		}
		return sum1 == middleSum ? count : -1;
	}
}