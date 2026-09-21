import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            long N = sc.nextLong();
            long answer = 0;

            while (N > 2) {

                // N보다 크거나 같은 가장 작은 제곱수의 제곱근
                long root = (long) Math.ceil(Math.sqrt(N));

                long square = root * root;

                // N → square까지 +1
                answer += square - N;

                // square → sqrt(square)
                answer++;

                N = root;
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}