
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            boolean[][] map = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                map[a][b] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (map[i][k] && map[k][j]) {
                            map[i][j] = true;
                        }
                    }
                }
            }

            int answer = 0;

            for (int i = 1; i <= N; i++) {
                int count = 0;

                for (int j = 1; j <= N; j++) {
                    if (map[i][j] || map[j][i]) {
                        count++;
                    }
                }

                if (count == N - 1) {
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}
