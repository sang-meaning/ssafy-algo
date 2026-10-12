import java.util.*;
import java.io.*;

/*
ai 피드백 o
dfs 시작하고 core를 선택할 때 for (int i=0; i<arr.size(); i++) 과 
방문 여부로 선택했하였고, 그 core를 선택하냐 안하냐 2개로 나누어 dfs 진행했는데,
 "내부 dir 탐색에도 for문이 있어서" 꼬임

 -> dfs 매개변수에 depth 의미하는 i 추가하고 arr.get(i)로 core 선택하도록 수정함


*/

public class Solution_1767_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int T;
    static int N;
    static int resultCore = 0; // resultCore가 최대일 때 result의 최소 구하기
    static int resultLine = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            int[][] board = new int[N][N];

            ArrayList<int[]> arr = new ArrayList<>();
            resultCore = 0;
            resultLine = Integer.MAX_VALUE; // 답을 찾았을 때 전선 최소수

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1)
                        arr.add(new int[] { i, j });
                }
            }

            boolean[] visited = new boolean[arr.size()];

            // core 접근하며 "남 동 북 서" 순으로 전선 깔며 깊이 탐색

            dfs(0, 0, 0, board, visited, arr);

            System.out.println("#"+t+" "+resultLine);

        }
        // tc 끝

      

    }

    // i는 depth, core는 찾은 코어수, line은 전선 수
    static void dfs(int i, int core, int line, int[][] board, boolean[] visited, ArrayList<int[]> arr) {

        // arr 끝까지 다 봤을 때
        if (i == arr.size()) {
            // 선택한 코어 최대값 갱신
            if (core > resultCore) {
                resultCore = core;
                resultLine = line;
            } else if (core == resultCore) {
                // 코어 최대값인데, 전선 최솟값 갱신
                if (resultLine > line)
                    resultLine = line;
            }
            return;

        }

        int curX = arr.get(i)[0];
        int curY = arr.get(i)[1];

        // arr[i]를 선택하거나 선택하지 않거나로 나누어 DFS 

        // 1) arr[i] 선택
        if (curX == 0 || curY == 0 || curX == N - 1 || curY == N - 1) {
            // 가장자리는 선택하고 다음 깊이 탐색
            visited[i] = true;
            dfs(i + 1, core + 1, line, board, visited, arr);

        } else {
            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                   
                int addLine = 0;
                boolean connectable = true; // 전선 깔 수 있는지

                while (true) {
                    addLine++;

                    
                    // 전선이거나 코어 만나면
                    if (board[nx][ny] == 1 || board[nx][ny] == 2) {
                        connectable = false;
                        break;
                    }

        

                    // 가장자리
                    if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
                        connectable = true;
                        break;
                    }

                    nx += dx[dir];
                    ny += dy[dir];

                }

                if (!connectable) continue;
                    

                // 전선 깔기
                nx = curX;
                ny = curY;

                for (int a = 0; a < addLine; a++) {

                    nx += dx[dir];
                    ny += dy[dir];
                    board[nx][ny] = 2;

                }

                visited[i] = true;

                dfs(i + 1, core + 1, line + addLine, board, visited, arr);

                nx = curX; // 전선 깐 것 백트래킹
                ny = curY;

                for (int a = 0; a < addLine; a++) {

                    nx += dx[dir];
                    ny += dy[dir];
                    board[nx][ny] = 0;

                }
            }
        }

        visited[i] = false; // visit 백트래킹 - 없어도 됨

        // 2) arr[i] 선택 안하고 DFS
        visited[i] = true; // 의미상 방문했다고 필요함
        dfs(i + 1, core, line, board, visited, arr);
        visited[i] = false;

    }

}
