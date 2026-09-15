import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1868_한석호 {
    static int N;
    static char[][] board;
    static int[][] mineCount;
    static boolean[][] visited;
    
    // 8방향 탐색용 상하좌우 및 대각선
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().trim().toCharArray();
            }

            // 1. 각 칸의 인접한 8방향 지뢰 개수 계산
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.') {
                        int count = 0;
                        for (int d = 0; d < 8; d++) {
                            int nr = r + dx[d];
                            int nc = c + dy[d];
                            if (isInRange(nr, nc) && board[nr][nc] == '*') {
                                count++;
                            }
                        }
                        mineCount[r][c] = count;
                    }
                }
            }

            int clicks = 0;

            // 2. 지뢰 수가 '0'인 칸부터 BFS 탐색 (연쇄 반응 발생)
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.' && mineCount[r][c] == 0 && !visited[r][c]) {
                        clicks++;
                        bfs(r, c);
                    }
                }
            }

            // 3. 아직 방문하지 않은 남은 '.' 칸들 낱개 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.' && !visited[r][c]) {
                        clicks++;
                    }
                }
            }

            System.out.println("#" + tc + " " + clicks);
        }
    }

    // BFS를 활용한 연쇄 폭발 구현
    static void bfs(int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // 현재 위치에서 8방향 탐색
            for (int d = 0; d < 8; d++) {
                int nr = r + dx[d];
                int nc = c + dy[d];

                if (isInRange(nr, nc) && board[nr][nc] == '.' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // 인접한 칸도 주변 지뢰가 0이라면 연쇄 확장을 위해 큐에 삽입
                    if (mineCount[nr][nc] == 0) {
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}