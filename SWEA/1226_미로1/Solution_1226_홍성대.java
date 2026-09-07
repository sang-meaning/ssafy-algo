import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map = new int[16][16];
    static boolean[][] visited = new boolean[16][16];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isPossible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            String line = br.readLine();
            if (line == null) break;
            int tcNum = Integer.parseInt(line.trim());

            int startX = -1, startY = -1;

            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    // br.read()는 글자 1개를 읽어서 정수(아스키코드)로 반환합니다.
                    // 거기서 '0'을 빼면 우리가 원하는 int 숫자(0, 1, 2, 3)가 됩니다.
                    map[i][j] = br.read() - '0';
                    visited[i][j] = false;

                    if (map[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }
                }
                // 줄바꿈 문자(\r, \n) 버리기 처리
                br.readLine(); 
            }

            isPossible = false;
            dfs(startX, startY);

            System.out.println("#" + tcNum + " " + (isPossible ? 1 : 0));
        }
    }

    static void dfs(int x, int y) {
        if (map[x][y] == 3) {
            isPossible = true;
            return;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16) {
                if (map[nx][ny] != 1 && !visited[nx][ny] && !isPossible) {
                    dfs(nx, ny);
                }
            }
        }
    }
}