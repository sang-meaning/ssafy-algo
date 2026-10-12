class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(0, 0, target, numbers);
        return answer;
    }
    public int dfs(int idx, int sum, int target, int[] numbers){
        if(idx == numbers.length){
            if(sum == target) return 1;
            return 0;
        }
        
        return dfs(idx+1, sum + numbers[idx], target, numbers)+
            dfs(idx+1, sum - numbers[idx], target, numbers);
        
    }
}