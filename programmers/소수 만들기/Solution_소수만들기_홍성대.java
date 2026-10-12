class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        //nums 원소의 최대값은 1000이므로, 3개의 합의 최대값은 2997
        int MAX = 3000;
        boolean[] isNotPrime = new boolean[MAX + 1];
        isNotPrime[0] = isNotPrime[1] = true;
        //크기 3000짜리 에라토스테네스의 체 생성
        for (int i = 2; i * i <= MAX; i++) {
            if (isNotPrime[i]) continue;
            for (int j = i * i; j <= MAX; j += i) {
                isNotPrime[j] = true;
            }
        }
        
        //3개 숫자의 모든 조합을 구해서 바로 소수인지 확인 
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    
                    // 합이 소수라면 카운트 증가
                    if (!isNotPrime[sum]) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}