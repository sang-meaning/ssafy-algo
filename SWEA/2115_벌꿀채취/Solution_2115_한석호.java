import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_한석호 {
    static int N, M, C;
    static int[][] map;
    static int maxProfit;
    static int maxSubProfit; // 특정 M개 영역에서 얻을 수 있는 최대 제곱합

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxProfit = 0;
            // 1. 첫 번째 일꾼의 구간 (r1, c1) 선택
            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {
                    int p1 = getMaxProfitForSegment(r1, c1);

                    // 2. 두 번째 일꾼의 구간 (r2, c2) 선택
                    for (int r2 = r1; r2 < N; r2++) {
                        // 같은 행인 경우, 첫 번째 일꾼의 영역(c1 ~ c1 + M - 1)과 겹치지 않는 범위부터 시작
                        int startC2 = (r1 == r2) ? c1 + M : 0;
                        for (int c2 = startC2; c2 <= N - M; c2++) {
                            int p2 = getMaxProfitForSegment(r2, c2);
                            maxProfit = Math.max(maxProfit, p1 + p2);
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxProfit).append("\n");
        }

        System.out.print(sb.toString());
    }

    // 주어진 (r, c) 위치에서 시작하는 M개 벌통에서 C 이하로 꿀을 뽑아 얻는 최대 제곱합 구하기
    private static int getMaxProfitForSegment(int r, int c) {
        maxSubProfit = 0;
        findMaxSub(r, c, 0, 0, 0);
        return maxSubProfit;
    }

    // M개의 벌통에 대해 부분집합 탐색 (DFS)
    private static void findMaxSub(int r, int c, int idx, int sumHoney, int sumSquare) {
        if (sumHoney > C) return; // 제한 C를 초과하면 중단

        if (idx == M) {
            maxSubProfit = Math.max(maxSubProfit, sumSquare);
            return;
        }

        int honey = map[r][c + idx];

        // 1. 현재 벌통의 꿀을 채취하는 경우
        findMaxSub(r, c, idx + 1, sumHoney + honey, sumSquare + (honey * honey));

        // 2. 현재 벌통의 꿀을 채취하지 않는 경우
        findMaxSub(r, c, idx + 1, sumHoney, sumSquare);
    }
}