import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int N;
    static boolean[] visited;
    static int[][] foodSynergy;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());

            foodSynergy = new int[N][N];
            visited = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    foodSynergy[i][j] =
                            Integer.parseInt(st.nextToken());
                }
            }

            // A와 B를 서로 바꾼 중복 조합 제거
            visited[0] = true;
            combine(1, 1);

            System.out.println("#" + testCase + " " + minDiff);
        }
    }

    static void combine(int startIdx, int count) {
        if (minDiff == 0) {
            return;
        }

        int targetCount = N / 2;

        if (count == targetCount) {
            calculateDifference();
            return;
        }

        int remainingCount = targetCount - count;

        for (int i = startIdx; i <= N - remainingCount; i++) {
            visited[i] = true;
            combine(i + 1, count + 1);
            visited[i] = false;
        }
    }

    static void calculateDifference() {
        int valueA = 0;
        int valueB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int synergy =
                        foodSynergy[i][j] + foodSynergy[j][i];

                if (visited[i] && visited[j]) {
                    valueA += synergy;
                } else if (!visited[i] && !visited[j]) {
                    valueB += synergy;
                }
            }
        }

        minDiff = Math.min(
                minDiff,
                Math.abs(valueA - valueB)
        );
    }
}