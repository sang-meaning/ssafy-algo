
public class Solution {
	static int count = 0;

	public int solution(int[] nums) {
		backtrack(nums, 0, 0, 0);
		return count;
	}

	public void backtrack(int[] nums, int idx, int depth, int sum) {
		if (depth == 3) {
			if (isPrime(sum)) {
				count++;
			}
			return;
		}

		for (int start = idx; start < nums.length; start++) {
			backtrack(nums, start + 1, depth + 1, sum + nums[start]);
		}
	}

	public boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
