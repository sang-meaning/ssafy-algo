import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 10개의 테스트 케이스 처리
        for (int t = 1; t <= 10; t++) {
            // 테스트 케이스 번호 입력 (문제 조건상 입력으로 들어옴)
            String tcNum = br.readLine();
            if (tcNum == null) break;
            
            Queue<Integer> queue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 8개의 숫자 큐에 삽입
            for (int i = 0; i < 8; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }
            
            int minus = 1; // 감소시킬 값 (1 ~ 5)
            
            while (true) {
                int num = queue.poll() - minus;
                
                // 0 이하가 되면 0으로 저장하고 종료
                if (num <= 0) {
                    queue.offer(0);
                    break;
                }
                
                queue.offer(num);
                
                // 감소값 순환 (1 -> 2 -> 3 -> 4 -> 5 -> 1)
                minus++;
                if (minus > 5) {
                    minus = 1;
                }
            }
            
            // 결과 출력
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tcNum).append(" ");
            for (int val : queue) {
                sb.append(val).append(" ");
            }
            
            System.out.println(sb.toString().trim());
        }
    }
}