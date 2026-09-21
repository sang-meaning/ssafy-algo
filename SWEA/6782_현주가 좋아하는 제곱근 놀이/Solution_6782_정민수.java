import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            long N = sc.nextLong();
            long count = 0;

            while (N > 2) {
                long root = (long) Math.sqrt(N);

                if (root * root == N) {
                    N = root;
                    count++;
                } else {
                    long next = (root + 1) * (root + 1);

                    count += next - N;
                    N = next;
                }
            }

            System.out.println("#" + tc + " " + count);
        }
    }
}