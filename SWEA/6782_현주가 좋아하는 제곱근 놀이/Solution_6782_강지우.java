import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스 입력
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int t = 1; t <= T; t++) {
            long N = Long.parseLong(br.readLine().trim());
            long count = 0;
            
            // N이 2가 될 때까지 반복
            while (N > 2) {
                long root = (long) Math.sqrt(N);
                
                // 1. 현재 N이 완벽한 제곱수인 경우
                if (root * root == N) {
                    N = root;     // N을 N의 제곱근으로 갱신
                    count++;      // 제곱근 연산 1회 추가
                } 
                // 2. 현재 N이 제곱수가 아닌 경우
                else {
                    root++; // 다음으로 가까운 제곱근을 구하기 위해 +1
                    long nextSquare = root * root; // N보다 큰 가장 가까운 제곱수
                    
                    // 다음 제곱수까지 도달하기 위해 1을 더하는 횟수 + 제곱근을 씌우는 연산 1회
                    count += (nextSquare - N) + 1;
                    
                    // 제곱근을 씌운 결과로 N을 갱신
                    N = root;
                }
            }
            
            System.out.println("#" + t + " " + count);
        }
    }
}