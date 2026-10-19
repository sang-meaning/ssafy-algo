import java.io.*;
import java.util.*;

public class Solution_5643_이름 {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder answer = new StringBuilder();
        int T = fs.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int n = fs.nextInt(), m = fs.nextInt();
            BitSet[] taller = new BitSet[n];
            for (int i = 0; i < n; i++) taller[i] = new BitSet(n);
            for (int i = 0; i < m; i++) {
                int shorter = fs.nextInt() - 1, higher = fs.nextInt() - 1;
                taller[shorter].set(higher);
            }
            // 플로이드 워셜: i -> mid -> j이면 i -> j도 성립한다.
            for (int mid = 0; mid < n; mid++) {
                for (int i = 0; i < n; i++) {
                    if (taller[i].get(mid)) taller[i].or(taller[mid]);
                }
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                int known = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j && (taller[i].get(j) || taller[j].get(i))) known++;
                }
                if (known == n - 1) count++;
            }
            answer.append('#').append(tc).append(' ').append(count).append('\n');
        }
        System.out.print(answer);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr, len;
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer); ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, value = 0, sign = 1;
            do { c = read(); } while (c <= 32 && c != -1);
            if (c == -1) throw new EOFException();
            if (c == '-') { sign = -1; c = read(); }
            while (c > 32 && c != -1) {
                value = value * 10 + c - '0'; c = read();
            }
            return value * sign;
        }
    }
}
