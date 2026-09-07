
import java.io.*;
import java.util.*;

public class Solution_3499_정영훈 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            String[] cards = new String[N];

            for (int i = 0; i < N; i++) {
                cards[i] = st.nextToken();
            }

            int mid = (N + 1) / 2;
            sb.append("#").append(tc);

            for (int i = 0; i < mid; i++) {
                sb.append(" ").append(cards[i]);
                if (mid + i < N) {
                    sb.append(" ").append(cards[mid + i]);
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}