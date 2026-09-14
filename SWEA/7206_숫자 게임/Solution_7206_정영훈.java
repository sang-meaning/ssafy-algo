
import java.io.*;
import java.util.*;

public class Solution_7206_정영훈 {

    static int[] memo = new int[100000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Arrays.fill(memo, -1);

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());

            sb.append("#").append(tc).append(" ")
              .append(dfs(n)).append("\n");
        }

        System.out.print(sb);
    }

    // n에서 진행할 수 있는 최대 턴 수
    static int dfs(int n) {
        if (n < 10) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = split(String.valueOf(n), 0, 1, 0);
        return memo[n];
    }

    // 숫자를 두 조각 이상으로 나누는 모든 경우 탐색
    static int split(String s, int index, int product, int count) {
        if (index == s.length()) {
            // 한 조각이면 분할하지 않은 것이므로 제외
            if (count < 2) {
                return 0;
            }

            return 1 + dfs(product);
        }

        int max = 0;
        int number = 0;

        for (int i = index; i < s.length(); i++) {
            number = number * 10 + (s.charAt(i) - '0');

            max = Math.max(max,
                    split(s, i + 1, product * number, count + 1));
        }

        return max;
    }
}