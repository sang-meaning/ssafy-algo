class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    public int dfs(int[] numbers, int target, int depth, int current){
        if(depth == numbers.length){
            if(current == target) return 1;
            return 0;
        }
        return dfs(numbers, target, depth+1, current+numbers[depth]) + dfs(numbers, target, depth+1, current-numbers[depth]);
    }
}