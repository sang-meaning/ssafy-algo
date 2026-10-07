import java.util.*;

public class Solution_1247_임성진 {
    static int N, best;
    static int[] x, y;
    static boolean[] visited;

    static int dist(int a, int b) {
        return Math.abs(x[a] - x[b]) + Math.abs(y[a] - y[b]);
    }

    static void dfs(int cur, int cnt, int sum) {
        if (sum >= best) return;
        if (cnt == N) {
            best = Math.min(best, sum + dist(cur, 1));
            return;
        }
        for (int i = 2; i < N + 2; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i, cnt + 1, sum + dist(cur, i));
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            x = new int[N + 2];
            y = new int[N + 2];
            for (int i = 0; i < N + 2; i++) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            visited = new boolean[N + 2];
            best = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            System.out.println("#" + tc + " " + best);
        }
    }
}