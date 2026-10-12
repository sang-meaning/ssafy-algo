import java.io.*;
import java.util.*;

public class Solution_4012_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            visited = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 0번 재료는 무조건 A 음식에 고정
            visited[0] = true;
            comb(1, 1);

            System.out.println("#" + test_case + " " + minDiff);
        }
    }

    // 재료 N/2개를 선택
    static void comb(int idx, int count) {
        if (count == N / 2) {
            calcDiff();
            return;
        }

        for (int i = idx; i < N; i++) {
            visited[i] = true;
            comb(i + 1, count + 1);
            visited[i] = false;
        }
    }

    // 두 음식 간 시너지 차이 계산
    static void calcDiff() {
        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;

                if (visited[i] && visited[j]) {
                    sumA += map[i][j];
                } else if (!visited[i] && !visited[j]) {
                    sumB += map[i][j];
                }
            }
        }

        int diff = Math.abs(sumA - sumB);
        minDiff = Math.min(minDiff, diff);
    }
}
