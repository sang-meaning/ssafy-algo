package al_prac;

import java.util.*;

public class Solution_1225_하상호 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {

            int test_case = sc.nextInt();

            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < 8; i++) {
                q.offer(sc.nextInt());
            }

            int minus = 1;

            while (true) {
                int num = q.poll();

                num -= minus;

                if (num <= 0) {
                    q.offer(0);
                    break;
                }

                q.offer(num);

                minus++;

                if (minus == 6) {
                    minus = 1;
                }
            }

            System.out.print("#" + test_case + " ");

            while (!q.isEmpty()) {
                System.out.print(q.poll() + " ");
            }

            System.out.println();
        }
    }
}