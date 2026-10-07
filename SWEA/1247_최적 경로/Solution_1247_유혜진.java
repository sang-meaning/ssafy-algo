import java.util.*;

// SWEA 1247. 최적 경로 - 순열(DFS) + 가지치기
public class Solution {
    static int N, answer;
    static int[] cx, cy;
    static int comX, comY, homeX, homeY;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            comX = sc.nextInt();
            comY = sc.nextInt();
            homeX = sc.nextInt();
            homeY = sc.nextInt();

            cx = new int[N];
            cy = new int[N];
            for (int i = 0; i < N; i++) {
                cx[i] = sc.nextInt();
                cy[i] = sc.nextInt();
            }

            visited = new boolean[N];
            answer = Integer.MAX_VALUE;
            dfs(0, comX, comY, 0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    // cnt: 방문한 고객 수, (x, y): 현재 위치, sum: 지금까지 이동 거리
    static void dfs(int cnt, int x, int y, int sum) {
        if (sum >= answer) return; // 가지치기

        if (cnt == N) {
            sum += Math.abs(x - homeX) + Math.abs(y - homeY);
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(cnt + 1, cx[i], cy[i], sum + Math.abs(x - cx[i]) + Math.abs(y - cy[i]));
            visited[i] = false;
        }
    }
}