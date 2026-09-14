package swea;

import java.io.*;
import java.util.*;

public class Solution_7206_하상호 {

    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Arrays.fill(memo, -1);

        // 한 자리 숫자는 더 이상 자를 수 없음
        for (int i = 0; i <= 9; i++) {
            memo[i] = 0;
        }

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            int answer = dfs(N);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static int dfs(int num) {

        // 한 자리 숫자
        if (num < 10) {
            return 0;
        }

        // 이미 계산한 숫자
        if (memo[num] != -1) {
            return memo[num];
        }

        String str = String.valueOf(num);

        int length = str.length();

        int max = 0;

        /*
         * 숫자 사이의 공간은
         * length - 1개
         *
         * 각 위치를 자를지 말지
         * 비트마스킹으로 표현
         */
        int total = 1 << (length - 1);

        // 0은 한 번도 자르지 않는 경우이므로 제외
        for (int mask = 1; mask < total; mask++) {

            int product = 1;

            int current = 0;

            for (int i = 0; i < length; i++) {

                // 현재 숫자 만들기
                current = current * 10
                        + (str.charAt(i) - '0');

                /*
                 * 마지막 자리거나
                 * 현재 위치에서 자르는 경우
                 */
                if (i == length - 1 ||
                        (mask & (1 << i)) != 0) {

                    product *= current;

                    current = 0;
                }
            }

            /*
             * 현재 게임 한 번 +
             * 만들어진 숫자로 계속 게임
             */
            int count = 1 + dfs(product);

            max = Math.max(max, count);
        }

        memo[num] = max;

        return max;
    }
}