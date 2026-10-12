import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] synergy;
    static boolean[] selected;
    static int minDifference;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        for (int testCase = 1; testCase <= T; testCase++) {
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

            // A와 B를 바꾸어도 결과가 같으므로
            // 0번 재료는 항상 A 음식에 포함시킨다.
            selected[0] = true;

            chooseIngredients(1, 1);

            answer.append("#")
                    .append(testCase)
                    .append(" ")
                    .append(minDifference)
                    .append("\n");
        }

        System.out.print(answer);
    }

    /**
     * A 음식에 들어갈 N/2개의 재료를 선택한다.
     *
     * @param index 현재 확인할 재료 번호
     * @param count 현재까지 A 음식에 선택한 재료 개수
     */
    static void chooseIngredients(int index, int count) {
        // 맛 차이가 0이면 가능한 최솟값이므로 탐색 종료
        if (minDifference == 0) {
            return;
        }

        // A 음식의 재료를 N/2개 모두 선택한 경우
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        // 모든 재료를 확인한 경우
        if (index == N) {
            return;
        }

        // 남은 재료를 모두 선택해도 N/2개를 만들 수 없는 경우
        if (count + (N - index) < N / 2) {
            return;
        }

        // 현재 재료를 A 음식에 넣는 경우
        selected[index] = true;
        chooseIngredients(index + 1, count + 1);

        // 현재 재료를 B 음식에 넣는 경우
        selected[index] = false;
        chooseIngredients(index + 1, count);
    }

    static void calculateDifference() {
        int tasteA = 0;
        int tasteB = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {

                // 두 재료가 모두 A 음식에 들어간 경우
                if (selected[i] && selected[j]) {
                    tasteA += synergy[i][j] + synergy[j][i];
                }

                // 두 재료가 모두 B 음식에 들어간 경우
                else if (!selected[i] && !selected[j]) {
                    tasteB += synergy[i][j] + synergy[j][i];
                }
            }
        }

        int difference = Math.abs(tasteA - tasteB);
        minDifference = Math.min(minDifference, difference);
    }
}