import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution_6782_한석호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (N > 2) {
                long root = (long) Math.sqrt(N);

                // N이 완전제곱수인 경우
                if (root * root == N) {
                    N = root;
                    count++;
                } 
                // N이 완전제곱수가 아닌 경우 -> 다음 완전제곱수로 바로 이동
                else {
                    long nextRoot = root + 1;
                    long nextSquare = nextRoot * nextRoot;
                    
                    count += (nextSquare - N); // 1을 더해주는 횟수를 일괄 계산
                    N = nextSquare;           // N을 다음 완전제곱수로 변경
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb.toString());
    }
}