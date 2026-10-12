import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            // 이용권 가격
            int dayPrice = sc.nextInt();
            int monthPrice = sc.nextInt();
            int threeMonthPrice = sc.nextInt();
            int yearPrice = sc.nextInt();

            // 각 달의 최소 이용 비용
            int[] monthCost = new int[13];

            for (int i = 1; i <= 12; i++) {

                int days = sc.nextInt();

                // 1일권과 1달권 중 저렴한 비용 선택
                monthCost[i] = Math.min(days * dayPrice,monthPrice);
            }

            // DP 배열
            int[] dp = new int[16];

            Arrays.fill(dp, Integer.MAX_VALUE);

            // 1월 시작 전에는 비용이 없음
            dp[1] = 0;

            // 1월부터 12월까지 확인
            for (int i = 1; i <= 12; i++) {

                //이번 달을 개별 이용권으로 처리
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + monthCost[i]);

                //이번 달부터 3달권 사용
                dp[i + 3] = Math.min(dp[i + 3], dp[i] + threeMonthPrice);
            }

            // 1년권 가격으로 정답 초기화
            int answer = yearPrice;

            // 12월까지 이용 계획을 모두 처리한 경우
            for (int i = 13; i <= 15; i++) {
                answer = Math.min(answer, dp[i]);
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}