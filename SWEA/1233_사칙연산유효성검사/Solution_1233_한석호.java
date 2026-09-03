package samsung01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution_1233_한석호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 10개의 테스트 케이스 처리
        for (int t = 1; t <= 10; t++) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) {
                break;
            }

            int N = Integer.parseInt(line.trim());
            int isValid = 1;

            for (int i = 0; i < N; i++) {
                String[] info = br.readLine().trim().split("\\s+");

                // 값 (숫자 또는 연산자)
                String value = info[1];

                // 자식 노드가 존재하는지 확인 (토큰 개수가 2개 초과)
                boolean hasChild = info.length > 2;

                if (isValid == 0) {
                    continue; // 이미 유효하지 않다면 남은 입력만 소진
                }

                // 숫자인지 확인
                boolean isDigit = Character.isDigit(value.charAt(0));

                // 1. 자식이 있는데 숫자인 경우 -> 유효하지 않음
                if (hasChild && isDigit) {
                    isValid = 0;
                }
                // 2. 자식이 없는데 연산자인 경우 -> 유효하지 않음
                else if (!hasChild && !isDigit) {
                    isValid = 0;
                }
            }

            System.out.println("#" + t + " " + isValid);
        }
    }
}