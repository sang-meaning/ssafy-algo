import java.util.*;

public class Solution {
    // memo[n]: 숫자 n에서 진행할 수 있는 최대 턴 수
    static int[] memo = new int[100000];

    static int solve(int n) {
        if (n < 10) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        String s = String.valueOf(n);

        // 왼쪽부터 조각을 만드는 모든 방법 탐색
        memo[n] = split(s, 0, 1, 0);
        return memo[n];
    }

    static int split(String s, int start, int product, int count) {
        if (start == s.length()) {
            // 반드시 두 조각 이상으로 나눠야 함
            if (count < 2) {
                return 0;
            }

            // 이번 턴 1회 + 곱한 숫자에서의 최대 턴 수
            return 1 + solve(product);
        }

        int best = 0;
        int piece = 0;

        for (int end = start; end < s.length(); end++) {
            // s[start..end]를 하나의 숫자로 구성
            piece = piece * 10 + (s.charAt(end) - '0');

            best = Math.max(
                best,
                split(s, end + 1, product * piece, count + 1)
            );
        }

        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        Arrays.fill(memo, -1);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int n = sc.nextInt();

            sb.append('#').append(tc).append(' ')
              .append(solve(n)).append('\n');
        }

        System.out.print(sb);
        sc.close();
    }
}