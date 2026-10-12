import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int answer = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    int[] dist = new int[N + 1];

                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {

                            if (map[i][j] == 1) {

                                int d = Math.abs(r - i) + Math.abs(c - j);

                                if (d <= N) {
                                    dist[d]++;
                                }
                            }
                        }
                    }

                    int house = 0;

                    for (int k = 1; k <= N + 1; k++) {

                        house += dist[k - 1];

                        int cost = k * k + (k - 1) * (k - 1);

                        if (house * M >= cost) {
                            answer = Math.max(answer, house);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}