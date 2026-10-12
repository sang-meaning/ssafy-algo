import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1227_유혜진 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            br.readLine(); // 테스트 케이스 번호
            map = new int[100][100];
            visited = new boolean[100][100];
            int startR = 0, startC = 0;

            for (int i = 0; i < 100; i++) {
                String line = br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        startR = i;
                        startC = j;
                    }
                }
            }

            int ans = bfs(startR, startC) ? 1 : 0;
            System.out.println("#" + t + " " + ans);
        }
    }

    static boolean bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cr = curr[0];
            int cc = curr[1];

            if (map[cr][cc] == 3) return true;

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nr < 100 && nc >= 0 && nc < 100) {
                    if (!visited[nr][nc] && map[nr][nc] != 1) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return false;
    }
}