import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K;
    static int[][] film;
    static int minCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 두께 (행)
            W = Integer.parseInt(st.nextToken()); // 가로 (열)
            K = Integer.parseInt(st.nextToken()); // 연속 통과 기준

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

        dfs(row + 1, count);

        int[] backup = new int[W];
        System.arraycopy(film[row], 0, backup, 0, W);

        changeRow(row, 0);
        dfs(row + 1, count + 1);

        changeRow(row, 1);
        dfs(row + 1, count + 1);

        System.arraycopy(backup, 0, film[row], 0, W);
    }

    static void changeRow(int r, int color) {
        for (int c = 0; c < W; c++) {
            film[r][c] = color;
        }
    }

    static boolean check() {
        for (int c = 0; c < W; c++) {
            boolean colPass = false;
            int sameCount = 1; 

            for (int r = 1; r < D; r++) {
                if (film[r][c] == film[r - 1][c]) {
                    sameCount++;
                } else {
                    sameCount = 1; 
                }

                if (sameCount >= K) {
                    colPass = true;
                    break; 
                }
            }

            if (!colPass) {
                return false;
            }
        }
        return true;
    }
}