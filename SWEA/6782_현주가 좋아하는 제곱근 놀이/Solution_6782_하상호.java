package swea;

import java.io.*;
import java.util.*;

public class Solution_6782_하상호 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());

            long answer = 0;

            while (N > 2) {

                long sqrt = (long) Math.sqrt(N);

                // 이미 완전제곱수인 경우
                if (sqrt * sqrt == N) {
                    N = sqrt;
                    answer++;
                }
                // 완전제곱수가 아니라면
                else {
                    long next = sqrt + 1;
                    long nextSquare = next * next;

                    // 다음 완전제곱수까지 +1 연산
                    answer += nextSquare - N;

                    // sqrt 연산
                    N = next;
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}