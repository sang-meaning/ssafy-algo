import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, B;
    static int[] H = new int[20];
    static int ans;

    static void dfs(int idx, int sum) {
        if (sum >= ans) return;

        if (sum >= B) {
            ans = sum;
            return;
        }

        if (idx == N) return;

        dfs(idx + 1, sum + H[idx]);
        dfs(idx + 1, sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int total_sum = 0;
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
                total_sum += H[i];
            }

            ans = total_sum;
            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(ans - B).append("\n");
        }

        System.out.print(sb);
    }
}