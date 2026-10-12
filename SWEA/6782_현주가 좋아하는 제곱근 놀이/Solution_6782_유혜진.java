import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_6782_유혜진 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            long N = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (N > 2) {
                long root = (long) Math.sqrt(N);
                if (root * root == N) {
                    // N이 이미 제곱수인 경우
                    N = root;
                    count++;
                } else {
                    // N이 제곱수가 아닌 경우, 다음 제곱수까지 필요한 연산 횟수 계산
                    long nextRoot = root + 1;
                    long nextSquare = nextRoot * nextRoot;
                    count += (nextSquare - N) + 1;
                    N = nextRoot;
                }
            }

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}