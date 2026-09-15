import java.io.*;
import java.util.*;

public class Solution_14510_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[] trees = new int[N];

            st = new StringTokenizer(br.readLine());

            int maxH = 0;

            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, trees[i]);
            }

            int one = 0;
            int two = 0;

            for (int i = 0; i < N; i++) {
                int diff = maxH - trees[i];
                two += diff / 2;
                one += diff % 2;
            }

            while (two > one + 1) {
                two--;
                one += 2;
            }

            int ans = 0;

            if (one > two) {
                ans = one * 2 - 1;
            } else if (two > one) {
                ans = two * 2;
            } else {
                ans = one + two;
            }

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }

        System.out.println(sb);
    }
}