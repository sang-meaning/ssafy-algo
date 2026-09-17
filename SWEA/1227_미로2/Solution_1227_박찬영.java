import java.io.*;
import java.util.*;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        for (int tc = 1; tc <= 10; tc++) {
            String testCaseNum = br.readLine();
            if (testCaseNum == null || testCaseNum.trim().isEmpty()) break;

            map = new int[100][100];
            int startX = 0, startY = 0;


            for (int i = 0; i < 100; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            int answer = bfs(startX, startY);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static int bfs(int startX, int startY) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[100][100];

        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];


                if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100) continue;


                if (map[nx][ny] == 3) {
                    return 1;
                }
                if (map[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }

        return 0;
    }
}