import java.io.*;
import java.util.*;

public class Solution_2112_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int D, W, K;
    static int[][] film;
    static int[] inject;
    static int minCount;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (K == 1) {
                sb.append("#").append(tc).append(" 0\n");
                continue;
            }

            inject = new int[D];
            Arrays.fill(inject, -1);
            minCount = K;

            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(minCount).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int row, int count) {
        if (count >= minCount) {
            return;
        }

        if (row == D) {
            if (check()) {
                minCount = count;
            }
            return;
        }

        // 1. 약품을 투입하지 않는 경우
        inject[row] = -1;
        dfs(row + 1, count);

        // 2. A 약품 투입
        inject[row] = 0;
        dfs(row + 1, count + 1);

        // 3. B 약품 투입
        inject[row] = 1;
        dfs(row + 1, count + 1);

        // 원상 복구
        inject[row] = -1;
    }

    // 모든 열이 통과 기준 K를 만족하는지 검사
    static boolean check() {
        for (int c = 0; c < W; c++) {
            int maxCont = 1;
            int currentCont = 1;

            // 0번째 행의 값 가져오기
            int prev = (inject[0] != -1) ? inject[0] : film[0][c];

            for (int r = 1; r < D; r++) {
                int curr = (inject[r] != -1) ? inject[r] : film[r][c];

                if (curr == prev) {
                    currentCont++;
                } else {
                    prev = curr;
                    currentCont = 1;
                }

                if (currentCont > maxCont) {
                    maxCont = currentCont;
                }
            }

            if (maxCont < K) {
                return false;
            }
        }
        return true;
    }
}