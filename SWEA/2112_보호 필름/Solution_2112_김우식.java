import java.io.*;
import java.util.*;

public class Solution {

    static int D, W, K;
    static int[][] map;
    static int min;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = K;

            if (check()) {
                min = 0;
            } else {
                dfs(0, 0);
            }

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(min)
                    .append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int row, int cnt) {

        // 이미 최소값보다 많이 넣었으면 볼 필요 없음
        if (cnt >= min) {
            return;
        }

        // 현재 상태로 이미 통과하면 정답 갱신
        if (check()) {
            min = cnt;
            return;
        }

        // 모든 행을 다 확인했으면 종료
        if (row == D) {
            return;
        }

        // 현재 행 원본 저장
        int[] backup = map[row].clone();

        // 1. 약품 투입 안 함
        dfs(row + 1, cnt);

        // 2. A 약품 투입
        Arrays.fill(map[row], 0);
        dfs(row + 1, cnt + 1);

        // 3. B 약품 투입
        Arrays.fill(map[row], 1);
        dfs(row + 1, cnt + 1);

        // 원상복구
        map[row] = backup;
    }

    static boolean check() {

        // 모든 열 검사
        for (int c = 0; c < W; c++) {

            int count = 1;
            boolean pass = false;

            // 위에서 아래로 검사
            for (int r = 1; r < D; r++) {

                if (map[r][c] == map[r - 1][c]) {
                    count++;
                } else {
                    count = 1;
                }

                if (count >= K) {
                    pass = true;
                    break;
                }
            }

            // 한 열이라도 K개 연속이 없으면 실패
            if (!pass) {
                return false;
            }
        }

        return true;
    }
}