
package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2477_김정원 {
    static int N, M, K, A, B, answer;
    static int[] receptionTime, repairTime, arrivalTime;
    static int[] receptionUsed, repairUsed;

    static class Counter {
        int customer;
        int endTime;

        Counter() {
            this.customer = 0;
            this.endTime = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            receptionTime = new int[N + 1];
            repairTime = new int[M + 1];
            arrivalTime = new int[K + 1];

            receptionUsed = new int[K + 1];
            repairUsed = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                receptionTime[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                repairTime[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                arrivalTime[i] = Integer.parseInt(st.nextToken());
            }

            answer = 0;
            simulation();

            for (int i = 1; i <= K; i++) {
                if (receptionUsed[i] == A && repairUsed[i] == B) {
                    answer += i;
                }
            }

            if (answer == 0) answer = -1;

            System.out.println(String.format("#%d %d", t, answer));
        }
    }

    static void simulation() {
        Queue<Integer> receptionQ = new ArrayDeque<>();
        Queue<Integer> repairQ = new ArrayDeque<>();

        Counter[] reception = new Counter[N + 1];
        Counter[] repair = new Counter[M + 1];

        for (int i = 1; i <= N; i++) {
            reception[i] = new Counter();
        }

        for (int i = 1; i <= M; i++) {
            repair[i] = new Counter();
        }

        int time = 0;
        int nextCustomer = 1;
        int finished = 0;

        while (finished < K) {

            // 1. 접수 창구 업무 완료
            // 창구 번호가 작은 순서로 정비 대기 큐에 추가
            for (int i = 1; i <= N; i++) {
                if (reception[i].customer == 0) continue;
                if (reception[i].endTime != time) continue;

                int customer = reception[i].customer;
                repairQ.offer(customer);

                reception[i].customer = 0;
            }

            // 2. 정비 창구 업무 완료
            for (int i = 1; i <= M; i++) {
                if (repair[i].customer == 0) continue;
                if (repair[i].endTime != time) continue;

                repair[i].customer = 0;
                finished++;
            }

            // 3. 현재 시간에 도착한 고객 추가
            while (nextCustomer <= K &&
                   arrivalTime[nextCustomer] <= time) {

                receptionQ.offer(nextCustomer);
                nextCustomer++;
            }

            // 4. 빈 접수 창구에 고객 배정
            for (int i = 1; i <= N; i++) {
                if (reception[i].customer != 0) continue;
                if (receptionQ.isEmpty()) break;

                int customer = receptionQ.poll();

                reception[i].customer = customer;
                reception[i].endTime = time + receptionTime[i];

                receptionUsed[customer] = i;
            }

            // 5. 빈 정비 창구에 고객 배정
            for (int i = 1; i <= M; i++) {
                if (repair[i].customer != 0) continue;
                if (repairQ.isEmpty()) break;

                int customer = repairQ.poll();

                repair[i].customer = customer;
                repair[i].endTime = time + repairTime[i];

                repairUsed[customer] = i;
            }

            time++;
        }
    }
}
