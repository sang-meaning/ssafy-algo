import java.io.BufferedReader;      // 입력을 빠르게 받기 위한 클래스
import java.io.InputStreamReader;  // System.in을 문자 입력 형태로 변환
import java.util.ArrayDeque;       // Queue 구현체로 사용할 클래스
import java.util.Queue;            // Queue 자료구조
import java.util.StringTokenizer;  // 한 줄의 문자열을 공백 기준으로 나누기 위한 클래스

public class Solution_1225_한석호 {

    public static void main(String[] args) throws Exception {

        // 키보드 입력을 빠르게 받기 위한 BufferedReader 생성
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        // 총 10개의 테스트 케이스
        for (int tc = 0; tc < 10; tc++) {

            // 테스트 케이스 번호 입력
            // 예: 1
            int testCase = Integer.parseInt(br.readLine());

            // 다음 줄의 8개 숫자를 공백 기준으로 나누기
            // 예: 9550 9556 9550 9553 9558 9551 9551 9551
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 숫자들을 순서대로 처리하기 위해 Queue 생성
            Queue<Integer> queue = new ArrayDeque<>();

            // 입력된 8개의 숫자를 Queue에 저장
            while (st.hasMoreTokens()) {

                // 다음 숫자를 꺼내 정수로 변환한 뒤 Queue 뒤쪽에 삽입
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            // 암호 생성이 끝났는지 확인하기 위한 변수
            boolean finish = false;

            // 0 이하의 숫자가 나올 때까지 반복
            while (!finish) {

                // 한 사이클:
                // 첫 번째 숫자는 1 감소
                // 두 번째 숫자는 2 감소
                // ...
                // 다섯 번째 숫자는 5 감소
                for (int minus = 1; minus <= 5; minus++) {

                    // Queue의 맨 앞 숫자를 꺼낸 뒤 minus만큼 감소
                    int cur = queue.poll() - minus;

                    // 감소한 숫자가 0 이하가 되면
                    if (cur <= 0) {

                        // 문제 조건에 따라 0으로 만들어 Queue 맨 뒤에 삽입
                        queue.offer(0);

                        // 암호 생성 종료 표시
                        finish = true;

                        // 현재 for문 종료
                        break;
                    }

                    // 아직 0보다 크다면
                    // 감소한 숫자를 Queue 맨 뒤로 이동
                    queue.offer(cur);
                }
            }

            // 테스트 케이스 번호 출력
            // 예: #1
            System.out.print("#" + testCase + " ");

            // Queue에 남아있는 최종 8개의 암호를 순서대로 출력
            while (!queue.isEmpty()) {

                // 맨 앞 숫자를 하나씩 꺼내 출력
                System.out.print(queue.poll() + " ");
            }

            // 한 테스트 케이스 출력이 끝났으므로 줄바꿈
            System.out.println();
        }
    }
}