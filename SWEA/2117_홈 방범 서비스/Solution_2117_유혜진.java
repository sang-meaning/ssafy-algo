import java.util.Scanner;

public class Solution_2117_유혜진 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();   // 도시 크기
            int M = sc.nextInt();   // 집 하나가 내는 돈

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int answer = 0;   // 서비스 받는 집 수의 최댓값

            // ① 모든 칸을 중심으로 해보기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    // ② K를 1부터 늘려가며 해보기
                    for (int K = 1; K <= 2 * N; K++) {

                        // ③ 마름모 안의 집 수 세기
                        int count = 0;
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                int dist = Math.abs(r - i) + Math.abs(c - j);
                                if (map[i][j] == 1 && dist < K) {
                                    count++;
                                }
                            }
                        }

                        // ④ 손해 안 보면 최댓값 갱신
                        int cost = K * K + (K - 1) * (K - 1);
                        if (count * M >= cost) {
                            answer = Math.max(answer, count);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}