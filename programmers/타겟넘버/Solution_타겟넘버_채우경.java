class Solution {
    static int cnt=0;
    public int solution(int[] numbers, int target) {
        cnt=0;
        
        dfs(numbers,target,0,0);
        
        return cnt;
    }
    
    static void dfs(int[] numbers,int target,int depth,int sum){
        
        
        if(numbers.length==depth){
            if(sum==target)
            {
                cnt++;
                
            }
            return;
        }
        
        dfs(numbers,target,depth+1,sum-numbers[depth]);
        dfs(numbers,target,depth+1,sum+numbers[depth]);
    }
}