import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringBuilder answer = new StringBuilder();

        // 테스트 케이스는 항상 10개
        for (int testCase = 1; testCase <= 10; testCase++) {
            int N = Integer.parseInt(br.readLine());
            boolean isValid = true;

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");

                // input[0] : 정점 번호
                // input[1] : 숫자 또는 연산자
                String value = input[1];

                boolean isOperator =
                        value.equals("+")
                        || value.equals("-")
                        || value.equals("*")
                        || value.equals("/");

                boolean isNumber = value.matches("\\d+");

                if (isOperator) {
                    // 연산자는 왼쪽, 오른쪽 자식이 모두 있어야 한다.
                    if (input.length != 4) {
                        isValid = false;
                    }
                } else if (isNumber) {
                    // 숫자는 자식이 없어야 한다.
                    if (input.length != 2) {
                        isValid = false;
                    }
                } else {
                    // 숫자도 아니고 연산자도 아닌 경우
                    isValid = false;
                }
            }

            answer.append("#")
                    .append(testCase)
                    .append(" ")
                    .append(isValid ? 1 : 0)
                    .append("\n");
        }

        System.out.print(answer);
    }
}