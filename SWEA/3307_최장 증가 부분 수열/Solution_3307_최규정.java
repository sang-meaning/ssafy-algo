import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();

            int[] arr = new int[N];
            int[] dp = new int[N];

            // 수열 입력
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            int answer = 0;

            // LIS 계산
            for (int i = 0; i < N; i++) {

                // 자기 자신만 선택하는 경우
                dp[i] = 1;

                // 현재 숫자보다 앞에 있는 숫자 확인
                for (int j = 0; j < i; j++) {

                    // 증가하는 수열을 만들 수 있다면
                    if (arr[j] < arr[i]) {

                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                // 지금까지 계산한 LIS 중 최댓값
                answer = Math.max(answer, dp[i]);
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}