import java.io.*;
import java.util.*;

public class Solution_7206_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[] memo = new int[100000];

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(tc).append(" ").append(dfs(n)).append("\n");
        }
        System.out.print(sb);
    }

    static int dfs(int num) {
        if (num < 10) {
            return 0;
        }
        if (memo[num] != -1) {
            return memo[num];
        }

        String s = String.valueOf(num);
        int len = s.length();
        int maxTurn = 0;

        int maxMask = 1 << (len - 1);
        for (int mask = 1; mask < maxMask; mask++) {
            List<Integer> parts = new ArrayList<>();
            int prev = 0;

            for (int i = 0; i < len - 1; i++) {
                if ((mask & (1 << i)) != 0) {
                    parts.add(Integer.parseInt(s.substring(prev, i + 1)));
                    prev = i + 1;
                }
            }
            parts.add(Integer.parseInt(s.substring(prev)));

            int nextNum = 1;
            for (int part : parts) {
                nextNum *= part;
            }

            maxTurn = Math.max(maxTurn, 1 + dfs(nextNum));
        }

        return memo[num] = maxTurn;
    }
}