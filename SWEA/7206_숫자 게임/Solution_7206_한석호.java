import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    // N <= 99,999 이지만, 곱셈 과정에서 더 큰 숫자가 나올 수 있으므로 DP 배열 크기를 100,000으로 설정
    // 10만 이상의 숫자는 메모이제이션 없이 재귀 호출로 처리
    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        // 메모이제이션 배열 초기화 (-1로 미방문 표시)
        Arrays.fill(memo, -1);

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine().trim());

            int result = solve(n);

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static int solve(int n) {
        // 10 미만의 한 자릿수 숫자는 더 이상 쪼갤 수 없음 -> 0턴
        if (n < 10) {
            return 0;
        }

        // 메모이제이션 체크 (10만 미만인 경우)
        if (n < 100000 && memo[n] != -1) {
            return memo[n];
        }

        String s = String.valueOf(n);
        int len = s.length();
        int maxTurn = 0;

        // 숫자의 자릿수 사이에 '절단면'을 놓는 비트마스크 탐색
        // len자릿수면 사이 공간은 (len - 1)개 존재
        // (1 << (len - 1)) - 1 은 모든 절단면 조합 (1부터 (1 << (len - 1)) - 1 까지)
        int cutCount = len - 1;
        int maxBit = 1 << cutCount;

        for (int flag = 1; flag < maxBit; flag++) {
            int product = 1;
            int curNum = s.charAt(0) - '0';

            for (int i = 0; i < cutCount; i++) {
                // i번째 자리에 절단면이 있는지 확인 (1이면 쪼갬, 0이면 숫자를 이어붙임)
                if ((flag & (1 << i)) != 0) {
                    product *= curNum;
                    curNum = s.charAt(i + 1) - '0';
                } else {
                    curNum = curNum * 10 + (s.charAt(i + 1) - '0');
                }
            }
            // 마지막 남은 수 곱해주기
            product *= curNum;

            // 쪼갠 후 곱한 결과로 다음 턴 진행 (+1 턴 추가)
            maxTurn = Math.max(maxTurn, 1 + solve(product));
        }

        // 메모이제이션 저장
        if (n < 100000) {
            memo[n] = maxTurn;
        }

        return maxTurn;
    }
}