class Solution {

    static int answer;

    public int solution(int[] numbers, int target) {

        answer = 0;

        dfs(numbers, target, 0, 0);

        return answer;
    }

    static void dfs(int[] numbers, int target, int idx, int sum) {

        // 모든 숫자를 다 사용했으면
        if (idx == numbers.length) {

            // 지금까지 만든 합이 target이면
            if (sum == target) {
                answer++;
            }

            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, idx + 1, sum + numbers[idx]);

        // 현재 숫자를 빼는 경우
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}