package swea;

import java.io.*;
import java.util.*;

public class Solution_3421_하상호 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] bad = new int[M][2];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                bad[i][0] = Integer.parseInt(st.nextToken()) - 1;
                bad[i][1] = Integer.parseInt(st.nextToken()) - 1;
            }

            int answer = 0;

            for (int mask = 0; mask < (1 << N); mask++) {

                boolean possible = true;

                for (int i = 0; i < M; i++) {

                    int a = bad[i][0];
                    int b = bad[i][1];

                    if ((mask & (1 << a)) != 0 &&
                        (mask & (1 << b)) != 0) {

                        possible = false;
                        break;
                    }
                }

                if (possible) {
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}