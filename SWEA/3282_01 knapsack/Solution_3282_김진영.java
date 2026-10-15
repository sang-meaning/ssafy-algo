import java.io.BufferedInputStream;
import java.io.IOException;

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

    public static void main(String[] args) throws Exception {
        System.setIn(new BufferedInputStream(System.in));
        FastScanner fs = new FastScanner();
        StringBuilder answer = new StringBuilder();
        int testCases = fs.nextInt();

        for (int tc = 1; tc <= testCases; tc++) {
            int itemCount = fs.nextInt();
            int capacity = fs.nextInt();
            int[] dp = new int[capacity + 1];

            for (int i = 0; i < itemCount; i++) {
                int volume = fs.nextInt();
                int value = fs.nextInt();

                // 같은 물건을 한 번만 고르기 위해 용량을 큰 쪽부터 갱신한다.
                for (int c = capacity; c >= volume; c--) {
                    dp[c] = Math.max(dp[c], dp[c - volume] + value);
                }
            }

            answer.append('#').append(tc).append(' ').append(dp[capacity]).append('\n');
        }
        System.out.print(answer);
    }
}
