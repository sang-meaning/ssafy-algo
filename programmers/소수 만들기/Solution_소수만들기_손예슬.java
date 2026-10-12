class Solution {
    public int solution(int[] nums) {
        int answer = 0;
    
        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i+1; j < nums.length - 1; j++){
                for (int r = j+1; r < nums.length; r++){
                    int total = nums[i] + nums[j] + nums[r];
                    answer += isPrime(total);
                }
            }
        }


        return answer;
    }

    // 소수 판별 메서드 (합은 항상 3이상이니까)
    int isPrime(int total){
        for(int i = 2; i * i <= total; i++){
            if(total % i == 0) return 0;
        }
        return 1;
    }
}


