package cording_prac;

import java.io.*;
import java.util.*;

public class Solution_3307_하상호 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            int[] tails = new int[N];
            int size = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());

                int left = 0;
                int right = size;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (tails[mid] < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                tails[left] = num;

                if (left == size) {
                    size++;
                }
            }

            sb.append("#").append(tc).append(" ")
              .append(size).append("\n");
        }

        System.out.print(sb);
    }
}
