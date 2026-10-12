package al_prac.pgms;


public class Solution_타겟넘버_하상호 {

    static int answer;

    public int solution(int[] numbers, int target) {
        answer = 0;

        dfs(numbers, target, 0, 0);

        return answer;
    }

    static void dfs(int[] numbers, int target, int depth, int sum) {
        // 모든 숫자의 부호를 결정한 경우
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(numbers, target, depth + 1, sum + numbers[depth]);

        // 현재 숫자를 빼는 경우
        dfs(numbers, target, depth + 1, sum - numbers[depth]);
    }
}