import java.io.*;
import java.util.*;

public class Solution_3260_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String A = st.nextToken();
            String B = st.nextToken();

            int i = A.length() - 1;
            int j = B.length() - 1;
            int carry = 0;

            StringBuilder result = new StringBuilder();

            while (i >= 0 || j >= 0 || carry > 0) {
                int sum = carry;

                if (i >= 0) sum += A.charAt(i--) - '0';
                if (j >= 0) sum += B.charAt(j--) - '0';

                result.append(sum % 10);
                carry = sum / 10;
            }

            sb.append("#").append(tc).append(" ")
              .append(result.reverse()).append("\n");
        }

        System.out.print(sb);
    }
}