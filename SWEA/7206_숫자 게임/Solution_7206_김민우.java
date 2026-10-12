import java.io.*;
import java.util.*;

public class Solution {

    static int T;
    static Map<Integer, Integer> memo;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());

            memo = new HashMap<>();

            int answer = dfs(number);

            sb.append("#"+test_case+" "+answer+"\n");
        }

        System.out.print(sb);
    }

    /**
     * number에서 시작했을 때 만들 수 있는 최대 추가 턴 수
     */
    static int dfs(int number) {

        // 한 자리 숫자는 더 이상 나눌 수 없다.
        if (number < 10) {
            return 0;
        }

        // 이미 이전 분할에서 계산한 숫자라면 결과 재사용
        if (memo.containsKey(number)) {
            return memo.get(number);
        }

        String str = String.valueOf(number);
        int length = str.length();

        int maxTurn = 0;

        int maxMask = 1 << (length - 1);

        for (int mask = 1; mask < maxMask; mask++) {

            int product = 1;
            int start = 0;

            // 각 숫자 사이를 확인
            for (int i = 0; i < length - 1; i++) {

                // i번째 경계에서 자르는 경우
                if ((mask & (1 << i)) != 0) {

                    int dividedNumber = Integer.parseInt(str.substring(start, i + 1));

                    product *= dividedNumber;
                    start = i + 1;
                }
            }

            // 마지막으로 남은 숫자 처리
            int lastNumber = Integer.parseInt(str.substring(start));

            product *= lastNumber;

            // 현재 분할 1회 + 다음 숫자에서의 최대 턴
            maxTurn = Math.max(maxTurn, 1 + dfs(product));
        }

        memo.put(number, maxTurn);

        return maxTurn;
    }
}