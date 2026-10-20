import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final long MOD = 1_234_567_891L;

    static long[] factorial;

    // base^exponent % MOD
    static long pow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exponent /= 2;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] nArr = new int[T];
        int[] rArr = new int[T];
        int maxN = 0;

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            nArr[tc] = Integer.parseInt(st.nextToken());
            rArr[tc] = Integer.parseInt(st.nextToken());

            maxN = Math.max(maxN, nArr[tc]);
        }

        // 0!부터 maxN!까지 미리 계산
        factorial = new long[maxN + 1];
        factorial[0] = 1;

        for (int i = 1; i <= maxN; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            int n = nArr[tc];
            int r = rArr[tc];

            long numerator = factorial[n];
            long denominator =
                    (factorial[r] * factorial[n - r]) % MOD;

            // denominator의 모듈러 역원: denominator^(MOD - 2)
            long inverse = pow(denominator, MOD - 2);

            long answer = (numerator * inverse) % MOD;

            sb.append("#")
              .append(tc + 1)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}