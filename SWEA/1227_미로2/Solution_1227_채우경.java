package samsung01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    // 상, 하, 좌, 우 이동을 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 10개의 테스트 케이스 처리
        for (int t = 1; t <= 10; t++) {
            // 테스트 케이스 번호 읽기
            String testCaseNum = br.readLine();
            
            int[][] maze = new int[100][100];
            int startX = -1, startY = -1;

            // 100x100 미로 데이터 입력 받아 배열 구성
            for (int i = 0; i < 100; i++) {
                String line = br.readLine();
                for (int j = 0; j < 100; j++) {
                    maze[i][j] = line.charAt(j) - '0';
                    // 출발점(2) 위치 저장
                    if (maze[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
            }

            // BFS 탐색을 통한 도달 가능 여부 판단 (1: 가능, 0: 불가능)
            int result = bfs(startX, startY, maze) ? 1 : 0;

            // 결과 출력
            System.out.println("#" + testCaseNum + " " + result);
        }
    }

    private static boolean bfs(int startX, int startY, int[][] maze) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[100][100];

        // 시작점 큐에 삽입 및 방문 처리
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 체크
                if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100) {
                    // 도착점(3)을 만난 경우 성공
                    if (maze[nx][ny] == 3) {
                        return true;
                    }

                    // 길(0)이고 아직 방문하지 않은 지점 탐색
                    if (maze[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        // 도착점에 도달하지 못한 경우
        return false;
    }
}