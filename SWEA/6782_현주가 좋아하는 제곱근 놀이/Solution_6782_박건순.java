import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

    static long count;

    public static void main(String args[]) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            long N = Long.parseLong(br.readLine());

            count = 0;

            game(N);

            System.out.println("#" + test_case + " " + count);
        }
    }

    static void game(long n) {

        while (n != 2) {

            long sqrt = (long) Math.sqrt(n);

            // 완전제곱수
            if (sqrt * sqrt == n) {
                n = sqrt;
                count++;
            }

            // 완전제곱수가 아니라면
            else {
                long next = sqrt + 1;
                long nextSquare = next * next;

                count += nextSquare - n;
                n = nextSquare;
            }
        }
    }
}