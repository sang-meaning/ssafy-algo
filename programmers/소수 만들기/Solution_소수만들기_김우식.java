class Solution {
    public int solution(int[] nums) {
        // 이거는 한번 배열 쫙 만들어서 한번만 봄
        int answer = 0;
        int sum = 0;        
        int max = 0;
        for(int n : nums){
            max += n;
        }
        boolean[] isPrime = new boolean[max + 1];
        isPrime[0] = isPrime[1] = true;
        
        for(int i = 2; i * i <= max; i++){
            if(isPrime[i]) continue;
            for(int j = i * i; j <= max; j += i){
                isPrime[j] = true;
            }
        }
        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                for(int k = j + 1; k < nums.length; k++){
                    sum = nums[i] + nums[j] + nums[k];
                    if(!isPrime[sum])
                        answer++;
                }
            }
        }
        return answer;
        
        // 이거는 매번 프라임 드감
        // for(int i = 0; i < nums.length - 2; i++){
        //     for(int j = i + 1; j < nums.length - 1; j++){
        //         for(int k = j + 1; k < nums.length; k++){
        //             sum = nums[i] + nums[j] + nums[k];
        //             if(isPrime(sum))
        //                 answer++;
        //         }
        //     }
        // }
        // return answer;
        
    }
    
    // public static boolean isPrime(int n){
    //     if(n < 2){
    //         return false;
    //     }
    //     for(int i = 2; i * i <= n; i++){
    //         if(n%i == 0){
    //           return false;   
    //         }
    //     }
    //     return true;
    // }
}
