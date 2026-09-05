import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {

            int N = Integer.parseInt(br.readLine());
            int ans = 1;

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                int node = Integer.parseInt(st.nextToken());
                String value = st.nextToken();

                boolean isLeaf = node * 2 > N;

                boolean isOperator =
                        value.equals("+") ||
                                value.equals("-") ||
                                value.equals("*") ||
                                value.equals("/");

                if (isLeaf) {
                    if (isOperator) {
                        ans = 0;
                    }

                } else {
                    if (!isOperator) {
                        ans = 0;
                    }

                    if (node * 2 + 1 > N) {
                        ans = 0;
                    }
                }
            }

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(ans)
                    .append("\n");
        }

        System.out.print(sb);
    }
}