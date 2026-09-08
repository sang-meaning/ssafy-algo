import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static boolean[][] bad = new boolean[21][21];
    static boolean[] isSelected = new boolean[21];
    static int ans;

    static void dfs(int idx) {
        if (idx > N) {
            ans++;
            return;
        }

        dfs(idx + 1);

        boolean canPick = true;
        for (int i = 1; i < idx; i++) {
            if (isSelected[i] && bad[i][idx]) {
                canPick = false;
                break;
            }
        }

        if (canPick) {
            isSelected[idx] = true;
            dfs(idx + 1);
            isSelected[idx] = false; 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 테스트케이스마다 배열 및 정답 초기화
            for (int i = 1; i <= N; i++) {
                isSelected[i] = false;
                for (int j = 1; j <= N; j++) {
                    bad[i][j] = false;
                }
            }
            ans = 0;

            // 상극 정보 입력
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                bad[u][v] = true;
                bad[v][u] = true;
            }

            dfs(1);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
}