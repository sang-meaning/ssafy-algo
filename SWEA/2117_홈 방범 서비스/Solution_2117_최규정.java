import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] map = new int[N][N];

            // 도시 정보 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int answer = 0;

            // 서비스 중심 위치 완전탐색
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    // 서비스 영역 크기 K
                    for (int K = 1; K <= 2 * N - 1; K++) {

                        int houseCount = 0;

                        // 모든 집의 위치 확인
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {

                                // 집이 없는 칸은 제외
                                if (map[i][j] == 0) {
                                    continue;
                                }

                                // 맨해튼 거리 계산
                                int distance = Math.abs(r - i) + Math.abs(c - j);

                                // 서비스 범위에 포함되는 집
                                if (distance < K) {
                                    houseCount++;
                                }
                            }
                        }

                        // 운영 비용 계산
                        int cost = K * K
                                 + (K - 1) * (K - 1);

                        // 손해가 발생하지 않는 경우
                        if (houseCount * M >= cost) {

                            answer = Math.max(answer, houseCount);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }
}