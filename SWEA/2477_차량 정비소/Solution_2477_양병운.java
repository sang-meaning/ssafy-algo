import java.util.*;
import java.io.*;

class Solution {
    static class Customer {
        int id;
        int visitedTime;
        int reception = -1;
        int repair = -1;

        Customer(int id, int visitedTime) {
            this.id = id;
            this.visitedTime = visitedTime;
        }
    }
    static int N, M, K, A, B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            int[] receptionTime = new int[N];
            int[] repairTime = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) receptionTime[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) repairTime[i] = Integer.parseInt(st.nextToken());

            Customer[] customers = new Customer[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                int visitedTime = Integer.parseInt(st.nextToken());
                customers[i] = new Customer(i + 1, visitedTime);
            }

            Queue<Customer> receptionQueue = new ArrayDeque<>();
            Queue<Customer> repairQueue = new ArrayDeque<>();
            Customer[] receptionDesk = new Customer[N];
            Customer[] repairDesk = new Customer[M];
            int[] receptionEnd = new int[N];
            int[] repairEnd = new int[M];
            int time = 0;
            int arrivalIndex = 0;
            int completed = 0;
            int answer = 0;

            while (completed < K) {
                for (int i = 0; i < N; i++) {
                    if (receptionDesk[i] != null  && receptionEnd[i] == time) {
                        repairQueue.offer(receptionDesk[i]);
                        receptionDesk[i] = null;
                    }
                }
                for (int i = 0; i < M; i++) {
                    if (repairDesk[i] != null && repairEnd[i] == time) {
                        Customer customer = repairDesk[i];
                        if (customer.reception == A && customer.repair == B) answer += customer.id;
                        repairDesk[i] = null;
                        completed++;
                    }
                }

                while (arrivalIndex < K && customers[arrivalIndex].visitedTime == time) {
                    receptionQueue.offer(customers[arrivalIndex]);
                    arrivalIndex++;
                }
                
                for (int i = 0; i < N; i++) {
                    if (receptionDesk[i] == null && !receptionQueue.isEmpty()) {
                        Customer customer = receptionQueue.poll();
                        customer.reception = i + 1;
                        receptionDesk[i] = customer;
                        receptionEnd[i] = time + receptionTime[i];
                    }
                }

                for (int i = 0; i < M; i++) {
                    if (repairDesk[i] == null && !repairQueue.isEmpty()) {
                        Customer customer = repairQueue.poll();
                        customer.repair = i + 1;
                        repairDesk[i] = customer;
                        repairEnd[i] = time + repairTime[i];
                    }
                }
                time++;
            }
            if (answer == 0) answer = -1;
            System.out.println("#" + test_case + " " + answer);
        }
    }
}