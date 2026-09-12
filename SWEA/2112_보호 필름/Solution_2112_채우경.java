import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int D, W, K;
    static int[][] film;
    static int[] inject; // 약품 투입 상태: -1(투입 안 함), 0(약품 A), 1(약품 B)
    static int minInjections;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // K가 1인 경우 약품을 투입할 필요 없이 항상 합격
            if (K == 1) {
                sb.append("#").append(t).append(" 0\n");
                continue;
            }

            inject = new int[D];
            for (int i = 0; i < D; i++) {
                inject[i] = -1; // 초기화
            }

            minInjections = K; // 최악의 경우에도 K번 투입하면 무조건 통과

            dfs(0, 0);

            sb.append("#").append(t).append(" ").append(minInjections).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int row, int count) {
        // 백트래킹 가지치기: 현재 투입 횟수가 이미 최솟값 이상이면 중단
        if (count >= minInjections) {
            return;
        }

        // 모든 행에 대해 결정 완료 시 성능 검사
        if (row == D) {
            if (check()) {
                minInjections = Math.min(minInjections, count);
            }
            return;
        }

        // 1. 약품 투입 안 함
        inject[row] = -1;
        dfs(row + 1, count);

        // 2. 약품 A(0) 투입
        inject[row] = 0;
        dfs(row + 1, count + 1);

        // 3. 약품 B(1) 투입
        inject[row] = 1;
        dfs(row + 1, count + 1);
        
        // 원상 복구
        inject[row] = -1;
    }

    // 성능 검사 함수
    private static boolean check() {
        for (int c = 0; c < W; c++) {
            boolean pass = false;
            int consecutive = 1;

            for (int r = 1; r < D; r++) {
                int prevCell = (inject[r - 1] != -1) ? inject[r - 1] : film[r - 1][c];
                int currCell = (inject[r] != -1) ? inject[r] : film[r][c];

                if (prevCell == currCell) {
                    consecutive++;
                } else {
                    consecutive = 1;
                }

                if (consecutive >= K) {
                    pass = true;
                    break;
                }
            }

            // 한 열이라도 연속 K개를 만족하지 못하면 실패
            if (!pass) return false;
        }
        return true;
    }
}