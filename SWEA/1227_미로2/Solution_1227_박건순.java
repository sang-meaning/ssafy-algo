import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    static final int SIZE = 100;

    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int test_number = Integer.parseInt(br.readLine());

            int[][] map = new int[SIZE][SIZE];

            int start_r = 0;
            int start_c = 0;

            for (int r = 0; r < SIZE; r++) {
                String line = br.readLine();

                for (int c = 0; c < SIZE; c++) {
                    map[r][c] = line.charAt(c) - '0';

                    if (map[r][c] == 2) {
                        start_r = r;
                        start_c = c;
                    }
                }
            }

            int result = bfs(map, start_r, start_c) ? 1 : 0;

            System.out.println("#" + test_number + " " + result);
        }
    }

    static boolean bfs(int[][] map, int start_r, int start_c) {
        boolean[][] visited = new boolean[SIZE][SIZE];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{start_r, start_c});
        visited[start_r][start_c] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 반드시 배열에 접근하기 전에 범위 검사
                if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE) {
                    continue;
                }

                if (visited[nr][nc] || map[nr][nc] == 1) {
                    continue;
                }

                if (map[nr][nc] == 3) {
                    return true;
                }

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return false;
    }
}
