import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = sc.nextInt();
            
            Queue<Integer> que = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                que.offer(sc.nextInt());
            }

            int count = 1;

            while (true) {
                int num = que.poll() - count;

                if (num <= 0) {
                    que.offer(0);
                    break;
                }

                que.offer(num);

                count++;
                if (count > 5) {
                    count = 1;
                }
            }

            System.out.print("#" + n);
            while (!que.isEmpty()) {
                System.out.print(" " + que.poll());
            }
            System.out.println();
        }
    }
}