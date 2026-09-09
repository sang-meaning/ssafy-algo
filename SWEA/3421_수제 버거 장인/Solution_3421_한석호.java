import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3421_한석호 {
    static int N, M, count;
    static int[] badPair;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            badPair = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                // 서로 같이 들어가면 안 되는 재료를 비트로 표현
                badPair[u] |= (1 << v);
                badPair[v] |= (1 << u);
            }

            count = 0;
            dfs(1, 0);

            System.out.println("#" + tc + " " + count);
        }
    }

    static void dfs(int idx, int selectedMask) {
        if (idx == N + 1) {
            count++;
            return;
        }

        // 1. 현재 재료(idx)를 안 넣는 경우
        dfs(idx + 1, selectedMask);

        // 2. 현재 재료(idx)를 넣는 경우
        // 기존 선택된 재료 중 idx와 궁합이 안 맞는 재료가 없는지 확인
        if ((selectedMask & badPair[idx]) == 0) {
            dfs(idx + 1, selectedMask | (1 << idx));
        }
    }
}