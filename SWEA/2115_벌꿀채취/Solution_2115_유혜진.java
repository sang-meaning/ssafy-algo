import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_유혜진 {
    static int N, M, C;
    static int[][] map;
    static int maxProfit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
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

            int[][] profits = new int[N][N - M + 1];

            // 1. 각 위치에서 M개 연속으로 채취할 때 얻을 수 있는 최대 수익 미리 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    maxProfit = 0;
                    getMax(i, j, 0, 0, 0);
                    profits[i][j] = maxProfit;
                }
            }

            // 2. 두 일꾼이 겹치지 않게 선택하는 경우의 수 중 최대 합 구하기
            int ans = 0;
            for (int i1 = 0; i1 < N; i1++) {
                for (int j1 = 0; j1 <= N - M; j1++) {
                    // 일꾼 1 선택
                    for (int i2 = i1; i2 < N; i2++) {
                        int startJ = (i1 == i2) ? j1 + M : 0;
                        for (int j2 = startJ; j2 <= N - M; j2++) {
                            // 일꾼 2 선택 (겹치지 않도록)
                            ans = Math.max(ans, profits[i1][j1] + profits[i2][j2]);
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    // 부분집합을 이용해 C 이하의 조건에서 얻을 수 있는 최대 제곱 합 계산
    static void getMax(int r, int c, int idx, int currentSum, int currentProfit) {
        if (currentSum > C) return;
        if (idx == M) {
            maxProfit = Math.max(maxProfit, currentProfit);
            return;
        }

        // 현재 벌통을 포함하는 경우
        getMax(r, c + 1, idx + 1, currentSum + map[r][c + idx], currentProfit + (map[r][c + idx] * map[r][c + idx]));
        // 현재 벌통을 포함하지 않는 경우
        getMax(r, c + 1, idx + 1, currentSum, currentProfit);
    }
}