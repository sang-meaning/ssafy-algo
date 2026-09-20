import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            long N = Long.parseLong(br.readLine());
            long ans = 0;

            while(N > 2) {

                long root = (long)Math.sqrt(N);

                // 혹시 모를 sqrt 오차 보정
                while(root * root > N) root--;
                while((root+1) * (root+1) <= N) root++;

                if(root * root == N) {

                    N = root;
                    ans++;
                }
                else {

                    long next = (root+1) * (root+1);

                    // next까지 +1 반복한 것과 동일
                    ans += next - N;

                    // next -> sqrt(next)
                    ans++;

                    N = root + 1;
                }
            }

            sb.append("#").append(tc).append(" ")
              .append(ans).append("\n");
        }

        System.out.print(sb);
    }
}