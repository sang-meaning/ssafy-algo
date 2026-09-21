import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            long N = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (N > 2) {
                long sqrt = (long) Math.sqrt(N);

                // 완전 제곱수라면 바로 제곱근으로 만든다.
                if (sqrt * sqrt == N) {
                    N = sqrt;
                    count++;
                } else {
                    // 완전 제곱수가 아니면 다음 완전 제곱수까지 (+1) 연산을 수행한 후 제곱근 연산을 수행한다.
                    long nextSqrt = sqrt + 1;
                    long nextSquare = nextSqrt * nextSqrt;

                    count += (nextSquare - N) + 1;
                    N = nextSqrt;
                }
            }

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }

        System.out.print(sb.toString());
    }
}