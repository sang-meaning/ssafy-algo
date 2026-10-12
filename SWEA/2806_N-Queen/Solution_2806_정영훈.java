import java.io.*;

public class Solution_2806_정영훈 {

    static int n, answer;
    static boolean[] column, diagonal1, diagonal2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            answer = 0;

            column = new boolean[n];
            diagonal1 = new boolean[2 * n - 1];
            diagonal2 = new boolean[2 * n - 1];

            dfs(0);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int row) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row + col;
            int d2 = row - col + n - 1;

            // 같은 열이나 대각선에 퀸이 있으면 배치 불가
            if (column[col] || diagonal1[d1] || diagonal2[d2]) {
                continue;
            }

            column[col] = diagonal1[d1] = diagonal2[d2] = true;
            dfs(row + 1);
            column[col] = diagonal1[d1] = diagonal2[d2] = false;
        }
    }
}