import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int ans;
    static boolean[] vCol;
    static boolean[] vDiag1; // 우상향 대각선 (/)
    static boolean[] vDiag2; // 우하향 대각선 (\)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            // 크기에 맞춰 방문 배열 생성
            vCol = new boolean[N];
            vDiag1 = new boolean[2 * N];
            vDiag2 = new boolean[2 * N];

            ans = 0;
            dfs(0);

            System.out.println("#" + tc + " " + ans);
        }
    }

    private static void dfs(int row) {
        // N개의 퀸을 모두 안착시켰을 때
        if (row == N) {
            ans++;
            return;
        }

        for (int col = 0; col < N; col++) {
            // 열, / 대각선, \ 대각선에 퀸이 없는 경우만 진행
            if (!vCol[col] && !vDiag1[row + col] && !vDiag2[row - col + N]) {
                // 방문 표시
                vCol[col] = true;
                vDiag1[row + col] = true;
                vDiag2[row - col + N] = true;

                dfs(row + 1); // 다음 행 호출

                // 백트래킹 (원상복구)
                vCol[col] = false;
                vDiag1[row + col] = false;
                vDiag2[row - col + N] = false;
            }
        }
    }
}