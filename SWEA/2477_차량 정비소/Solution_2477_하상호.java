package cording_prac;

import java.io.*;
import java.util.*;

public class Solution_2477_하상호 {

    static class FastScanner {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringTokenizer st;

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = fs.nextInt();
            int M = fs.nextInt();
            int K = fs.nextInt();
            int A = fs.nextInt();
            int B = fs.nextInt();

            int[] receptionTime = new int[N + 1];
            int[] repairTime = new int[M + 1];

            for (int i = 1; i <= N; i++) {
                receptionTime[i] = fs.nextInt();
            }

            for (int i = 1; i <= M; i++) {
                repairTime[i] = fs.nextInt();
            }

            int[][] arrival = new int[K][2];

            for (int i = 0; i < K; i++) {
                arrival[i][0] = fs.nextInt();
                arrival[i][1] = i + 1;
            }

            Arrays.sort(arrival, (x, y) -> {
                if (x[0] != y[0]) {
                    return Integer.compare(x[0], y[0]);
                }
                return Integer.compare(x[1], y[1]);
            });

            Queue<Integer> receptionWait = new ArrayDeque<>();
            Queue<Integer> repairWait = new ArrayDeque<>();

            int[] receptionCustomer = new int[N + 1];
            int[] receptionEnd = new int[N + 1];

            int[] repairCustomer = new int[M + 1];
            int[] repairEnd = new int[M + 1];

            boolean[] usedA = new boolean[K + 1];

            int next = 0;
            int completed = 0;
            int answer = 0;

            for (int time = 0; completed < K; time++) {

                for (int i = 1; i <= M; i++) {
                    if (repairCustomer[i] != 0
                            && repairEnd[i] == time) {
                        repairCustomer[i] = 0;
                        completed++;
                    }
                }

                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] != 0
                            && receptionEnd[i] == time) {
                        repairWait.offer(receptionCustomer[i]);
                        receptionCustomer[i] = 0;
                    }
                }

                while (next < K && arrival[next][0] <= time) {
                    receptionWait.offer(arrival[next][1]);
                    next++;
                }

                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] == 0
                            && !receptionWait.isEmpty()) {

                        int customer = receptionWait.poll();

                        receptionCustomer[i] = customer;
                        receptionEnd[i] = time + receptionTime[i];

                        if (i == A) {
                            usedA[customer] = true;
                        }
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (repairCustomer[i] == 0
                            && !repairWait.isEmpty()) {

                        int customer = repairWait.poll();

                        repairCustomer[i] = customer;
                        repairEnd[i] = time + repairTime[i];

                        if (i == B && usedA[customer]) {
                            answer += customer;
                        }
                    }
                }
            }

            if (answer == 0) answer = -1;

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
