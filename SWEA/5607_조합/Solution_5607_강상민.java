import java.io.*;
import java.util.*;

public class Solution_5607_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, N, R;
    static long D = 1234567891;
    static long[] fact;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        // 팩토리얼 미리 구해두기 (N <= 100만)
        fact = new long[1000001];
        fact[0] = 1;
        for (int i=1; i<=1000000; i++) {
            fact[i] = fact[i-1] * i % D;
        }

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            // N C R = N! / (R! * (N-R)!)
            // mod에서는 나누기가 안 되니까 분모^(D-2) 를 곱하기
            long bottom = fact[R] * fact[N-R] % D;
            long result = fact[N] * pow(bottom, D-2) % D;

            sb.append("#"+t+" "+result).append("\n");
        }

        System.out.println(sb);
    }

    // 분할정복 거듭제곱 (a^b % D)
    static long pow(long a, long b) {
        long res = 1;
        a %= D;
        while (b > 0) {
            if (b % 2 == 1) res = res * a % D;
            a = a * a % D;
            b /= 2;
        }
        return res;
    }
}