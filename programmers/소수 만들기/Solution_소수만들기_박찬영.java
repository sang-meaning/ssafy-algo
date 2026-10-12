class Solution {
    public int solution(int[] nums) {
    int count = 0;
    
    for (int i = 0; i < nums.length -2; i++){
        for (int j = i+1; j < nums.length -1; j++){
            for (int k = j+1; k < nums.length; k++){
                int sum = nums[i] + nums[j] + nums[k];
                
                if (isPrime(sum)){
                    count++;
                }
            }
        }
    }
    return count; 
    }     
        
        
        
        
    public boolean isPrime(int m){
        if(m<2){
            return false;
        }
        for(int i=2; i*i<=m; i++){
            if(m%i == 0){
                return false;
            }
        }
        return true;
    }        
        
        
}