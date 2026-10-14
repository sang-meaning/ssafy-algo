import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] a = new int[N + 1];
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            int[] b = new int[M + 1];
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            int[] arrival = new int[K + 1];
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= K; i++) {
                arrival[i] = Integer.parseInt(st.nextToken());
            }

            Queue<Integer> receptionQ = new ArrayDeque<>();
            Queue<Integer> repairQ = new ArrayDeque<>();

            int[] reception = new int[N + 1];
            int[] repair = new int[M + 1];

            int[] receptionEnd = new int[N + 1];
            int[] repairEnd = new int[M + 1];

            int[] usedReception = new int[K + 1];
            int[] usedRepair = new int[K + 1];

            int time = 0;
            int finished = 0;
            int nextCustomer = 1;

            while (finished < K) {

                for (int i = 1; i <= N; i++) {
                    if (reception[i] != 0
                            && receptionEnd[i] == time) {

                        repairQ.offer(reception[i]);
                        reception[i] = 0;
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (repair[i] != 0
                            && repairEnd[i] == time) {

                        repair[i] = 0;
                        finished++;
                    }
                }

                while (nextCustomer <= K
                        && arrival[nextCustomer] <= time) {

                    receptionQ.offer(nextCustomer);
                    nextCustomer++;
                }

                for (int i = 1; i <= N; i++) {
                    if (reception[i] == 0
                            && !receptionQ.isEmpty()) {

                        int customer = receptionQ.poll();

                        reception[i] = customer;
                        receptionEnd[i] = time + a[i];

                        usedReception[customer] = i;
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (repair[i] == 0
                            && !repairQ.isEmpty()) {

                        int customer = repairQ.poll();

                        repair[i] = customer;
                        repairEnd[i] = time + b[i];

                        usedRepair[customer] = i;
                    }
                }

                time++;
            }

            int ans = 0;

            for (int i = 1; i <= K; i++) {
                if (usedReception[i] == A
                        && usedRepair[i] == B) {
                    ans += i;
                }
            }

            if (ans == 0) {
                ans = -1;
            }

            sb.append("#").append(tc)
              .append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
}