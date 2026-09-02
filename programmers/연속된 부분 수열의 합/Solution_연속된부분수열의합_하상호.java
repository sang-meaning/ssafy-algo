package al_prac.pgms;

public class Solution_연속된부분수열의합_하상호 {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        long sum = 0;

        int answerLeft = 0;
        int answerRight = sequence.length - 1;
        int minLength = sequence.length + 1;

        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];

            // 합이 k보다 크면 왼쪽 원소를 제거
            while (sum > k && left <= right) {
                sum -= sequence[left];
                left++;
            }

            if (sum == k) {
                int length = right - left + 1;

                if (length < minLength) {
                    minLength = length;
                    answerLeft = left;
                    answerRight = right;
                }
            }
        }

        return new int[] {answerLeft, answerRight};
    }
}