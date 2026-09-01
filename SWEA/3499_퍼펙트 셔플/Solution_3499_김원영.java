import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_queue {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();

            String[] cards = new String[N];

            for (int i = 0; i < N; i++) {
                cards[i] = sc.next();
            }

            Queue<String> firstqueue = new LinkedList<>();
            Queue<String> secondqueue = new LinkedList<>();

            int half = (N + 1) / 2;

            for (int i = 0; i < half; i++) {
                firstqueue.offer(cards[i]);
            }

            for (int i = half; i < N; i++) {
                secondqueue.offer(cards[i]);
            }

            ArrayList<String> result = new ArrayList<>();

            while (!firstqueue.isEmpty() || !secondqueue.isEmpty()) {

                if (!firstqueue.isEmpty()) {
                    result.add(firstqueue.poll());
                }

                if (!secondqueue.isEmpty()) {
                    result.add(secondqueue.poll());
                }
            }

            System.out.print("#" + tc);

            for (int i = 0; i < result.size(); i++) {
                System.out.print(" " + result.get(i));
            }

            System.out.println();
        }

        sc.close();
    }
}