import java.io.*;
import java.io.*;

public class Solution_6782_정서우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            long n = Long.parseLong(br.readLine().trim());
            long count = 0;

            while (n > 2) {
                long root = (long) Math.sqrt(n);

                // 이미 완전제곱수인 경우 바로 제곱근으로 이동
                if (root * root == n) {
                    n = root;
                    count++;
                } else {
                    // n보다 큰 다음 완전제곱수까지 필요한 +1 연산을 한 번에 계산
                    long nextRoot = root + 1;
                    long nextSquare = nextRoot * nextRoot;

                    count += (nextSquare - n);
                    n = nextSquare;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }
}