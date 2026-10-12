package al_prac.swea;

import java.io.*;
import java.util.*;

public class Solution_4012_하상호 {

    static int N;
    static int[][] synergy;
    static boolean[] selected;
    static int minDifference;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            synergy = new int[N][N];
            selected = new boolean[N];
            minDifference = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // A와 B를 서로 바꾼 경우는 동일하므로
            // 0번 식재료를 A음식에 고정한다.
            selected[0] = true;
            combination(1, 1);

            answer.append("#")
                  .append(tc)
                  .append(" ")
                  .append(minDifference)
                  .append("\n");
        }

        System.out.print(answer);
    }

    static void combination(int start, int count) {
        // A음식에 N/2개의 식재료를 모두 선택
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        // 앞으로 남은 재료를 전부 선택해도 N/2개를 채울 수 없는 경우
        if (count + (N - start) < N / 2) {
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            combination(i + 1, count + 1);
            selected[i] = false;

            // 맛의 차이는 음수가 될 수 없으므로 최적해
            if (minDifference == 0) {
                return;
            }
        }
    }

    static void calculateDifference() {
        int tasteA = 0;
        int tasteB = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int pairSynergy = synergy[i][j] + synergy[j][i];

                if (selected[i] && selected[j]) {
                    tasteA += pairSynergy;
                } else if (!selected[i] && !selected[j]) {
                    tasteB += pairSynergy;
                }
            }
        }

        minDifference = Math.min(
                minDifference,
                Math.abs(tasteA - tasteB)
        );
    }
}