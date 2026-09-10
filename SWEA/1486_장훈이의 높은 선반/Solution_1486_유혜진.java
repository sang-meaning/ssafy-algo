import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_유혜진 {
    static int N, B, ans;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            heights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;
            dfs(0, 0);

            System.out.println("#" + t + " " + (ans - B));
        }
    }

    static void dfs(int idx, int currentSum) {
        // 가지치기: 현재 합이 이미 구한 최소값 이상이면 더 탐색할 필요 없음
        if (currentSum >= ans) {
            return;
        }

        if (idx == N) {
            if (currentSum >= B) {
                ans = Math.min(ans, currentSum);
            }
            return;
        }

        // 현재 직원을 탑에 포함하는 경우
        dfs(idx + 1, currentSum + heights[idx]);
        // 현재 직원을 탑에 포함하지 않는 경우
        dfs(idx + 1, currentSum);
    }
}