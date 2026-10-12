package submission;

public class Solution {

	static boolean[] sign = new boolean[20];
	static int count = 0;

	public static int solution(int[] numbers, int target) {
		dfs(0, 0, numbers, target);
		return count;
	}

	public static void dfs(int s, int depth, int[] numbers, int target) {

		if (depth == numbers.length + 1)
			return;

		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (sign[i]) {
				sum += numbers[i];
			} else {
				sum -= numbers[i];
			}
		}
		if (sum == target) {
			count++;
		}

		for (int i = s; i < numbers.length; i++) {
			sign[i] = true;
			dfs(i + 1, depth + 1, numbers, target);
			sign[i] = false;
		}
	}
}
