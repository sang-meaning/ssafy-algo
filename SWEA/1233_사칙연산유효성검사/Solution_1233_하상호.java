package al_prac.swea;
import java.io.*;
import java.util.*;

public class Solution_1233_하상호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringBuilder output = new StringBuilder();

        for (int testCase = 1; testCase <= 10; testCase++) {
            int nodeCount = Integer.parseInt(br.readLine());
            int result = 1;

            for (int i = 0; i < nodeCount; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                // 노드 번호
                st.nextToken();

                // 노드에 저장된 값
                String value = st.nextToken();

                // 남아 있는 토큰이 있으면 자식 노드가 존재함
                boolean hasChildren = st.hasMoreTokens();
                boolean isOperator = isOperator(value);

                if (hasChildren) {
                    // 자식이 있는 노드는 연산자여야 함
                    if (!isOperator) {
                        result = 0;
                    }
                } else {
                    // 자식이 없는 노드는 숫자여야 함
                    if (isOperator) {
                        result = 0;
                    }
                }
            }

            output.append("#")
                  .append(testCase)
                  .append(" ")
                  .append(result)
                  .append("\n");
        }

        System.out.print(output);
    }

    static boolean isOperator(String value) {
        return value.equals("+")
            || value.equals("-")
            || value.equals("*")
            || value.equals("/");
    }
}