import java.util.*;

// SWEA 5643. 키 순서 - 플로이드-워셜
// tall[a][b] = true : a보다 b가 크다 (a → b로 갈 수 있다)
public class Solution_5643_유혜진 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            boolean[][] tall = new boolean[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                tall[a][b] = true;
            }

            // i < k 이고 k < j 이면 i < j
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (!tall[i][k]) continue; // 가지치기: i < k 가 아니면 볼 필요 없음
                    for (int j = 1; j <= N; j++) {
                        if (tall[k][j]) tall[i][j] = true;
                    }
                }
            }

            int answer = 0;
            for (int i = 1; i <= N; i++) {
                int cnt = 0;
                for (int j = 1; j <= N; j++) {
                    // i보다 큰지 작은지 알 수 있는 학생 수
                    if (tall[i][j] || tall[j][i]) cnt++;
                }
                if (cnt == N - 1) answer++;
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}