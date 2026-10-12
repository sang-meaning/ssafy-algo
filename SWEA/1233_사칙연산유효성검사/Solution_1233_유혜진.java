import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1233_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // SWEA 문제 특성상 총 10개의 테스트 케이스가 주어집니다.
        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            boolean isValid = true;

            // [규칙 1] N이 짝수이면 올바른 사칙연산 식이 만들어질 수 없음
            if (N % 2 == 0) {
                isValid = false;
            }

            for (int i = 1; i <= N; i++) {
                String[] tokens = br.readLine().split(" ");
                char value = tokens[1].charAt(0); // 노드에 저장된 값 (tokens[0]은 노드 번호)
                
                // 값(value)이 연산자인지 확인
                boolean isOperator = (value == '+' || value == '-' || value == '*' || value == '/');

                // [규칙 2] 위치별 값 검사
                if (i <= N / 2) {
                    // 1 ~ N/2 번 위치는 자식이 있는 중간 노드 -> 연산자가 아니면(숫자면) 잘못됨
                    if (!isOperator) isValid = false;
                } else {
                    // (N/2) + 1 ~ N 번 위치는 맨 밑바닥 노드 -> 연산자이면 잘못됨
                    if (isOperator) isValid = false;
                }
            }

            // 결과 출력: 유효하면 1, 아니면 0
            int answer = isValid ? 1 : 0;
            System.out.println("#" + tc + " " + answer);
        }
    }
}