class Solution_타겟넘버_임성진 {
    private int[] numbers;
    private int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        return dfs(0, 0);
    }

    private int dfs(int idx, int sum) {
        if (idx == numbers.length) {
            return sum == target ? 1 : 0;
        }
        return dfs(idx + 1, sum + numbers[idx]) + dfs(idx + 1, sum - numbers[idx]);
    }
}