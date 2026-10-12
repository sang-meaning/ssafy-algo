import java.io.*;
import java.util.*;

class Solution {
    static Map<Integer, Integer> memo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            memo = new HashMap<>();
            int answer = dfs(N);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static int dfs(int n) {
        if (n < 10) return 0;
        if (memo.containsKey(n)) return memo.get(n);
        String str = String.valueOf(n);
        int len = str.length();
        int maxTurn = 0;
        int gapCount = len - 1;
        for (int mask = 1; mask < (1 << gapCount); mask++) {
            int product = 1;
            int start = 0;
            for (int i = 0; i < gapCount; i++) {
                if ((mask & (1 << i)) != 0) {
                    int value = Integer.parseInt(str.substring(start, i + 1));
                    product *= value;
                    start = i + 1;
                }
            }
            int value = Integer.parseInt(str.substring(start));
            product *= value;
            maxTurn = Math.max( maxTurn,1 + dfs(product));
        }
        memo.put(n, maxTurn);
        return maxTurn;
    }
}