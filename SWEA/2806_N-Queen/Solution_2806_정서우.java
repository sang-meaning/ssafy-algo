import java.io.*;

public class Solution_2806_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int cnt;
    static boolean[] col, diag1, diag2;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cnt = 0;

            col = new boolean[N];
            diag1 = new boolean[2 * N];
            diag2 = new boolean[2 * N];

            dfs(0);

            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int row) {
        if (row == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (col[i] || diag1[row + i] || diag2[row - i + N - 1])
                continue;

            col[i] = true;
            diag1[row + i] = true;
            diag2[row - i + N - 1] = true;

            dfs(row + 1);

            col[i] = false;
            diag1[i] = false;
            diag2[row - i + N - 1] = false;
        }
    }
}
