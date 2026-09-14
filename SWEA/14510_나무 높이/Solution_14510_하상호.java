package swea;

import java.io.*;
import java.util.*;

public class Solution_14510_하상호 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            int[] tree = new int[N];

            st = new StringTokenizer(br.readLine());

            int maxHeight = 0;

            for (int i = 0; i < N; i++) {

                tree[i] = Integer.parseInt(st.nextToken());

                maxHeight = Math.max(maxHeight, tree[i]);
            }

            int one = 0; // +1이 필요한 횟수
            int two = 0; // +2가 필요한 횟수

            for (int i = 0; i < N; i++) {

                int diff = maxHeight - tree[i];

                two += diff / 2;
                one += diff % 2;
            }

            /*
             * +2 한 번을
             * +1 두 번으로 변경 가능
             *
             * two가 너무 많으면
             * one으로 변환하여 균형을 맞춘다.
             */
            while (two > one + 1) {

                two--;

                one += 2;
            }

            int answer;

            /*
             * +1 횟수가 더 많다면
             *
             * 홀수날:
             * 1 3 5 7 ...
             *
             * 마지막 짝수날은 필요 없으므로
             * one * 2 - 1
             */
            if (one > two) {

                answer = one * 2 - 1;
            }

            /*
             * two == one
             * 또는
             * two == one + 1
             */
            else {

                answer = two * 2;
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}
