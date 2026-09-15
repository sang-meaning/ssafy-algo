import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1868_유혜진 {
    static int N;
    static char[][] map;
    static int[][] mineCount;
    static boolean[][] visited;
    
    // 8방향 (상, 하, 좌, 우, 대각선)
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().trim().toCharArray();
            }

            // 1. 각 칸마다 주변 8방향의 지뢰 개수 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '*') {
                        continue;
                    }
                    int cnt = 0;
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                            if (map[nx][ny] == '*') {
                                cnt++;
                            }
                        }
                    }
                    mineCount[i][j] = cnt;
                }
            }

            int clickCount = 0;

            // 2. 주변 지뢰 개수가 0인 칸을 우선적으로 클릭하여 연쇄 오픈 (BFS)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != '*' && mineCount[i][j] == 0 && !visited[i][j]) {
                        bfs(i, j);
                        clickCount++;
                    }
                }
            }

            // 3. 아직 방문되지 않은 나머지 칸들(지뢰가 아니면서 숫자가 1 이상인 고립된 칸들) 개별 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != '*' && !visited[i][j]) {
                        clickCount++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(clickCount).append("\n");
        }
        System.out.print(sb);
    }

    // BFS를 통해 주변 0인 영역들을 연쇄적으로 열어줌
    static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && map[nx][ny] != '*') {
                        visited[nx][ny] = true;
                        // 만약 인접한 곳도 지뢰 개수가 0이라면 큐에 넣어 계속 연쇄 확산
                        if (mineCount[nx][ny] == 0) {
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }
}