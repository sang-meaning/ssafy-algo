import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            grid = new int[N][N];
            
            int maxTaste = 0; // 최적화를 위해 치즈의 최대 맛 값 기록

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    maxTaste = Math.max(maxTaste, grid[i][j]);
                }
            }

            int maxChunks = 1; // 0일 차에는 덩어리가 1개 존재하므로 기본값 1

            // 1일 차부터 maxTaste-1 일 차까지 갉아먹었을 때 덩어리 수 계산
            for (int day = 1; day < maxTaste; day++) {
                visited = new boolean[N][N];
                int currentChunks = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        // 현재 날짜(day)보다 맛이 크고, 방문하지 않은 위치 탐색
                        if (grid[i][j] > day && !visited[i][j]) {
                            bfs(i, j, day);
                            currentChunks++;
                        }
                    }
                }

                maxChunks = Math.max(maxChunks, currentChunks);
            }

            System.out.println("#" + tc + " " + maxChunks);
        }
    }

    private static void bfs(int startX, int startY, int day) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    // 맛이 day보다 크고 아직 방문하지 않았다면 큐에 추가
                    if (grid[nx][ny] > day && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }
}