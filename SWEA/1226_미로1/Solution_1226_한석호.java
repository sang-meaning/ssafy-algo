package samsung01;

import java.util.*;
import java.io.*;

public class Solution_1226_한석호 {
	static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 10개의 테스트 케이스
        for (int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 번호

            int[][] graph = new int[16][16];
            boolean[][] visited = new boolean[16][16];

            // 미로 지도 입력받기
            for (int i = 0; i < 16; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < 16; j++) {
                    graph[i][j] = line.charAt(j) - '0';
                }
            }

            // BFS를 위한 큐 생성 (int[] 배열 형태로 {x, y} 좌표 저장)
            Queue<int[]> queue = new ArrayDeque<>();
            
            // 시작점 (1, 1) 설정
            visited[1][1] = true;
            queue.offer(new int[]{1, 1});
            
            int res = 0;

            // BFS 탐색 시작
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 범위 검사
                    if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16) {
                        if (!visited[nx][ny]) {
                            // 목적지(3)에 도착한 경우
                            if (graph[nx][ny] == 3) {
                                res = 1;
                                break;
                            }

                            // 이동 가능한 길(0)인 경우
                            if (graph[nx][ny] == 0) {
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }
                }

                // 목적지를 찾았으면 외부 while 루프도 탈출
                if (res == 1) {
                    break;
                }
            }

            // 결과 출력
            System.out.println("#" + T + " " + res);
        }
    }
}
