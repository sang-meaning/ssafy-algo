class Solution_소수만들기_강지우 {
	public int solution(int[] nums) {
        int count = 0;

        for (int i=0; i<nums.length-2; i++) {
            for (int j=i+1; j<nums.length-1; j++) {
                for (int k=j+1; k<nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];


                    if (isPrime(sum)) {
                        count++;
                    }
                }
            }
        }

	    return count;
	}
	
	private boolean isPrime(int n) {
	    if(n<2) {
	        return false;
	    }
	    
	    for (int i=2; i*i<=n; i++) {
	        if(n%i==0) {
	            return false;
	        }
	    }
	    return true;
	    
	}
}