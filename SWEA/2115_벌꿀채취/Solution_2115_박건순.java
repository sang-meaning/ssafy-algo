import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, C;
    static int[][] map;
    static int answer;

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
            answer = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 첫 번째 사람 시작 위치
            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {

                    int profit1 = getMaxProfit(r1, c1);

                    // 두 번째 사람 시작 위치
                    for (int r2 = r1; r2 < N; r2++) {
                        for (int c2 = 0; c2 <= N - M; c2++) {

                            // 완전히 같은 위치 또는 겹치는 구간 제외
                            if (r1 == r2 && c2 < c1 + M) {
                                continue;
                            }

                            int profit2 = getMaxProfit(r2, c2);

                            answer = Math.max(answer, profit1 + profit2);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }


    // r, c에서 시작하는 M개의 벌통 중
    // C 이하로 꿀을 골랐을 때 최대 수익
    static int getMaxProfit(int r, int c) {

        int[] honey = new int[M];

        for (int i = 0; i < M; i++) {
            honey[i] = map[r][c + i];
        }

        return subset(honey, 0, 0, 0);
    }


    // 부분집합
    static int subset(int[] honey, int idx, int sum, int profit) {

        // 용량 초과
        if (sum > C) {
            return 0;
        }

        // M개를 모두 확인
        if (idx == M) {
            return profit;
        }

        // 현재 꿀 선택
        int select = subset(
                honey,
                idx + 1,
                sum + honey[idx],
                profit + honey[idx] * honey[idx]
        );

        // 현재 꿀 선택 안 함
        int notSelect = subset(
                honey,
                idx + 1,
                sum,
                profit
        );

        return Math.max(select, notSelect);
    }
}