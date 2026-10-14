import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt(); // 접수 창구 수
            int M = sc.nextInt(); // 정비 창구 수
            int K = sc.nextInt(); // 고객 수
            int A = sc.nextInt(); // 찾을 접수 창구
            int B = sc.nextInt(); // 찾을 정비 창구

            int[] receptionTime = new int[N];

            for (int i = 0; i < N; i++) {
                receptionTime[i] = sc.nextInt();
            }

            int[] repairTime = new int[M];

            for (int i = 0; i < M; i++) {
                repairTime[i] = sc.nextInt();
            }

            int[] arrival = new int[K + 1];

            for (int i = 1; i <= K; i++) {
                arrival[i] = sc.nextInt();
            }

            int[] usedReception = new int[K + 1];
            int[] usedRepair = new int[K + 1];

            int[] receptionCustomer = new int[N];
            int[] receptionFinish = new int[N];

            int[] repairCustomer = new int[M];
            int[] repairFinish = new int[M];

            Queue<Integer> receptionWait = new ArrayDeque<>();
            Queue<Integer> repairWait = new ArrayDeque<>();

            int time = 0;
            int nextCustomer = 1;
            int repairCount = 0;

            while (repairCount < K) {
                for (int i = 0; i < M; i++) {

                    if (repairCustomer[i] != 0 && repairFinish[i] == time) {

                        repairCustomer[i] = 0;
                    }
                }

                for (int i = 0; i < N; i++) {

                    if (receptionCustomer[i] != 0 && receptionFinish[i] == time) {

                        int customer = receptionCustomer[i];

                        repairWait.offer(customer);

                        receptionCustomer[i] = 0;
                    }
                }

                for (int i = 0; i < M; i++) {

                    if (repairCustomer[i] == 0 && !repairWait.isEmpty()) {

                        int customer = repairWait.poll();

                        repairCustomer[i] = customer;

                        usedRepair[customer] = i + 1;

                        repairFinish[i] = time + repairTime[i];

                        repairCount++;
                    }
                }


                while (nextCustomer <= K && arrival[nextCustomer] <= time) {

                    receptionWait.offer(nextCustomer);

                    nextCustomer++;
                }

                for (int i = 0; i < N; i++) {

                    if (receptionCustomer[i] == 0  && !receptionWait.isEmpty()) {

                        int customer = receptionWait.poll();

                        receptionCustomer[i] = customer;

                        usedReception[customer] = i + 1;

                        receptionFinish[i] = time + receptionTime[i];
                    }
                }
                time++;
            }

            
            int answer = 0;

            for (int i = 1; i <= K; i++) {
                if (usedReception[i] == A && usedRepair[i] == B) {
                    answer += i;
                }
            }

            if (answer == 0) {
                answer = -1;
            }
            System.out.println("#" + tc + " " + answer);
        }
        sc.close();
    }
}