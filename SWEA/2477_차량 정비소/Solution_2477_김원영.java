
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();

            int[] a = new int[N + 1];
            int[] b = new int[M + 1];
            int[] arrival = new int[K + 1];

            for (int i = 1; i <= N; i++) {
                a[i] = sc.nextInt();
            }

            for (int i = 1; i <= M; i++) {
                b[i] = sc.nextInt();
            }

            for (int i = 1; i <= K; i++) {
                arrival[i] = sc.nextInt();
            }

            int[] reception = new int[N + 1];
            int[] repair = new int[M + 1];
            int[] receptionEnd = new int[N + 1];
            int[] repairEnd = new int[M + 1];
            int[] usedReception = new int[K + 1];
            int[] usedRepair = new int[K + 1];

            Queue<Integer> receptionQueue = new ArrayDeque<>();
            Queue<Integer> repairQueue = new ArrayDeque<>();

            int time = 0;
            int next = 1;
            int completed = 0;

            while (completed < K) {

                for (int i = 1; i <= M; i++) {
                    if (repair[i] != 0 && repairEnd[i] == time) {
                        repair[i] = 0;
                        completed++;
                    }
                }

                for (int i = 1; i <= N; i++) {
                    if (reception[i] != 0 && receptionEnd[i] == time) {
                        repairQueue.add(reception[i]);
                        reception[i] = 0;
                    }
                }

                while (next <= K && arrival[next] <= time) {
                    receptionQueue.add(next);
                    next++;
                }

                for (int i = 1; i <= N; i++) {
                    if (reception[i] == 0 && !receptionQueue.isEmpty()) {
                        int customer = receptionQueue.poll();

                        reception[i] = customer;
                        receptionEnd[i] = time + a[i];
                        usedReception[customer] = i;
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (repair[i] == 0 && !repairQueue.isEmpty()) {
                        int customer = repairQueue.poll();

                        repair[i] = customer;
                        repairEnd[i] = time + b[i];
                        usedRepair[customer] = i;
                    }
                }

                time++;
            }

            int sum = 0;

            for (int i = 1; i <= K; i++) {
                if (usedReception[i] == A && usedRepair[i] == B) {
                    sum += i;
                }
            }

            if (sum == 0) {
                sum = -1;
            }

            System.out.println("#" + tc + " " + sum);
        }

        sc.close();
    }
}
