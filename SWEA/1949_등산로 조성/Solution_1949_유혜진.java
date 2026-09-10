import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_유혜진 {
    static int N, K, maxLen;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            int maxHeight = 0;

            // 지도 정보 입력 및 가장 높은 봉우리의 높이 찾기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > maxHeight) {
                        maxHeight = map[i][j];
                    }
                }
            }

            maxLen = 0;
            visited = new boolean[N][N];

            // 가장 높은 봉우리들에서 각각 DFS 탐색 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, 1, false);
                        visited[i][j] = false;
                    }
                }
            }

            System.out.println("#" + t + " " + maxLen);
        }
    }

    static void dfs(int x, int y, int len, boolean isCut) {
        maxLen = Math.max(maxLen, len);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위를 벗어나거나 이미 방문한 경우 패스
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

            // 1. 다음 지형이 현재 지형보다 낮아서 그대로 이동할 수 있는 경우
            if (map[nx][ny] < map[x][y]) {
                visited[nx][ny] = true;
                dfs(nx, ny, len + 1, isCut);
                visited[nx][ny] = false;
            } 
            // 2. 다음 지형이 현재 지형보다 크거나 같지만, 아직 공사를 하지 않았고 K 이하로 깎아서 낮출 수 있는 경우
            else if (!isCut && map[nx][ny] - K < map[x][y]) {
                int originalHeight = map[nx][ny];
                
                // 최대한 긴 등산로를 만들기 위해 다음 지형을 (현재 지형 높이 - 1)로 깎음
                map[nx][ny] = map[x][y] - 1;
                visited[nx][ny] = true;
                
                dfs(nx, ny, len + 1, true);
                
                // 백트래킹 (원상복구)
                visited[nx][ny] = false;
                map[nx][ny] = originalHeight;
            }
        }
    }
}