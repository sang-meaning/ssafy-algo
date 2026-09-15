import java.io.*;
import java.util.*;

public class Solution {

    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Arrays.fill(memo, -1);

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            int answer = dfs(N);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }

    static int dfs(int n) {

        if (n < 10) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        String s = String.valueOf(n);

        int len = s.length();
        int maxTurn = 0;

        for (int mask = 1; mask < (1 << (len - 1)); mask++) {

            int product = 1;
            int start = 0;

            for (int i = 0; i < len - 1; i++) {

                if ((mask & (1 << i)) != 0) {

                    int num = Integer.parseInt(s.substring(start, i + 1));

                    product *= num;

                    start = i + 1;
                }
            }

            int num = Integer.parseInt(s.substring(start));
            product *= num;

            maxTurn = Math.max(maxTurn, 1 + dfs(product));
        }

        memo[n] = maxTurn;

        return maxTurn;
    }
}