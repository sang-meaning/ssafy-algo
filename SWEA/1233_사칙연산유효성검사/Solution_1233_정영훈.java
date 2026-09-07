import java.io.*;
import java.util.*;

public class Solution_1233_정영훈 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int answer = 1;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();          
                String value = st.nextToken();
                boolean isOperator =
                        value.equals("+") ||
                        value.equals("-") ||
                        value.equals("*") ||
                        value.equals("/");
                boolean hasChild = st.hasMoreTokens();
                if (isOperator) {
                    if (!hasChild) {
                        answer = 0;
                    }
                } else {
                    if (hasChild) {
                        answer = 0;
                    }
                }
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }
}