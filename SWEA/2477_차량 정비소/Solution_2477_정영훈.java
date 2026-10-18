import java.io.*;
import java.util.*;

class Solution {
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
            int[] arrival = new int[K + 1];

            for (int i = 1; i <= N; i++) {
                receptionTime[i] = fs.nextInt();
            }
            for (int i = 1; i <= M; i++) {
                repairTime[i] = fs.nextInt();
            }
            for (int i = 1; i <= K; i++) {
                arrival[i] = fs.nextInt();
            }

            int[] receptionCustomer = new int[N + 1];
            int[] repairCustomer = new int[M + 1];
            int[] receptionEnd = new int[N + 1];
            int[] repairEnd = new int[M + 1];
            int[] usedReception = new int[K + 1];

            PriorityQueue<Integer> receptionWait = new PriorityQueue<>();
            Queue<Integer> repairWait = new ArrayDeque<>();

            int nextCustomer = 1;
            int completed = 0;
            int answer = 0;
            int time = 0;

            while (completed < K) {
                while (nextCustomer <= K
                        && arrival[nextCustomer] <= time) {
                    receptionWait.offer(nextCustomer++);
                }

                for (int i = 1; i <= M; i++) {
                    if (repairCustomer[i] != 0
                            && repairEnd[i] <= time) {
                        repairCustomer[i] = 0;
                        completed++;
                    }
                }

                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] != 0
                            && receptionEnd[i] <= time) {
                        repairWait.offer(receptionCustomer[i]);
                        receptionCustomer[i] = 0;
                    }
                }

                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] == 0
                            && !receptionWait.isEmpty()) {
                        int customer = receptionWait.poll();

                        receptionCustomer[i] = customer;
                        receptionEnd[i] = time + receptionTime[i];
                        usedReception[customer] = i;
                    }
                }

                for (int i = 1; i <= M; i++) {
                    if (repairCustomer[i] == 0
                            && !repairWait.isEmpty()) {
                        int customer = repairWait.poll();

                        repairCustomer[i] = customer;
                        repairEnd[i] = time + repairTime[i];

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
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int value = 0;
            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }
            return value;
        }
    }
}