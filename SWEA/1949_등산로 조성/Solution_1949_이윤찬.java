
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int K;
    static int[][] hikingMap;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited;
    static int max;
    static boolean used;
    static int length;
    static int answer;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());


            N = Integer.parseInt(st.nextToken());

            K = Integer.parseInt(st.nextToken());

            hikingMap = new int[N][N];
            answer= 0;
            visited = new boolean[N][N];
            max = 0;
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    hikingMap[y][x] = Integer.parseInt(st.nextToken());
                    max = Math.max(max,hikingMap[y][x]);
                }
            }
            used = false;


            //최대높이에서 시작
            for(int y = 0 ; y <N; y++) {
                for(int x = 0; x< N; x++) {
                    if(hikingMap[y][x]== max) {
                        // 가장 높은 봉우리 x,y
                        visited[y][x]= true;
                        dfs(x,y,1,used);
                        visited[y][x]= false;
                    }

                }
            }
            // dfs(x좌표, y좌표, 이동거리, used 내용


            sb.append("#").append(tc).append(" ").append(answer);


            System.out.println(sb);
        }


    }
    public  static void dfs(int x, int y, int length ,boolean use) {
        // 첫 행선지 방문처리
        answer = Math.max(answer, length);
        // 4방향 탐색

        for (int d = 0; d < 4; d++) {
            int moveX = x + dx[d];
            int moveY = y + dy[d];

            // 경계면 확인
            if (moveX < 0 || moveY < 0 || moveX >= N || moveY >= N) continue;

            // 방문했던곳인지 체크
            if (visited[moveY][moveX]) continue;

            // dfs 실행 (일반)
            if (hikingMap[y][x] > hikingMap[moveY][moveX]) {
                visited[moveY][moveX] = true;
                dfs(moveX, moveY, length + 1, use);// 산 깍기 사용.ver
                visited[moveY][moveX] = false;
            }
            else if (hikingMap[y][x] > hikingMap[moveY][moveX] - K) {

                if (!use) {

                    int original = hikingMap[moveY][moveX];

                    hikingMap[moveY][moveX] = hikingMap[y][x] - 1;
                    visited[moveY][moveX] = true;
                    dfs(moveX, moveY, length + 1, true);
                    visited[moveY][moveX] = false;
                    hikingMap[moveY][moveX] = original;
                } else {
                    continue;
                }
            }

        }
    }

}