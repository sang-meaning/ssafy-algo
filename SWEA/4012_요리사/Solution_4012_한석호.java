import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_한석호 {
    static int N;
    static int[][] S;
    static boolean[] selected; // true: A음식 재료, false: B음식 재료
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            S = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minDiff = Integer.MAX_VALUE;
            selected = new boolean[N];

            // 0번 재료는 항상 A음식에 고정(조합 중복 제거)하여 1개 뽑은 상태로 시작
            selected[0] = true;
            comb(1, 1);

            System.out.println("#" + tc + " " + minDiff);
        }
    }

    /**
     * @param idx   탐색할 식재료 인덱스
     * @param count 현재까지 A음식에 선택된 식재료 개수
     */
    static void comb(int idx, int count) {
        // A음식 재료 N/2개를 모두 고른 경우
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        // 남은 재료를 모두 선택해도 N/2개를 채울 수 없는 경우 가지치기
        if (idx >= N) return;

        // 1. idx번 재료를 A음식에 선택하는 경우
        selected[idx] = true;
        comb(idx + 1, count + 1);

        // 2. idx번 재료를 B음식에 선택하는 경우 (선택 해제)
        selected[idx] = false;
        comb(idx + 1, count);
    }

    // A음식과 B음식의 맛의 차이를 계산하는 함수
    static void calculateDifference() {
        int tasteA = 0;
        int tasteB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (selected[i] && selected[j]) {
                    // i, j 모두 A음식 재료인 경우
                    tasteA += S[i][j] + S[j][i];
                } else if (!selected[i] && !selected[j]) {
                    // i, j 모두 B음식 재료인 경우
                    tasteB += S[i][j] + S[j][i];
                }
            }
        }

        int diff = Math.abs(tasteA - tasteB);
        minDiff = Math.min(minDiff, diff);
    }
}