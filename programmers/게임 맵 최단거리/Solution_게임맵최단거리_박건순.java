import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        return bfs(maps);
    }

    private int bfs(int[][] maps) {

        int rows = maps.length;
        int cols = maps[0].length;

        int[][] dist = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == rows - 1 && col == cols - 1) {
                return dist[row][col];
            }

            for (int direction = 0; direction < 4; direction++) {

                int nextRow = row + DR[direction];
                int nextCol = col + DC[direction];

                if (nextRow < 0 || nextRow >= rows
                        || nextCol < 0 || nextCol >= cols) {
                    continue;
                }

                if (maps[nextRow][nextCol] == 0
                        || dist[nextRow][nextCol] != 0) {
                    continue;
                }

                dist[nextRow][nextCol] = dist[row][col] + 1;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        return -1;
    }
}