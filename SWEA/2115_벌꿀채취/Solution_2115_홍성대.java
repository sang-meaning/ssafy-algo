import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] map;
    static int[][] profit; // (r, c)부터 M칸을 선택했을 때 얻을 수 있는 최대 수익
    static int maxHoneyProfit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
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

            // 1. 각 위치에서 M칸 선택 시 얻을 수 있는 최대 수익 미리 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    maxHoneyProfit = 0;
                    subset(i, j, 0, 0, 0);
                    profit[i][j] = maxHoneyProfit;
                }
            }

            // 2. 서로 겹치지 않는 두 일꾼의 영역 선택 후 최댓값 계산
            int ans = 0;
            for (int r1 = 0; r1 < N; r1++) {
                for (int c1 = 0; c1 <= N - M; c1++) {
                    for (int r2 = r1; r2 < N; r2++) {
                        // 같은 행이면 c1 + M부터 탐색해야 겹치지 않음
                        int startC2 = (r1 == r2) ? c1 + M : 0;
                        for (int c2 = startC2; c2 <= N - M; c2++) {
                            ans = Math.max(ans, profit[r1][c1] + profit[r2][c2]);
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    // M개의 칸 중 채취량 합이 C를 넘지 않으면서 제곱합이 최대가 되는 부분집합 탐색
    static void subset(int r, int c, int idx, int sumHoney, int sumCost) {
        if (sumHoney > C) return;

        if (idx == M) {
            maxHoneyProfit = Math.max(maxHoneyProfit, sumCost);
            return;
        }

        int current = map[r][c + idx];
        // 현재 벌통을 선택하는 경우
        subset(r, c, idx + 1, sumHoney + current, sumCost + current * current);
        // 현재 벌통을 선택하지 않는 경우
        subset(r, c, idx + 1, sumHoney, sumCost);
    }
}