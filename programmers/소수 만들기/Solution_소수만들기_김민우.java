class Solution {
    static int answer;
    static int[] num;
    public int solution(int[] nums) {
        answer = 0;
        num = nums;
        sum_dfs(-1, 0, 0);
        return answer;
        
    }
    
    public void sum_dfs(int idx, int curSum, int cnt){
        if(idx == (num.length-1)){
            if(cnt != 3) 
                return;
            for(int i = 2; i*i <= curSum; i++)
                if(curSum % i == 0)
                    return;
            answer++;
            return;
        }
        if(cnt == 3){
            for(int i = 2; i*i <= curSum; i++)
                if(curSum % i == 0)
                    return;
            answer++;
            return;
        }
        
        sum_dfs(idx+1, curSum + num[idx+1], cnt+1);
        sum_dfs(idx+1, curSum, cnt);
    }
}