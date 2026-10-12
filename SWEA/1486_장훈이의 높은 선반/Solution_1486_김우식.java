package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486 {

    static int T, N, B, ans;
    static int[] height;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            height = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;

            subset(0, 0);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(ans)
              .append("\n");
        }

        System.out.print(sb);
    }

    static void subset(int idx, int sum) {

        if (sum >= B) {
            ans = Math.min(ans, sum - B);
            return;
        }

        if (idx == N) {
            return;
        }

        subset(idx + 1, sum + height[idx]);

        subset(idx + 1, sum);
    }
}