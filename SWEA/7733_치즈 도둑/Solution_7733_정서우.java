import java.io.*;
import java.util.*;

public class Solution_7733_정서우 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];

            int maxDay = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > maxDay) {
                        maxDay = map[i][j];
                    }
                }
            }

            // 치즈를 아무것도 먹지 않은 0일차에는 덩어리가 1개
            int maxChunks = 1;

            // 1일부터 치즈의 최대 맛 수치(maxDay - 1)까지 시뮬레이션
            for (int day = 1; day < maxDay; day++) {
                visited = new boolean[N][N];
                int chunkCount = 0;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        // day 이하인 칸은 이미 먹힌 칸이므로 제외
                        if (map[i][j] > day && !visited[i][j]) {
                            bfs(i, j, day);
                            chunkCount++;
                        }
                    }
                }

                if (chunkCount > maxChunks) {
                    maxChunks = chunkCount;
                }
            }

            System.out.println("#" + tc + " " + maxChunks);
        }
    }

    static void bfs(int startX, int startY, int day) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;

                // day보다 큰 치즈만 연결 가능
                if (map[nx][ny] > day) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}