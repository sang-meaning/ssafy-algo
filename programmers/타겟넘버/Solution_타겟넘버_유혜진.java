class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        // 0번째 숫자부터, 누적 합 0으로 DFS 탐색 시작
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private void dfs(int[] numbers, int target, int index, int sum) {
        // [기저 조건] 모든 숫자를 다 탐색했을 때
        if (index == numbers.length) {
            // 지금까지의 계산 결과가 target과 같다면 정답 개수 +1
            if (sum == target) {
                answer++;
            }
            return;
        }

        // 1번 선택지: 현재 숫자를 더하고 다음 숫자로 넘어가기
        dfs(numbers, target, index + 1, sum + numbers[index]);

        // 2번 선택지: 현재 숫자를 빼고 다음 숫자로 넘어가기
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}