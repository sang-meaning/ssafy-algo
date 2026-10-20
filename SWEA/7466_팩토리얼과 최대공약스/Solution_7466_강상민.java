import java.io.*;
import java.util.*;

public class Solution_7466_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static long N,K;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Long.parseLong(st.nextToken());
            K = Long.parseLong(st.nextToken());

            // N, K <= 10억

            // K 소인수 분해 하면 소수들로 표현 가능 : p라 할때
            // p가 N!에 몇개 들어있는지 센다
            // 각 소수마다 min (K쪽 개수, N! 쪽 개수) 만큼 p를 곱한다

            long result = 1;
            long k = K;

            // K 소인수 분해
            for (long p=2; p*p <= k; p++) {
                if (k%p != 0) continue;


                // K 안에 p가 몇개 있는지
                long cntK = 0;
                while(k%p == 0) {
                    k /= p;
                    cntK++;
                }

                // N! 안에 p 몇개 있는지
                long e = Math.min(cntK, count(N,p));
                for (int i=0; i<e; i++)
                    result *= p;


            }

            // 남은 k가 1보다 크면 그 자체로 소수
            if (k>1 && k<=N) result *= k;
            sb.append("#"+t+" "+result).append("\n");
        }
        System.out.println(sb);
    }

    static long count(long n, long p) {
        long cnt = 0;
        for (long q=p; q<=n; q*=p) {
            cnt += n/q;
        }

        return cnt;
    }
}
