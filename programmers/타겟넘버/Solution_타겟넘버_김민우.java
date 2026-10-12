class Solution {
    
    static int sol = 0;
    static int[] num_list;
    static int T;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        num_list = numbers;
        T = target;
        
        dfs(-1, 0);
        answer = sol;
        return answer;
    }
    
    public void dfs(int idx, int sum){
        
        if(idx == (num_list.length-1)){
            if(sum == T)
                sol++;
            return;
        }
        
        else{
            dfs(idx+1, sum+num_list[idx+1]);
            dfs(idx+1, sum-num_list[idx+1]);
        }
    }
}