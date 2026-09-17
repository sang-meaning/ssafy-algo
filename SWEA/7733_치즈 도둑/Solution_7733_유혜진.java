import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7733_유혜진 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            int maxFlavor = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxFlavor = Math.max(maxFlavor, map[i][j]);
                }
            }

            int maxChunks = 1; // 치즈가 아예 안 먹히거나 기본 상태일 때 최소 1개
            for (int day = 0; day <= maxFlavor; day++) {
                visited = new boolean[N][N];
                int chunks = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] > day && !visited[i][j]) {
                            bfs(i, j, day);
                            chunks++;
                        }
                    }
                }
                maxChunks = Math.max(maxChunks, chunks);
            }

            sb.append("#").append(tc).append(" ").append(maxChunks).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int r, int c, int day) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cr = curr[0];
            int cc = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if (map[nr][nc] > day && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}