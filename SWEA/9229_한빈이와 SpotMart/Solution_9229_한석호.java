package samsung01;

import java.util.*;
import java.util.Arrays;

public class Solution_9229_ÇÑ¼®È£ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] snack = new int[N];
            for (int i = 0; i < N; i++) {
                snack[i] = sc.nextInt();
            }

            Arrays.sort(snack);
            // System.out.println(N + " " + M + " " + Arrays.toString(snack));

            int left = 0;
            int right = N - 1;
            int res = 0;
            int middle = 0;

            if (snack[0] + snack[1] > M) {
                System.out.println("#" + t + " -1");
                continue;
            }

            while (left < right) {
                middle = snack[left] + snack[right];

                if (middle < M) {
                    left += 1;

                    if (res < middle) {
                        res = middle;
                    }
                } else if (middle > M) {
                    right -= 1;
                } else if (middle == M) {
                    res = middle;
                    break;
                }
            }

            System.out.println("#" + t + " " + res);
        }

        sc.close();
    }
}