import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3421_유혜진 {
    static int N, M;
    static boolean[][] badPair; // badPair[a][b] = true 이면 a와 b는 같이 넣을 수 없음
    static boolean[] selected; // 현재 조합에 선택된 재료 체크 (1~N)
    static int answer;          // 가능한 버거 종류의 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 재료의 수
            M = Integer.parseInt(st.nextToken()); // 안 어울리는 쌍의 수

            badPair = new boolean[N + 1][N + 1];
            selected = new boolean[N + 1];
            answer = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // a와 b는 서로 함께 들어갈 수 없음 (양방향 표시)
                badPair[a][b] = true;
                badPair[b][a] = true;
            }

            // 1번 재료부터 선택 여부 결정 시작
            dfs(1);

            System.out.println("#" + tc + " " + answer);
        }
    }

    // idx번 재료를 버거에 넣을지 말지 결정하는 DFS
    static void dfs(int idx) {
        // [기저 조건] 1번부터 N번 재료까지 모두 결정을 마친 경우
        if (idx == N + 1) {
            answer++; // 유효한 버거 조합 1개 완성!
            return;
        }

        // --- 선택 1: idx번 재료를 넣지 않는 경우 ---
        dfs(idx + 1);

        // --- 선택 2: idx번 재료를 넣는 경우 ---
        // (단, 지금까지 선택한 재료들과 안 어울리는 쌍이 없는지 먼저 검사!)
        if (canSelect(idx)) {
            selected[idx] = true;  // 재료 선택 (Stamp)
            dfs(idx + 1);          // 다음 재료로 이동
            selected[idx] = false; // 원상 복구 (Unstamp)
        }
    }

    // idx번 재료를 현재 조합에 추가해도 괜찮은지 확인하는 함수
    static boolean canSelect(int idx) {
        for (int i = 1; i < idx; i++) {
            // i번 재료가 이미 선택되어 있고, i와 idx가 궁합이 안 맞는 쌍이라면 선택 불가!
            if (selected[i] && badPair[i][idx]) {
                return false;
            }
        }
        return true; // 충돌하는 재료가 없으면 선택 가능
    }
}