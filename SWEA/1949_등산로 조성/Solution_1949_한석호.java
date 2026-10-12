import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Solution_1949_한석호 {
    static int N, K;
    static int[][] board;
    static boolean[][] visited;
    static int maxLen;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            visited = new boolean[N][N];
            maxLen = 0;

            int maxHeight = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] > maxHeight) {
                        maxHeight = board[i][j];
                    }
                }
            }

            // 최고 봉우리 좌표 수집
            List<Point> peaks = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == maxHeight) {
                        peaks.add(new Point(i, j));
                    }
                }
            }

            // 각 봉우리에서 DFS 탐색 시작
            for (Point p : peaks) {
                visited[p.x][p.y] = true;
                dfs(p.x, p.y, 1, false);
                visited[p.x][p.y] = false; // 백트래킹 복원
            }

            System.out.println("#" + t + " " + maxLen);
        }
    }

    static void dfs(int x, int y, int length, boolean usedCut) {
        if (length > maxLen) {
            maxLen = length;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 지도 경계 및 방문 여부 확인
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                // 1. 깎지 않고 이동 가능한 경우
                if (board[nx][ny] < board[x][y]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, length + 1, usedCut);
                    visited[nx][ny] = false;
                }
                // 2. 깎아서 이동할 수 있고, 아직 공사 기회가 남은 경우
                else if (!usedCut) {
                    int needed = board[nx][ny] - (board[x][y] - 1);
                    if (needed <= K) {
                        int originalHeight = board[nx][ny];
                        board[nx][ny] = board[x][y] - 1; // 가장 유리한 높이(현재-1)로 설정
                        visited[nx][ny] = true;

                        dfs(nx, ny, length + 1, true);

                        // 상태 원복 (백트래킹)
                        visited[nx][ny] = false;
                        board[nx][ny] = originalHeight;
                    }
                }
            }
        }
    }
}