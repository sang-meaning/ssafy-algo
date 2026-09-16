import java.util.*;

public class Solution {

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            N = sc.nextInt();

            map = new int[N][N];

            int maxTaste = 0;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {

                    map[x][y] = sc.nextInt();

                    maxTaste = Math.max(maxTaste, map[x][y]);
                }
            }

            int answer = 1;

            for (int day = 1; day <= maxTaste; day++) {

                visited = new boolean[N][N];

                int count = 0;

                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {

                        if (map[x][y] > day && !visited[x][y]) {

                            count++;
                            
                            bfs(x, y, day);
                        }
                    }
                }

                answer = Math.max(answer, count);
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void bfs(int x, int y, int day) {

        Deque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int cx = current[0];
            int cy = current[1];

            for (int d = 0; d < 4; d++) {

                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] <= day) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
}