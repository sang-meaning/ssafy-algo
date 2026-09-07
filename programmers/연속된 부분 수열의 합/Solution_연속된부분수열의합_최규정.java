import java.util.*;


class Solution {
    public int[] solution(int[] sequence, int k) {

        int left = 0;
        int right = 0;
        int sum = sequence[0];

        int bestLeft = 0;
        int bestRight = sequence.length - 1;
        int bestLength = Integer.MAX_VALUE;

        while (left < sequence.length && right < sequence.length) {

            if (sum == k) {

                int length = right - left;

                if (length < bestLength) {
                    bestLength = length;
                    bestLeft = left;
                    bestRight = right;
                }

                sum = sum - sequence[left];
                left++;
            }

            else if (sum < k) {

                right++;

                if (right < sequence.length) {
                    sum = sum + sequence[right];
                }
            }

            else {

                sum = sum - sequence[left];
                left++;
            }
        }
        int[] answer = {bestLeft, bestRight};
        
        return answer;
    }
}