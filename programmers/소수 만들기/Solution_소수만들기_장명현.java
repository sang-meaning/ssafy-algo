class Solution {
    public int solution(int[] nums) {
        boolean[] isNotPrime = new boolean[3001];
        for (int i=2; i*i<=3000; i++) {
            for (int j=i*2; j<=3000; j+=i) {
                isNotPrime[j] = true;
            }
        }
        
        int answer = 0;
        int n = nums.length;
        for (int i=0; i<n-2; i++) {
            for (int j=i+1; j<n-1; j++) {
                for (int k=j+1; k<n; k++) {
                    int suma = nums[i] + nums[j] + nums[k];
                    if (isNotPrime[suma] == false) answer++;
                }
            }
        }
        
        return answer;
    }
}