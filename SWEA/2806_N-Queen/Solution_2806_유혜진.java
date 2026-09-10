import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution_2806_유혜진 {
    static int N;
    static int ans;
    static int[] col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = 0;
            col = new int[N]; // col[i]는 i번째 행에 놓인 퀸의 열 번호를 의미합니다.

            dfs(0);
            System.out.println("#" + t + " " + ans);
        }
    }

    public static void dfs(int row) {
        // 모든 행에 퀸을 성공적으로 배치한 경우
        if (row == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            col[row] = i; // 현재 행(row)의 i번째 열에 퀸을 놓아봅니다.
            
            // 유망한 위치(서로 공격할 수 없는 위치)인지 확인
            if (isPossible(row)) {
                dfs(row + 1); // 다음 행으로 재귀 호출
            }
        }
    }

    public static boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            // 1. 같은 열에 이미 퀸이 있는 경우
            // 2. 대각선 상에 퀸이 있는 경우 (행의 차이와 열의 차이가 같으면 대각선 위치)
            if (col[i] == col[row] || Math.abs(row - i) == Math.abs(col[row] - col[i])) {
                return false;
            }
        }
        return true;
    }
}