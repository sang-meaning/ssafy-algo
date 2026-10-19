import java.io.*;
import java.util.*;

public class Solution_3282_김현호 {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder answer = new StringBuilder();
        int T = fs.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int n = fs.nextInt(), capacity = fs.nextInt();
            int[] dp = new int[capacity + 1];
            for (int i = 0; i < n; i++) {
                int volume = fs.nextInt(), value = fs.nextInt();
                // 역순 갱신: 같은 물건이 여러 번 선택되는 것을 방지한다.
                for (int c = capacity; c >= volume; c--) {
                    dp[c] = Math.max(dp[c], dp[c - volume] + value);
                }
            }
            answer.append('#').append(tc).append(' ').append(dp[capacity]).append('\n');
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
