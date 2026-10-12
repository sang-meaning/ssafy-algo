import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int[] dp = new int[100000]; // 1 ~ 99999 메모이제이션

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        Arrays.fill(dp, -1);

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(t).append(" ").append(getMaxTurn(n)).append("\n");
        }
        System.out.print(sb);
    }

    static int getMaxTurn(int n) {
        // 10 미만의 한 자리 수는 더 이상 쪼갤 수 없음
        if (n < 10) return 0;

        // 이미 계산된 값이면 반환
        if (dp[n] != -1) return dp[n];

        String s = String.valueOf(n);
        int len = s.length();
        int maxTurns = 0;

        // 숫자의 사이 공간(len - 1개)에 분할 지점(비트마스크)을 선택
        // 예: 4자리 수라면 3개의 위치 중 1개 이상을 선택 (1부터 (1<<3)-1 까지)
        int numGaps = len - 1;
        int limit = 1 << numGaps;

        for (int mask = 1; mask < limit; mask++) {
            int product = 1;
            int start = 0;

            for (int i = 0; i < numGaps; i++) {
                // i번째 갭에서 숫자를 잘라야 하는 경우
                if ((mask & (1 << i)) != 0) {
                    int subNum = Integer.parseInt(s.substring(start, i + 1));
                    product *= subNum;
                    start = i + 1;
                }
            }
            // 마지막 조각 처리
            product *= Integer.parseInt(s.substring(start));

            // 현재 분할로 얻은 곱으로 다음 턴 진행 (+1턴)
            maxTurns = Math.max(maxTurns, 1 + getMaxTurn(product));
        }

        return dp[n] = maxTurns;
    }
}