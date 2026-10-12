import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_유혜진 {
    static int D, W, K;
    static int[][] film;     // 현재 필름 상태
    static int[][] backup;   // 원래 필름 상태 (원상복구용)
    static int minAns;       // 최소 약품 투여 횟수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 두께 (행)
            W = Integer.parseInt(st.nextToken()); // 가로 크기 (열)
            K = Integer.parseInt(st.nextToken()); // 합격 기준 (연속 개수)

            film = new int[D][W];
            backup = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    film[i][j] = val;
                    backup[i][j] = val; // 원본 백업
                }
            }

            minAns = K; // 최악의 경우에도 K번 투여하면 무조건 통과 가능

            // 1. 약품을 0개 넣었을 때(원래 상태) 먼저 통과하는지 확인!
            if (check()) {
                System.out.println("#" + tc + " " + 0);
            } else {
                // 2. 통과 못하면 DFS 탐색 시작 (0번째 행부터, 투여 횟수 0회)
                dfs(0, 0);
                System.out.println("#" + tc + " " + minAns);
            }
        }
    }

    // [DFS / 백트래킹]
    // depth: 현재 고려 중인 행 (0 ~ D-1)
    // cnt: 현재까지 약품을 투여한 횟수
    static void dfs(int depth, int cnt) {
        // 가지치기 (Pruning): 이미 구한 최소 횟수보다 크거나 같으면 더 볼 필요 없음!
        if (cnt >= minAns) return;

        // 기저 조건: 맨 끝 행(D)까지 모두 약품 투여 여부를 결정했을 때
        if (depth == D) {
            if (check()) {
                minAns = Math.min(minAns, cnt); // 최솟값 갱신
            }
            return;
        }

        // --- 3가지 선택 시도 ---

        // 선택 1: 약품 투여 안 함 (원래 상태 유지)
        dfs(depth + 1, cnt);

        // 선택 2: A 약품 투여 (depth행 전체를 0으로 변경)
        setRow(depth, 0);
        dfs(depth + 1, cnt + 1);

        // 선택 3: B 약품 투여 (depth행 전체를 1로 변경)
        setRow(depth, 1);
        dfs(depth + 1, cnt + 1);

        // 원상 복구: 다음 경우의 수를 위해 원래 필름 상태로 되돌림
        restoreRow(depth);
    }

    // [약품 투여 함수] row번째 행 전체를 type(0 또는 1)으로 변경
    static void setRow(int row, int type) {
        for (int j = 0; j < W; j++) {
            film[row][j] = type;
        }
    }

    // [원상 복구 함수] row번째 행 전체를 원본(backup) 상태로 되돌림
    static void restoreRow(int row) {
        for (int j = 0; j < W; j++) {
            film[row][j] = backup[row][j];
        }
    }

    // [성능 검사 함수] 모든 열이 세로 방향으로 동일 특성 K개 이상 연속인지 확인
    static boolean check() {
        if (K == 1) return true; // K가 1이면 무조건 통과

        for (int j = 0; j < W; j++) { // 각 열마다 확인
            int count = 1;
            boolean pass = false;

            for (int i = 0; i < D - 1; i++) {
                if (film[i][j] == film[i + 1][j]) {
                    count++;
                } else {
                    count = 1; // 연속이 깨지면 1부터 다시 시작
                }

                if (count >= K) {
                    pass = true; // 이 열은 합격!
                    break;
                }
            }

            if (!pass) return false; // 단 하나의 열이라도 K를 못 채우면 불합격!
        }

        return true; // 모든 열이 통과했으면 합격!
    }
}