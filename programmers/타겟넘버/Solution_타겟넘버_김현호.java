class Solution {
    static int answer;
    static int[] nums;
    static boolean[] selected;
    static int targ;
    public static void dfs(int start, int count){
        if(start == nums.length){
            if(count == targ){
                answer++;
            }
            return;
        }
        dfs(start + 1, count + nums[start]);
        dfs(start + 1, count - nums[start]);
    }
    public int solution(int[] numbers, int target) {
        answer = 0;
        nums = numbers;
        targ = target;
        selected = new boolean[nums.length];
        dfs(0,0);
        return answer;
    }
}