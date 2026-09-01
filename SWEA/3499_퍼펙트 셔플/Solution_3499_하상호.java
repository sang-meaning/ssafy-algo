package al_prac.swea;

import java.io.*;
import java.util.*;

public class Solution_3499_하상호 {

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(
                    new InputStreamReader(System.in)
            );
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder output = new StringBuilder();

        int T = fs.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = fs.nextInt();

            String[] cards = new String[N];

            for (int i = 0; i < N; i++) {
                cards[i] = fs.next();
            }

            /*
             * 카드가 홀수 개이면 앞쪽 묶음이 한 장 더 많다.
             *
             * N = 6 -> split = 3
             * 앞: 0, 1, 2
             * 뒤: 3, 4, 5
             *
             * N = 5 -> split = 3
             * 앞: 0, 1, 2
             * 뒤: 3, 4
             */
            int split = (N + 1) / 2;

            output.append("#").append(testCase);

            for (int i = 0; i < split; i++) {
                // 앞쪽 묶음의 카드
                output.append(" ").append(cards[i]);

                // 뒤쪽 묶음에 카드가 남아 있는 경우
                if (i + split < N) {
                    output.append(" ").append(cards[i + split]);
                }
            }

            output.append("\n");
        }

        System.out.print(output);
    }
}