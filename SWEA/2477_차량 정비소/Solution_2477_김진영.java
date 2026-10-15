import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;

public class Solution {
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr;
        private int len;

        private int read() throws IOException {
            if (ptr >= len) {
                len = System.in.read(buffer);
                ptr = 0;
                if (len < 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do c = read(); while (c <= ' ');
            int value = 0;
            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }
            return value;
        }
    }

    static class Customer {
        final int number;
        int receptionDesk;
        int repairDesk;

        Customer(int number) {
            this.number = number;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new BufferedInputStream(System.in));
        FastScanner fs = new FastScanner();
        StringBuilder answer = new StringBuilder();
        int testCases = fs.nextInt();

        for (int tc = 1; tc <= testCases; tc++) {
            int receptionCount = fs.nextInt();
            int repairCount = fs.nextInt();
            int customerCount = fs.nextInt();
            int targetReception = fs.nextInt() - 1;
            int targetRepair = fs.nextInt() - 1;

            int[] receptionTime = new int[receptionCount];
            int[] repairTime = new int[repairCount];
            for (int i = 0; i < receptionCount; i++) receptionTime[i] = fs.nextInt();
            for (int i = 0; i < repairCount; i++) repairTime[i] = fs.nextInt();

            int[] arrivalTime = new int[customerCount];
            for (int i = 0; i < customerCount; i++) arrivalTime[i] = fs.nextInt();

            Customer[] customers = new Customer[customerCount];
            for (int i = 0; i < customerCount; i++) customers[i] = new Customer(i + 1);

            Customer[] receptionUsing = new Customer[receptionCount];
            Customer[] repairUsing = new Customer[repairCount];
            int[] receptionEnd = new int[receptionCount];
            int[] repairEnd = new int[repairCount];
            ArrayDeque<Customer> receptionWaiting = new ArrayDeque<>();
            ArrayDeque<Customer> repairWaiting = new ArrayDeque<>();

            int nextArrival = 0;
            int completed = 0;
            int time = 0;

            while (completed < customerCount) {
                // 정비 종료, 접수 종료, 정비 배정 순서로 처리하면 같은 시각의 우선순위를 지킨다.
                for (int i = 0; i < repairCount; i++) {
                    if (repairUsing[i] != null && repairEnd[i] == time) {
                        repairUsing[i] = null;
                        completed++;
                    }
                }

                // 접수 창구 번호가 작은 순서로 넣어야 동시 종료 고객의 정비 대기 순서가 맞는다.
                for (int i = 0; i < receptionCount; i++) {
                    if (receptionUsing[i] != null && receptionEnd[i] == time) {
                        repairWaiting.offer(receptionUsing[i]);
                        receptionUsing[i] = null;
                    }
                }

                for (int i = 0; i < repairCount; i++) {
                    if (repairUsing[i] == null && !repairWaiting.isEmpty()) {
                        Customer customer = repairWaiting.poll();
                        customer.repairDesk = i;
                        repairUsing[i] = customer;
                        repairEnd[i] = time + repairTime[i];
                    }
                }

                while (nextArrival < customerCount && arrivalTime[nextArrival] == time) {
                    receptionWaiting.offer(customers[nextArrival++]);
                }

                for (int i = 0; i < receptionCount; i++) {
                    if (receptionUsing[i] == null && !receptionWaiting.isEmpty()) {
                        Customer customer = receptionWaiting.poll();
                        customer.receptionDesk = i;
                        receptionUsing[i] = customer;
                        receptionEnd[i] = time + receptionTime[i];
                    }
                }

                time++;
            }

            int sum = 0;
            for (Customer customer : customers) {
                if (customer.receptionDesk == targetReception && customer.repairDesk == targetRepair) {
                    sum += customer.number;
                }
            }
            answer.append('#').append(tc).append(' ').append(sum == 0 ? -1 : sum).append('\n');
        }
        System.out.print(answer);
    }
}
