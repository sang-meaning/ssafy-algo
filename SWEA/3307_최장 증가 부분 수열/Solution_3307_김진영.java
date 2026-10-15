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
            int n = fs.nextInt();
            int[] tails = new int[n];
            int length = 0;

            for (int i = 0; i < n; i++) {
                int number = fs.nextInt();
                int left = 0;
                int right = length;

                // number 이상인 첫 위치를 찾아 같은 길이의 꼬리를 더 작게 유지한다.
                while (left < right) {
                    int mid = (left + right) >>> 1;
                    if (tails[mid] < number) left = mid + 1;
                    else right = mid;
                }
                tails[left] = number;
                if (left == length) length++;
            }

            answer.append('#').append(tc).append(' ').append(length).append('\n');
        }
        System.out.print(answer);
    }
}
