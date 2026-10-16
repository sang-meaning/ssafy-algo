import java.io.*;
import java.util.*;

public class Solution {

    static class Customer {
        int id;
        int arrivalTime;

        Customer(int id, int arrivalTime) {
            this.id = id;
            this.arrivalTime = arrivalTime;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = fs.nextInt(); // 접수 창구 수
            int M = fs.nextInt(); // 정비 창구 수
            int K = fs.nextInt(); // 고객 수
            int A = fs.nextInt(); // 찾는 접수 창구
            int B = fs.nextInt(); // 찾는 정비 창구

            int[] receptionTime = new int[N + 1];
            int[] repairTime = new int[M + 1];

            for (int i = 1; i <= N; i++) {
                receptionTime[i] = fs.nextInt();
            }

            for (int i = 1; i <= M; i++) {
                repairTime[i] = fs.nextInt();
            }

            Customer[] customers = new Customer[K];

            for (int i = 0; i < K; i++) {
                customers[i] = new Customer(i + 1, fs.nextInt());
            }

            // 도착 시각순, 같은 시각이면 고객 번호순
            Arrays.sort(customers, (c1, c2) -> {
                if (c1.arrivalTime != c2.arrivalTime) {
                    return Integer.compare(c1.arrivalTime, c2.arrivalTime);
                }
                return Integer.compare(c1.id, c2.id);
            });

            // 접수 대기: 고객 번호가 작은 고객부터
            PriorityQueue<Integer> receptionWait = new PriorityQueue<>();

            // 정비 대기: 접수가 끝난 순서대로
            Queue<Integer> repairWait = new ArrayDeque<>();

            // 창구의 고객 번호가 0이면 빈 창구
            int[] receptionCustomer = new int[N + 1];
            int[] receptionEndTime = new int[N + 1];

            int[] repairCustomer = new int[M + 1];
            int[] repairEndTime = new int[M + 1];

            // 고객별로 이용한 접수 창구 기록
            int[] usedReception = new int[K + 1];

            int time = 0;
            int nextCustomer = 0;
            int completed = 0;
            int answer = 0;

            while (completed < K) {

                // 1. 정비가 끝난 고객 퇴장
                for (int i = 1; i <= M; i++) {
                    if (repairCustomer[i] != 0
                            && repairEndTime[i] == time) {
                        repairCustomer[i] = 0;
                        completed++;
                    }
                }

                // 2. 접수 완료 고객을 정비 대기 큐에 추가
                // 동시에 끝나면 접수 창구 번호가 작은 고객부터
                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] != 0
                            && receptionEndTime[i] == time) {
                        repairWait.offer(receptionCustomer[i]);
                        receptionCustomer[i] = 0;
                    }
                }

                // 3. 도착한 고객을 접수 대기에 추가
                while (nextCustomer < K
                        && customers[nextCustomer].arrivalTime <= time) {
                    receptionWait.offer(customers[nextCustomer].id);
                    nextCustomer++;
                }

                // 4. 빈 접수 창구에 배정
                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] == 0
                            && !receptionWait.isEmpty()) {
                        int customer = receptionWait.poll();

                        receptionCustomer[i] = customer;
                        receptionEndTime[i] = time + receptionTime[i];
                        usedReception[customer] = i;
                    }
                }

                // 5. 빈 정비 창구에 배정
                for (int i = 1; i <= M; i++) {
                    if (repairCustomer[i] == 0
                            && !repairWait.isEmpty()) {
                        int customer = repairWait.poll();

                        repairCustomer[i] = customer;
                        repairEndTime[i] = time + repairTime[i];

                        if (usedReception[customer] == A && i == B) {
                            answer += customer;
                        }
                    }
                }

                time++;
            }

            sb.append('#').append(tc).append(' ')
              .append(answer == 0 ? -1 : answer).append('\n');
        }

        System.out.print(sb);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int pointer = 0;
        private int length = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (pointer >= length) {
                length = in.read(buffer);
                pointer = 0;

                if (length == -1) {
                    return -1;
                }
            }

            return buffer[pointer++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }
    }
}