class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					int sum = nums[i] + nums[j] + nums[k];
					if (isPrime(sum)) {
						answer++;
					}
				}
			}
		}

        return answer;
    }
    
    boolean isPrime(int sum){
        if (sum == 2) {
			return true;
		}
		for (int l = 2; l * l <= sum; l++) {
			if (sum % l == 0) {
				return false;
			}
		}
		return true;
	}
}