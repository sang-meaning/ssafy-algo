package swea;

import java.io.*;
import java.util.*;

public class Miro_1226 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {

        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = Integer.parseInt(br.readLine());
            char[][] map = new char[16][16];
            Deque<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[16][16];

            for (int i = 0; i < 16; i++) {
                String s = br.readLine();
                for (int j = 0; j < 16; j++) {
                    map[i][j] = s.charAt(j);

                    if (map[i][j] == '2') { // 2이면 출발지
                        q.add(new int[] { i, j });
                        visited[i][j] = true;
                    }
                }
            }

            int ans = 0; // 도달 가능 여부(0: 불가능, 1: 가능)

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    if (map[nx][ny] == '1')
                        continue;
                    if (map[nx][ny] == '3') { // 3이면 도착지
                        ans = 1; // 도착지에 도달 가능하므로 1
                        q.clear();
                        break;
                    }

                    visited[nx][ny] = true;
                    q.add(new int[] { nx, ny });
                }
            }

            sb.append("#")
                    .append(T)
                    .append(" ")
                    .append(ans)
                    .append("\n");

        }

        System.out.println(sb);
    }

}
