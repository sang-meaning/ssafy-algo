package al_prac.swea;

import java.io.*;
import java.util.*;

public class Solution_1226_하상호 {

    static final int SIZE = 16;

    static int[][] maze;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringBuilder output = new StringBuilder();

        // 문제에서 테스트 케이스는 총 10개
        for (int testCase = 1; testCase <= 10; testCase++) {
            int testNumber = Integer.parseInt(br.readLine());

            maze = new int[SIZE][SIZE];
            visited = new boolean[SIZE][SIZE];

            int startRow = 0;
            int startCol = 0;

            for (int row = 0; row < SIZE; row++) {
                String line = br.readLine();

                for (int col = 0; col < SIZE; col++) {
                    maze[row][col] = line.charAt(col) - '0';

                    if (maze[row][col] == 2) {
                        startRow = row;
                        startCol = col;
                    }
                }
            }

            int result = bfs(startRow, startCol);

            output.append("#")
                  .append(testNumber)
                  .append(" ")
                  .append(result)
                  .append("\n");
        }

        System.out.print(output);
    }

    static int bfs(int startRow, int startCol) {
        Queue<Position> queue = new ArrayDeque<>();

        queue.offer(new Position(startRow, startCol));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (maze[current.row][current.col] == 3) {
                return 1;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = current.row + dr[direction];
                int nextCol = current.col + dc[direction];

                if (
                    nextRow < 0 || nextRow >= SIZE
                    || nextCol < 0 || nextCol >= SIZE
                ) {
                    continue;
                }

                if (maze[nextRow][nextCol] == 1) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.offer(new Position(nextRow, nextCol));
            }
        }

        return 0;
    }
}