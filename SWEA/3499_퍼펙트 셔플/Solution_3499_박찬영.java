import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());

            String[] cards = new String[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                cards[i] = st.nextToken();
            }

            sb.append("#").append(tc);

            int mid = (n + 1) / 2;
            int left = 0;
            int right = mid;

            while (left < mid || right < n) {
                if (left < mid) {
                    sb.append(" ").append(cards[left++]);
                }
                if (right < n) {
                    sb.append(" ").append(cards[right++]);
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}