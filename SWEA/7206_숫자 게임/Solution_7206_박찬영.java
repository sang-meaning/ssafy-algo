import java.io.*;
import java.util.*;

public class Solution {
    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(tc).append(" ").append(play(N)).append("\n");
        }

        System.out.print(sb);
    }

    static int play(int num) {
        if (num < 10) {
            return 0;
        }

        if (memo[num] != 0) {
            return memo[num];
        }

        String str = String.valueOf(num);
        int len = str.length();
        int gaps = len - 1; 
        int maxTurns = 0;

        for (int mask = 1; mask < (1 << gaps); mask++) {
            int product = 1;
            int startIdx = 0;

            for (int i = 0; i < gaps; i++) {
                if ((mask & (1 << i)) != 0) {
                    int piece = Integer.parseInt(str.substring(startIdx, i + 1));
                    product *= piece;
                    startIdx = i + 1;
                }
            }
            product *= Integer.parseInt(str.substring(startIdx));

            maxTurns = Math.max(maxTurns, 1 + play(product));
        }

        return memo[num] = maxTurns;
    }
}