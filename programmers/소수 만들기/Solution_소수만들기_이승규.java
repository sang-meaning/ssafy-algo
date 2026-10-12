class Solution {
		public int solution(int[] nums) {
			int answer = 0;
			int numsLen = nums.length;

			for (int i = 0; i < numsLen; i++) {
				for (int j = i + 1; j < numsLen; j++) {
					for (int k = j + 1; k < numsLen; k++) {
						if (isPrime(nums[i] + nums[j] + nums[k])) { // 3개 더하기 + 소수판별, 2 이하는 들어갈수없음
							answer++;
						}
					}
				}
			} 

			return answer;
		}

		public boolean isPrime(int target) {
			for (int i = 2; i < target; i++) {
				if (target % i == 0)
					return false; // 합성수
			}
			return true; // 소수
		}
	}