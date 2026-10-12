import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_한석호 {
    static int D, W, K;
    static int[][] film;
    static int minAns;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
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

            // K가 1이면 약품 투여 없이 무조건 통과
            if (K == 1) {
                System.out.println("#" + tc + " 0");
                continue;
            }

            minAns = K; // 최악의 경우에도 K번 투여하면 무조건 통과됨

            // 0번 행부터 약품 투여 횟수 0으로 시작
            dfs(0, 0);

            System.out.println("#" + tc + " " + minAns);
        }
    }

    // row: 현재 확인할 막의 인덱스, cnt: 현재까지 약품 투여 횟수
    static void dfs(int row, int cnt) {
        // 이미 찾은 최소 약품 투여 횟수보다 크거나 같으면 가지치기
        if (cnt >= minAns) {
            return;
        }

        // 모든 행에 대한 결정을 마쳤을 때 성능 검사
        if (row == D) {
            if (check()) {
                minAns = Math.min(minAns, cnt);
            }
            return;
        }

        // 1. 약품을 투여하지 않는 경우
        dfs(row + 1, cnt);

        // 원본 행 백업
        int[] temp = new int[W];
        for (int c = 0; c < W; c++) {
            temp[c] = film[row][c];
        }

        // 2. 약품 A(0) 투여
        for (int c = 0; c < W; c++) film[row][c] = 0;
        dfs(row + 1, cnt + 1);

        // 3. 약품 B(1) 투여
        for (int c = 0; c < W; c++) film[row][c] = 1;
        dfs(row + 1, cnt + 1);

        // 원상 복구 (Backtracking)
        for (int c = 0; c < W; c++) {
            film[row][c] = temp[c];
        }
    }

    // 성능 검사 함수 (모든 열에 대해 연속된 동일 특성이 K개 이상 존재하는지 확인)
    static boolean check() {
        for (int c = 0; c < W; c++) {
            boolean passed = false;
            int count = 1;

            for (int r = 1; r < D; r++) {
                if (film[r][c] == film[r - 1][c]) {
                    count++;
                } else {
                    count = 1;
                }

                if (count >= K) {
                    passed = true;
                    break; // 현재 열은 통과했으므로 다음 열 검사
                }
            }

            // 하나라도 K개 연속 조건을 통과하지 못하면 전체 성능검사 실패
            if (!passed) {
                return false;
            }
        }
        return true;
    }
}