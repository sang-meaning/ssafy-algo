import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    static StringBuilder sb = new StringBuilder();

    static int T;

    /*
     * memo[num]
     * = num에서 시작했을 때 만들 수 있는 최대 turn 수
     *
     * -1이면 아직 계산하지 않은 상태
     */
    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());

        Arrays.fill(memo, -1);

        // 한 자리 숫자는 더 이상 쪼갤 수 없음
        for (int i = 0; i <= 9; i++) {
            memo[i] = 0;
        }

        for (int tc = 1; tc <= T; tc++) {

            int num = Integer.parseInt(br.readLine());

            int result = dfs(num);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(result)
              .append("\n");
        }

        System.out.print(sb);
    }


    /*
     * num에서 시작했을 때
     * 최대 몇 번 쪼갤 수 있는지 반환
     */
    static int dfs(int num) {

        // 한 자리 숫자는 더 이상 분할 불가
        if (num < 10) {
            return 0;
        }

        // 이미 계산한 숫자라면 바로 반환
        if (memo[num] != -1) {
            return memo[num];
        }

        String str = String.valueOf(num);

        int len = str.length();

        int maxTurn = 0;


        /*
         * 숫자 사이의 경계 개수는 len - 1
         *
         * 예)
         * 123
         *
         * 1 ? 2 ? 3
         *
         * 경계 2개
         *
         * mask
         *
         * 01 -> 1 | 23
         * 10 -> 12 | 3
         * 11 -> 1 | 2 | 3
         *
         * 00은 아예 안 자르는 경우라 제외
         */
        int limit = 1 << (len - 1);

        for (int mask = 1; mask < limit; mask++) {

            int product = 1;

            int start = 0;


            /*
             * 각 경계를 확인
             */
            for (int i = 0; i < len - 1; i++) {

                /*
                 * i번째 경계를 자르는 경우
                 */
                if ((mask & (1 << i)) != 0) {

                    /*
                     * start ~ i까지 하나의 숫자로 만듦
                     *
                     * substring의 끝 인덱스는 미포함이므로
                     * i + 1
                     */
                    int value =
                            Integer.parseInt(
                                    str.substring(start, i + 1)
                            );

                    product *= value;

                    start = i + 1;
                }
            }


            /*
             * 마지막 조각 처리
             *
             * 예)
             * 123에서
             *
             * 1 | 23
             *
             * 앞에서 1을 처리했다면
             * 마지막 23을 여기서 처리
             */
            int value =
                    Integer.parseInt(
                            str.substring(start)
                    );

            product *= value;


            /*
             * 이번에 한 번 쪼갰으므로 +1
             *
             * 그리고 product에서 다시 최대 turn 계산
             */
            int turn = 1 + dfs(product);

            maxTurn = Math.max(maxTurn, turn);
        }


        memo[num] = maxTurn;

        return maxTurn;
    }
}