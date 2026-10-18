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

            BitSet[] taller = new BitSet[N];

            for (int i = 0; i < N; i++) {
                taller[i] = new BitSet(N);
            }

            for (int i = 0; i < M; i++) {
                int a = fs.nextInt() - 1;
                int b = fs.nextInt() - 1;
                taller[a].set(b);
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    if (taller[i].get(k)) {
                        taller[i].or(taller[k]);
                    }
                }
            }

            int answer = 0;

            for (int i = 0; i < N; i++) {
                boolean known = true;

                for (int j = 0; j < N; j++) {
                    if (i == j) continue;

                    if (!taller[i].get(j) && !taller[j].get(i)) {
                        known = false;
                        break;
                    }
                }

                if (known) answer++;
            }

            sb.append('#').append(tc).append(' ')
              .append(answer).append('\n');
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