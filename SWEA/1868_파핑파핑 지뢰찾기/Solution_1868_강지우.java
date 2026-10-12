import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int N;
    static char[][] map;
    static int[][] mineCount;
    static boolean[][] visited;
    
    // 8방향 탐색용 배열 (상하좌우 및 대각선)
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];

            // 맵 정보 입력
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 1. 모든 칸에 대해 주변 지뢰 개수 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        int count = 0;
                        for (int d = 0; d < 8; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];
                            if (ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] == '*') {
                                count++;
                            }
                        }
                        mineCount[i][j] = count;
                    } else {
                        mineCount[i][j] = -1; // 지뢰인 경우 -1로 표시
                    }
                }
            }

            int clicks = 0;

            // 2. 주변 지뢰가 '0'인 칸들을 우선적으로 클릭 (BFS로 연쇄 폭발)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineCount[i][j] == 0 && !visited[i][j]) {
                        clicks++;
                        bfs(i, j);
                    }
                }
            }

            // 3. 연쇄 폭발로 열리지 않은 나머지 숫자가 있는 칸들 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineCount[i][j] > 0 && !visited[i][j]) {
                        clicks++;
                    }
                }
            }

            // 결과 출력
            System.out.println("#" + t + " " + clicks);
        }
    }

    // 연쇄적으로 주변을 열어주는 BFS 메서드
    static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cr = curr[0];
            int cc = curr[1];

            for (int d = 0; d < 8; d++) {
                int nr = cr + dx[d];
                int nc = cc + dy[d];

                // 범위를 벗어나지 않고, 방문하지 않았으며 지뢰가 아닌 경우
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] != '*') {
                    visited[nr][nc] = true;
                    // 연쇄 폭발이 일어나는 칸(주변 지뢰가 0)인 경우 큐에 추가하여 계속 탐색
                    if (mineCount[nr][nc] == 0) {
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}