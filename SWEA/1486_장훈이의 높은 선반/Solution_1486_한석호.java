import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1486_한석호 {
    static int N, B;
    static int[] H;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            H = new int[N];
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            // 차이의 최댓값(초기값 설정)
            minDiff = Integer.MAX_VALUE;

            // 0번 점원부터 탐색 시작, 현재 누적 높이 = 0
            dfs(0, 0);

            System.out.println("#" + t + " " + minDiff);
        }
    }

    static void dfs(int idx, int sum) {
        // 백트래킹 1: 현재 높이가 이미 B 이상인 경우
        if (sum >= B) {
            minDiff = Math.min(minDiff, sum - B);
            return; // 키가 자연수이므로 더 더해봐야 최소 차이를 갱신할 수 없음
        }

        // 백트래킹 2: 이미 구한 최적해보다 차이가 벌어지는 경로 가지치기
        if (sum - B >= minDiff) {
            return;
        }

        // 모든 점원을 고려했으나 B를 넘지 못한 경우
        if (idx == N) {
            return;
        }

        // 1. 현재 점원을 탑에 포함하는 경우
        dfs(idx + 1, sum + H[idx]);

        // 2. 현재 점원을 탑에 포함하지 않는 경우
        dfs(idx + 1, sum);
    }
}