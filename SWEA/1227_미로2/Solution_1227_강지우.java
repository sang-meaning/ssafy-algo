import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static final int SIZE = 100;
    static int[][] maze;
    static boolean[][] visited;
    
    // 상, 하, 좌, 우 4방향 탐색용 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스는 항상 10개로 주어집니다.
        for (int t = 1; t <= 10; t++) {
            // 테스트 케이스 번호 읽기 (사용하지 않더라도 버퍼를 비워야 함)
            int tc = Integer.parseInt(br.readLine());
            
            maze = new int[SIZE][SIZE];
            visited = new boolean[SIZE][SIZE];

            int startX = -1;
            int startY = -1;

            // 100x100 미로 정보 입력
            for (int i = 0; i < SIZE; i++) {
                String line = br.readLine();
                for (int j = 0; j < SIZE; j++) {
                    maze[i][j] = line.charAt(j) - '0';
                    
                    // 출발점(2) 좌표 저장
                    if (maze[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            // BFS 탐색 후 결과 반환 (도달 가능: 1, 불가능: 0)
            int result = bfs(startX, startY);
            System.out.println("#" + tc + " " + result);
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            // 현재 위치가 도착점(3)이라면 1 반환 후 종료
            if (maze[cx][cy] == 3) {
                return 1;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 미로 범위를 벗어나지 않는지 확인
                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                    // 방문하지 않았고, 벽(1)이 아닌 경우 큐에 추가 (길(0)이거나 도착점(3)인 경우 이동 가능)
                    if (!visited[nx][ny] && maze[nx][ny] != 1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        
        // 큐가 다 빌 때까지 3을 만나지 못했다면 도달할 수 없음을 의미
        return 0;
    }
}