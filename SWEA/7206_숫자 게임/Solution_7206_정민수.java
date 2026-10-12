import java.io.*;
import java.util.*;

public class Solution {

    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Arrays.fill(memo, -1);

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int num = Integer.parseInt(br.readLine());

            System.out.println("#" + tc + " " + solve(num));
        }
    }

    static int solve(int num) {
        if (num < 10) {
            return 0;
        }

        if (memo[num] != -1) {
            return memo[num];
        }

        String str = String.valueOf(num);

        int max = 0;

        for (int i = 1; i < str.length(); i++) {
            int left = Integer.parseInt(str.substring(0, i));

            max = Math.max(max, split(str, i, left));
        }

        return memo[num] = max;
    }

    static int split(String str, int start, int product) {
        int max = 0;

        if (start == str.length()) {
            return 1 + solve(product);
        }

        for (int end = start + 1; end <= str.length(); end++) {
            int num = Integer.parseInt(str.substring(start, end));

            if (end == str.length()) {
                max = Math.max(max, 1 + solve(product * num));
            } else {
                max = Math.max(max, split(str, end, product * num));
            }
        }

        return max;
    }
}