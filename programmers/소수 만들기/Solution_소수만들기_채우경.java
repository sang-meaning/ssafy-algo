class Solution {
     int answer = 0;
    public int solution(int[] nums) {
       
        comb(nums,0,0,0);
        return answer; 
    }
    
    private void comb(int[] nums,int start,int dep,int sum){
        
        if(dep==3){
            if(sum<2)
                return;
            
            for(int i=2;i*i<=sum;i++){
                if(sum%i==0)
                    return;
            }
            answer++;
            return;
        }
        
        for(int i=start;i<nums.length;i++){
            comb(nums,i+1,dep+1,sum+nums[i]);
        }
    }
    
    
}