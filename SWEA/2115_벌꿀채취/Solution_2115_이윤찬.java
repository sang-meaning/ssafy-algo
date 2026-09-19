import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_이윤찬 {

    static int N, M, C;
    static int[][] map;

    // 각 위치에서 M칸을 선택했을 때 얻을 수 있는 최대 수익
    static int[][] profit;

    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for (int y = 0; y < N; y++) {

                st = new StringTokenizer(br.readLine());

                for (int x = 0; x < N; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            // M칸이 들어갈 수 있는 시작 위치만 사용
            profit = new int[N][N - M + 1];

            // 1. 각 M칸 구간의 최대 수익 계산
            for (int y = 0; y < N; y++) {

                for (int x = 0; x <= N - M; x++) {

                    profit[y][x] = getMaxProfit(y, x);
                }
            }

            answer = 0;

            // 2. 두 명의 일꾼 구간 선택
            selectWorkers();

            System.out.println("#" + tc + " " + answer);
        }
    }

    /*
     * (y, x)부터 가로 M칸 중
     * 꿀의 합이 C 이하인 부분집합을 선택해서
     * 얻을 수 있는 최대 수익 반환
     */
    static int getMaxProfit(int y, int x) {

        int maxProfit = 0;

        // M <= 5
        // 모든 부분집합 탐색
        for (int mask = 0; mask < (1 << M); mask++) {

            int sumHoney = 0;
            int sumProfit = 0;

            for (int i = 0; i < M; i++) {

                // i번째 벌통을 선택했는지 확인
                if ((mask & (1 << i)) != 0) {

                    int honey = map[y][x + i];

                    sumHoney += honey;
                    sumProfit += honey * honey;
                }
            }

            // C 이하일 경우에만 채취 가능
            if (sumHoney <= C) {
                maxProfit = Math.max(maxProfit, sumProfit);
            }
        }

        return maxProfit;
    }

    /*
     * 두 일꾼의 M칸 구간을 선택
     */
    static void selectWorkers() {

        for (int y1 = 0; y1 < N; y1++) {

            for (int x1 = 0; x1 <= N - M; x1++) {

                for (int y2 = y1; y2 < N; y2++) {

                    for (int x2 = 0; x2 <= N - M; x2++) {

                        // 완전히 같은 구간 방지
                        if (y1 == y2 && x1 == x2) {
                            continue;
                        }

                        // 같은 행이라면 겹치는지 확인
                        if (y1 == y2) {

                            /*
                             * 첫 번째 구간
                             * [x1, x1 + M - 1]
                             *
                             * 두 번째 구간
                             * [x2, x2 + M - 1]
                             */

                            if (x2 < x1 + M &&
                                x1 < x2 + M) {

                                continue;
                            }
                        }

                        int total =
                                profit[y1][x1]
                                + profit[y2][x2];

                        answer = Math.max(answer, total);
                    }
                }
            }
        }
    }
}