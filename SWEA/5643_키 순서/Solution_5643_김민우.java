import java.io.*;
import java.util.*;

public class Solution_5643_김민우 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder answer = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());

            // shorter[a][b] == true: a가 b보다 작다.
            boolean[][] shorter = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                shorter[a][b] = true;
            }

            // 플로이드–워셜을 이용한 전이 폐쇄
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (!shorter[i][k]) {
                        continue;
                    }

                    for (int j = 1; j <= N; j++) {
                        if (shorter[k][j]) {
                            shorter[i][j] = true;
                        }
                    }
                }
            }

            int result = 0;

            for (int i = 1; i <= N; i++) {
                boolean orderKnown = true;

                for (int j = 1; j <= N; j++) {
                    if (i == j) {
                        continue;
                    }

                    /*
                     * i < j 또는 j < i 중 하나를 알아야
                     * 두 학생의 키 순서를 판단할 수 있다.
                     */
                    if (!shorter[i][j] && !shorter[j][i]) {
                        orderKnown = false;
                        break;
                    }
                }

                if (orderKnown) {
                    result++;
                }
            }

            answer.append('#')
                  .append(tc)
                  .append(' ')
                  .append(result)
                  .append('\n');
        }

        System.out.print(answer);
    }
}