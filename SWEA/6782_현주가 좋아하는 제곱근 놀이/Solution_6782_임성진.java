import java.io.*;
import java.util.*;

public class Solution_6782_임성진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            long n = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (n != 2) {
                if (n == 1) { count++; break; }       // 1 -> 2
                long r = isqrt(n);
                if (r * r == n) {                     // 제곱수 -> 제곱근
                    n = r;
                    count++;
                } else {                              // 다음 제곱수까지 점프
                    long next = (r + 1) * (r + 1);
                    count += next - n;
                    n = next;
                }
            }
            sb.append('#').append(tc).append(' ').append(count).append('\n');
        }
        System.out.print(sb);
    }

    /** floor(sqrt(n)) 을 부동소수점 오차 없이 */
    static long isqrt(long n) {
        long r = (long) Math.sqrt((double) n);
        while (r > 0 && r * r > n) r--;
        while ((r + 1) * (r + 1) <= n) r++;
        return r;
    }
}