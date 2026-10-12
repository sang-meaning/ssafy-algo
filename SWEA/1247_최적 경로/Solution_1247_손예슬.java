
import java.io.*;
import java.util.*;

public class Solution {

    static int N, min;
    static int[][] customer;
    static int homeX, homeY;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int companyX = Integer.parseInt(st.nextToken());
            int companyY = Integer.parseInt(st.nextToken());

            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());

            customer = new int[N][2];
            visited = new boolean[N];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                customer[i][0] = Integer.parseInt(st.nextToken());
                customer[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, companyX, companyY, 0);

            sb.append("#").append(tc)
              .append(" ").append(min).append("\n");
        }

        System.out.print(sb);
    }

    // depth: 방문한 고객 수
    // x, y: 현재 위치
    // dist: 지금까지 이동한 거리
    static void dfs(int depth, int x, int y, int dist) {

        // 가지치기
        if (dist >= min) return;

        // 고객을 모두 방문했다면 집으로 이동
        if (depth == N) {
            int total = dist
                + Math.abs(x - homeX)
                + Math.abs(y - homeY);

            min = Math.min(min, total);
            return;
        }

        // 다음에 방문할 고객 선택 (순열)
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            int nx = customer[i][0];
            int ny = customer[i][1];

            int distance = Math.abs(x - nx)
                         + Math.abs(y - ny);

            visited[i] = true;

            dfs(depth + 1, nx, ny, dist + distance);

            visited[i] = false; // 백트래킹
        }
    }
}
