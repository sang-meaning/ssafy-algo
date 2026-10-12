import java.io.*;
import java.util.*;

public class Solution_1227_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static char[][] map = new char[100][100];
    static boolean[][] visited = new boolean[100][100];

    static int ans;

    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();

            Deque<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < 100; i++) {
                String s = br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = s.charAt(j);
                    visited[i][j] = false;

                    if (map[i][j] == '2') {
                        visited[i][j] = true;
                        q.add(new int[] { i, j });
                    }
                }
            }

            ans = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    if (map[nx][ny] == '1')
                        continue;

                    if (map[nx][ny] == '3') {
                        ans = 1;
                        q.clear();
                        break;
                    }

                    visited[nx][ny] = true;
                    q.add(new int[] { nx, ny });
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
