import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            long n = Long.parseLong(br.readLine());
            long cnt = 0;

            while (n != 2) {

                long sqrt = (long) Math.sqrt(n);

                // 완전제곱수인 경우
                if (sqrt * sqrt == n) {
                    n = sqrt;
                    cnt++;
                }

                // 완전제곱수가 아닌 경우
                else {
                    long next = (sqrt + 1) * (sqrt + 1);

                    cnt += next - n;
                    n = next;
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(cnt)
              .append("\n");
        }

        System.out.print(sb);
    }
}