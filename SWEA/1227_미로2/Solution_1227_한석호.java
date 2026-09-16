import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    // 상, 하, 좌, 우 이동을 위한 방향 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int SIZE = 100;

    // 위치 좌표 클래스
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 10개의 테스트 케이스
        for (int tc = 1; tc <= 10; tc++) {
            // 테스트케이스 번호 입력 (문제 조건에 따라 첫 줄에 TC 번호가 들어옴)
            String tcNum = br.readLine();
            if (tcNum == null) break;

            int[][] maze = new int[SIZE][SIZE];
            int startX = -1, startY = -1;

            // 100x100 미로 정보 입력받기
            for (int i = 0; i < SIZE; i++) {
                String line = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    maze[i][j] = line.charAt(j) - '0';
                    // 출발점 위치 기록
                    if (maze[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            // BFS를 수행하여 도달 가능 여부 확인 (1: 가능, 0: 불가능)
            int result = bfs(maze, startX, startY);

            // 결과 출력
            System.out.println("#" + tcNum + " " + result);
        }
    }

    private static int bfs(int[][] maze, int startX, int startY) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[SIZE][SIZE];

        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 상, 하, 좌, 우 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                // 미로 범위 체크
                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                    // 도착점(3)을 만난 경우 -> 도달 성공(1 반환)
                    if (maze[nx][ny] == 3) {
                        return 1;
                    }

                    // 지나갈 수 있는 길(0)이고 방문한 적 없는 경우
                    if (maze[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }

        // 큐가 빌 때까지 도착점에 다다르지 못한 경우 -> 도달 불가(0 반환)
        return 0;
    }
}