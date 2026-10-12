
class Solution_소수만들기_강상민 {
    public int solution(int[] nums) {
        int answer = 0;
        
        // 50 C 3 * 3000 = 50 50 50 /6 * 3000 = 125 * 1000 * 500 = 62500000
        
        for (int i=0; i<nums.length-2; i++) {
            for (int j=i+1; j<nums.length-1; j++) {
                for (int k=j+1; k<nums.length; k++) {
                    int a = nums[i];
                    int b = nums[j];
                    int c = nums[k];
                    
                    if (prime(a+b+c)) answer++;
                }
            }
        }


        return answer;
    }
    
    static boolean prime(int p) {
        for (int i=2; i<p; i++) {
            if (p % i == 0) return false;
        }
        
        return true;
    }
}