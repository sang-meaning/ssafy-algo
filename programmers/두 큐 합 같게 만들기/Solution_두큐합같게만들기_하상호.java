package al_prac.pgms;

public class Solution_두큐합같게만들기_하상호 {
    public int solution(int[] queue1, int[] queue2) {
        int n1 = queue1.length;
        int n2 = queue2.length;
        int totalLength = n1 + n2;

        int[] combined = new int[totalLength];

        long queue1Sum = 0;
        long totalSum = 0;

        for (int i = 0; i < n1; i++) {
            combined[i] = queue1[i];
            queue1Sum += queue1[i];
            totalSum += queue1[i];
        }

        for (int i = 0; i < n2; i++) {
            combined[n1 + i] = queue2[i];
            totalSum += queue2[i];
        }

        // 전체 합이 홀수면 두 큐의 합을 같게 만들 수 없음
        if (totalSum % 2 != 0) {
            return -1;
        }

        long target = totalSum / 2;

        int left = 0;
        int right = n1;
        int operationCount = 0;
        int maxOperation = totalLength * 3;

        while (operationCount <= maxOperation) {
            if (queue1Sum == target) {
                return operationCount;
            }

            if (queue1Sum > target) {
                // queue1에서 원소를 꺼내 queue2에 삽입
                queue1Sum -= combined[left % totalLength];
                left++;
            } else {
                // queue2에서 원소를 꺼내 queue1에 삽입
                queue1Sum += combined[right % totalLength];
                right++;
            }

            operationCount++;
        }

        return -1;
    }
}