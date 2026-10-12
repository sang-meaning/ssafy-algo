import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2806_한석호 {
    static int N;
    static int answer;

    // 충돌 체크용 배열 (O(1) 체크)
    static boolean[] colUsed;       // 열 사용 여부: c
    static boolean[] slashUsed;     // '/' 방향 대각선: r + c
    static boolean[] backslashUsed; // '\' 방향 대각선: r - c + (N - 1)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());

            answer = 0;
            colUsed = new boolean[N];
            slashUsed = new boolean[2 * N];
            backslashUsed = new boolean[2 * N];

            // 0번째 행부터 퀸 놓기 시작
            dfs(0);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void dfs(int row) {
        // N개의 퀸을 모두 배치한 경우
        if (row == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            int slash = row + col;
            int backslash = row - col + (N - 1);

            // 같은 열이나 대각선에 이미 퀸이 있다면 건너뜀
            if (colUsed[col] || slashUsed[slash] || backslashUsed[backslash]) {
                continue;
            }

            // 퀸 배치
            colUsed[col] = true;
            slashUsed[slash] = true;
            backslashUsed[backslash] = true;

            // 다음 행으로 이동
            dfs(row + 1);

            // 원복 (백트래킹)
            colUsed[col] = false;
            slashUsed[slash] = false;
            backslashUsed[backslash] = false;
        }
    }
}