class Solution {
    public int solution(int[] nums) {
        int size = nums.length;
        int cnt = 0;
        for(int i=2; i<size; i++){
            for(int j=1; j<i; j++){
                if(i==j) continue;
                for(int k=0; k<j; k++){
                    if(i==k || j==k) continue;
                    if(isPrime(nums[i]+nums[j]+nums[k])) cnt++;
                }
            }
        }
        return cnt;
    }
    public boolean isPrime(int num){
        for(int i=2; i*i<=num; i++){
            if(num%i==0) return false;
        }
        return true;
    }
}