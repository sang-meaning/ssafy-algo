import java.io.*;
import java.util.*;

public class Solution {

    static Map<Integer, Integer> memo;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            int number = Integer.parseInt(br.readLine().trim());

            memo = new HashMap<>();

            int answer = getMaxTurn(number);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }

    // 현재 숫자에서 얻을 수 있는 최대 턴 수
    static int getMaxTurn(int number) {

        // 한 자리 숫자이면 게임 종료
        if (number < 10) {
            return 0;
        }

        // 이미 계산한 숫자라면 결과 재사용
        if (memo.containsKey(number)) {
            return memo.get(number);
        }

        String str = Integer.toString(number);

        int maxTurn = split(str, 0, 1, 0);

        memo.put(number, maxTurn);

        return maxTurn;
    }

    // 숫자를 자르는 모든 경우 탐색
    static int split(
            String str,
            int start,
            int product,
            int pieceCount
    ) {

        // 숫자를 끝까지 잘랐을 때
        if (start == str.length()) {

            // 두 조각 이상이어야 실제로 자른 경우
            if (pieceCount >= 2) {
                return 1 + getMaxTurn(product);
            }

            // 숫자를 한 번도 자르지 않은 경우
            return 0;
        }

        int maxTurn = 0;
        int currentNumber = 0;

        /*
         * start 위치부터 숫자를 한 자리씩 붙인다.
         *
         * 123에서 start가 0이면
         * 1, 12, 123을 차례대로 만든다.
         */
        for (int end = start; end < str.length(); end++) {

            int digit = str.charAt(end) - '0';

            currentNumber =
                    currentNumber * 10 + digit;

            int result = split(
                    str,
                    end + 1,
                    product * currentNumber,
                    pieceCount + 1
            );

            maxTurn = Math.max(maxTurn, result);
        }

        return maxTurn;
    }
}