import java.io.*;
import java.util.*;

public class Solution_4012_정영훈 {
    static boolean[] visited;
    static int[][] map;
    static int minValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            visited = new boolean[n];
            map = new int[n][n];
            minValue = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited[0] = true;
            dfs(1, 1, n);

            sb.append("#").append(tc)
              .append(" ").append(minValue).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int start, int count, int n) {
        if (count == n / 2) {
            int a = 0;
            int b = 0;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        a += map[i][j] + map[j][i];
                    } else if (!visited[i] && !visited[j]) {
                        b += map[i][j] + map[j][i];
                    }
                }
            }

            minValue = Math.min(minValue, Math.abs(a - b));
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            dfs(i + 1, count + 1, n);
            visited[i] = false;
        }
    }
}