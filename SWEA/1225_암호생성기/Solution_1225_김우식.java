package swea;

import java.util.*;
import java.io.*;

public class SWEA1225 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {

            int T = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            Deque<Integer> dq = new ArrayDeque<>();

            for (int i = 0; i < 8; i++) {
                dq.offerLast(Integer.parseInt(st.nextToken()));
            }

            int minus = 1;

            while (true) {

                int n = dq.pollFirst();

                n -= minus;

                if (n <= 0) {
                    n = 0;
                    dq.offerLast(n);
                    break;
                }

                dq.offerLast(n);

                minus++;

                if (minus == 6) {
                    minus = 1;
                }
            }

            sb.append("#").append(T).append(" ");

            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst()).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}