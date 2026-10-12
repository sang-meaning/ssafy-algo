import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int testcase = 1; testcase <= 10; testcase++) {
            int N = Integer.parseInt(br.readLine());
            int answer = 1;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String value = st.nextToken();
                int childCount = st.countTokens();

                boolean isOperator =
                        value.equals("+") ||
                        value.equals("-") ||
                        value.equals("*") ||
                        value.equals("/");

                if (isOperator && childCount != 2) answer = 0;
                if (!isOperator && childCount != 0) answer = 0;
            }

            System.out.println("#" + testcase + " " + answer);
        }
    }
}