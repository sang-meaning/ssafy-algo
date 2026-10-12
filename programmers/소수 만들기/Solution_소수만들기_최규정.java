class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length -1; j++){
                for(int k = j+1; k < nums.length; k++){ //3중 반복문을 이용하여
                    int sum = nums[i] + nums[j] + nums[k];//서로다른 세 수의 합
                    
                    
                    boolean prime = true; //소수 확인용
                    
                    for(int n = 2; n * n <= sum; n++){ //제곱근 이하까지의 반복
                       if(sum % n == 0){ //제곱근 이하의 수로 나누어 떨어진다면
                           prime = false;//합성수임이 판별
                           break;
                       }
                    } 
                    
                    if(prime){
                        answer++; //prime이 true라면 sum는 소수이다.
                    }
                        
                    
                }
            }
        }
      
        return answer;
    }
}