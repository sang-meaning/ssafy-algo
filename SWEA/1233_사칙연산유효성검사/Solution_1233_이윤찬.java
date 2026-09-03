import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_이윤찬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 총 10개의 테스트 케이스
        for (int testCase = 1; testCase <= 10; testCase++) {
            int n = Integer.parseInt(br.readLine().trim());
            boolean isValid = true;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int nodeIdx = Integer.parseInt(st.nextToken());
                String val = st.nextToken();
                
                // 자식이 있는 부모 노드 (1 ~ n / 2): 반드시 연산자여야 함
                if (nodeIdx <= n / 2) {
                    if (val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/")) {
                        // 정상적인 연산자이므로 통과
                    } else {
                        isValid = false;
                    }
                } 
                // 자식이 없는 리프 노드 (n / 2 + 1 ~ n): 반드시 숫자여야 함
                else {
                    if (val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/")) {
                        isValid = false; // 리프 노드에 연산자가 오면 무효
                    }
                }
                
                // 남은 자식 번호 입력값들은 유효성 검사에 필요 없으므로 버림
                // (완전 이진 트리이므로 자식 노드 번호 토큰이 남아있을 수 있음)
            }

            // 결과 출력 (유효하면 1, 불가능하면 0)
            System.out.println("#" + testCase + " " + (isValid ? 1 : 0));
        }
    }
}