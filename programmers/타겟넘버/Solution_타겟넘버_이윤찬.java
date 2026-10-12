class Solution {
    static int len;
    static int answer;
    public int solution(int[] numbers, int target) {
        
        len  = numbers.length;
        answer = 0;
    
        
        
        dfs(numbers,0,0,target);
        
        return answer;
        
        
        
    }
    public void dfs(int[] arr , int sum , int leng, int target){
        
        if(leng == len){
            if(sum == target){
                answer++;
            }
            return;
        }
        
        dfs( arr ,sum + arr[leng], leng+1, target ); // 0 , 1, 2, 3
        
        dfs( arr, sum - arr[leng], leng+1 ,target);
        
        
    }
}