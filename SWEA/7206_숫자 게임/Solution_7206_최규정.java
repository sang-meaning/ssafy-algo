import java.util.*;

public class Solution {
    static int[] memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int number = sc.nextInt();

            memo = new int[number + 1];
            Arrays.fill(memo, -1);

            int answer = dfs(number);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static int dfs(int number) {
        if (number < 10) {
            return 0;
        }

        if (memo[number] != -1) {
            return memo[number];
        }

        String digits = String.valueOf(number);

        memo[number] = split(digits, 0, 1, 0);
        return memo[number];
    }


    static int split(String digits, int start, int product, int pieceCount) {

        if (start == digits.length()) {
            if (pieceCount < 2) {
                return -1;
            }
            return 1 + dfs(product);
        }

        int best = -1;
        int piece = 0;

        for (int end = start; end < digits.length(); end++) {

            piece = piece * 10 + (digits.charAt(end) - '0');

            int turns = split(digits, end + 1, product * piece, pieceCount + 1);
            
            best = Math.max(best, turns);
        }

        return best;
    }
}