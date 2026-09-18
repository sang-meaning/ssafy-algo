package swea;

import java.io.*;
import java.util.*;

public class Solution_2115_하상호 {

    static int N, M, C;
    static int[][] map;

    // 각 위치에서 M개 벌통을 선택했을 때 얻을 수 있는 최대 수익
    static int[][] profit;

    static int maxProfit;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            profit = new int[N][N - M + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 1. 각 M개 구간에서 얻을 수 있는 최대 수익 계산
            calculateProfit();

            // 2. 두 일꾼의 구간 선택
            int answer = selectWorkers();

            System.out.println("#" + tc + " " + answer);
        }
    }

    // 각 시작 위치에서 얻을 수 있는 최대 수익 계산
    static void calculateProfit() {

        for (int r = 0; r < N; r++) {

            for (int c = 0; c <= N - M; c++) {

                maxProfit = 0;

                // M개 벌통에서 부분집합 탐색
                subset(r, c, 0, 0, 0);

                profit[r][c] = maxProfit;
            }
        }
    }

    // M개의 벌통 중 어떤 벌통의 꿀을 채취할지 선택
    static void subset(int row, int startCol,
                       int idx, int sum, int value) {

        // C를 초과하면 불가능
        if (sum > C) {
            return;
        }

        // M개를 모두 확인
        if (idx == M) {
            maxProfit = Math.max(maxProfit, value);
            return;
        }

        int honey = map[row][startCol + idx];

        // 현재 벌통 선택
        subset(
                row,
                startCol,
                idx + 1,
                sum + honey,
                value + honey * honey
        );

        // 현재 벌통 선택하지 않음
        subset(
                row,
                startCol,
                idx + 1,
                sum,
                value
        );
    }

    // 두 일꾼의 구간 선택
    static int selectWorkers() {

        int answer = 0;

        for (int r1 = 0; r1 < N; r1++) {

            for (int c1 = 0; c1 <= N - M; c1++) {

                for (int r2 = r1; r2 < N; r2++) {

                    for (int c2 = 0; c2 <= N - M; c2++) {

                        // 완전히 같은 구간은 제외
                        if (r1 == r2 && c1 == c2) {
                            continue;
                        }

                        // 같은 행에 있을 경우 구간이 겹치는지 확인
                        if (r1 == r2) {

                            // [c1, c1 + M - 1]
                            // [c2, c2 + M - 1]

                            if (c1 + M > c2 &&
                                c2 + M > c1) {
                                continue;
                            }
                        }

                        answer = Math.max(
                                answer,
                                profit[r1][c1] + profit[r2][c2]
                        );
                    }
                }
            }
        }

        return answer;
    }
}