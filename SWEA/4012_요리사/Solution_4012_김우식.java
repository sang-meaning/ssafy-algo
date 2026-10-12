import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] synergy;
    static boolean[] selected;
    static int minDiff;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            synergy = new int[N][N];
            selected = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            // 시너지 입력
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(minDiff)
                    .append("\n");
        }

        System.out.print(sb);
    }

    // idx : 지금 보고 있는 재료 번호
    // count : A 음식에 선택한 재료 개수
    static void dfs(int idx, int count) {

        // A 음식에 N/2개를 다 골랐다면 맛 계산
        if (count == N / 2) {
            calculate();
            return;
        }

        // 모든 재료를 다 봤다면 종료
        if (idx == N) {
            return;
        }

        // 현재 재료를 A 음식에 넣는 경우
        selected[idx] = true;
        dfs(idx + 1, count + 1);

        // 현재 재료를 A 음식에 넣지 않는 경우
        selected[idx] = false;
        dfs(idx + 1, count);
    }

    static void calculate() {

        int tasteA = 0;
        int tasteB = 0;

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                // 둘 다 A 음식에 들어있는 경우
                if (selected[i] && selected[j]) {
                    tasteA += synergy[i][j] + synergy[j][i];
                }

                // 둘 다 B 음식에 들어있는 경우
                if (!selected[i] && !selected[j]) {
                    tasteB += synergy[i][j] + synergy[j][i];
                }
            }
        }

        int diff = Math.abs(tasteA - tasteB);

        minDiff = Math.min(minDiff, diff);
    }
}