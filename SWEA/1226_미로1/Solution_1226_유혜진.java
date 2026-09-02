import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1226_유혜진 {
    // 상, 하, 좌, 우 이동을 위한 방향 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 10개의 테스트 케이스
        for (int tc = 1; tc <= 10; tc++) {
            int tcNum = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 번호 읽기

            int[][] maze = new int[16][16];
            int startX = -1, startY = -1;

            // 16x16 미로 입력받기
            for (int i = 0; i < 16; i++) {
                String line = br.readLine();
                for (int j = 0; j < 16; j++) {
                    maze[i][j] = line.charAt(j) - '0'; // 문자를 숫자로 변환 ('0' -> 0)
                    if (maze[i][j] == 2) {
                        startX = i;
                        startY = j; // 시작점(2)의 위치 기억
                    }
                }
            }

            // BFS 탐색으로 도달 가능 여부 확인
            int answer = bfs(maze, startX, startY) ? 1 : 0;

            System.out.println("#" + tcNum + " " + answer);
        }
    }

    // BFS (너비 우선 탐색) 메서드
    public static boolean bfs(int[][] maze, int startX, int startY) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[16][16];

        // 시작점 큐에 넣고 방문 처리
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 도착점(3)에 도달했으면 성공!
            if (maze[x][y] == 3) {
                return true;
            }

            // 상, 하, 좌, 우 4방향 확인
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 미로 범위 안에 있고, 벽(1)이 아니며, 아직 방문하지 않은 곳이라면
                if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16) {
                    if (maze[nx][ny] != 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        // 큐가 비어있을 때까지 3을 못 만났다면 도달 불가능
        return false;
    }
}