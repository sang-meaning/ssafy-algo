package cording_prac;

import java.io.*;
import java.util.*;

public class Solution_3282_하상호 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                int volume = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                for (int j = K; j >= volume; j--) {

                    dp[j] = Math.max(
                        dp[j],
                        dp[j - volume] + value
                    );
                }
            }

            sb.append("#").append(tc).append(" ")
              .append(dp[K]).append("\n");
        }

        System.out.print(sb);
    }
}
