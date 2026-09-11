import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_14510_한석호 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] trees = new int[N];

            int maxH = 0;
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, trees[i]);
            }

            // 목표 높이가 maxH일 때와 maxH + 1일 때 중 최솟값 선택
            int ans = Math.min(solve(trees, N, maxH), solve(trees, N, maxH + 1));

            System.out.println("#" + test_case + " " + ans);
        }
    }

    private static int solve(int[] trees, int N, int targetH) {
        int count1 = 0; // 필요한 +1의 개수
        int count2 = 0; // 필요한 +2의 개수

        for (int i = 0; i < N; i++) {
            int diff = targetH - trees[i];
            count2 += diff / 2;
            count1 += diff % 2;
        }

        // +2를 +1 두 개로 나누어 밸런스를 맞춤 (count2 > count1 인 경우)
        if (count2 > count1) {
            while (count2 - count1 > 1) {
                count2--;
                count1 += 2;
            }
        }

        // 필요한 날짜 계산
        if (count1 > count2) {
            // +1이 더 많은 경우: 홀수 번째 날(+1) 위주로 진행
            return count1 * 2 - 1;
        } else if (count2 > count1) {
            // +2가 1개 더 많은 경우 (+2 +2 -> 1, 2, 3, 4일차 처리)
            return count2 * 2;
        } else {
            // +1과 +2의 개수가 같은 경우 (완벽하게 짝을 이룸)
            return count1 * 2;
        }
    }
}