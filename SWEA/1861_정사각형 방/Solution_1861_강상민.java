import java.util.*;
import java.io.*;

// 1000 * 1000 
// dfs 4방향하기 위험한데 1차이 나는 곳 계속 나오기 힘드니 통과 되려나
// bfs 였다면 1000*1000 * 1000 = 10억?


public class Solution_1861_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1,0,-1,0};
    static int[] dy= {0,1,0,-1};
    static int T;
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int len = 0; // 방의 개수 최댓값
    static int boardNum = Integer.MAX_VALUE; // len 일 때의 board값 최소

    

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            len = 0;
            boardNum = Integer.MAX_VALUE;

            board = new int[N][N];
            visited = new boolean[N][N];

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    // 모든 점을 시작점으로 DFS?
                    int curV = board[i][j];
                    
                    visited[i][j] = true;
                    dfs(1, i,j, board[i][j]);
                    visited[i][j] = false;
                }
            }

            sb.append("#"+t+" "+boardNum+" "+len).append("\n");

        }

        // tc 종료
        System.out.print(sb);
    }

    // depth:탐색한 개수
    static void dfs(int depth, int curX, int curY, int start) {
        if (len < depth) { // 더 많이 탐색
            len = depth;
            boardNum = start;
           
        } else if (len == depth) { // 같으면
            // boardNum은 최소값 갱신
            if (boardNum > start)
                boardNum = start;

        }

        for (int dir=0; dir<4; dir++) {
            int nx = curX+dx[dir];
            int ny = curY+dy[dir];

            if (nx<0||ny<0||nx>=N||ny>=N) continue;
            if (visited[nx][ny] == true) continue;

            int nv = board[nx][ny];
            
            if (nv != board[curX][curY]+1) continue;

            // 갈 수 있는 곳
            visited[nx][ny] = true;
            dfs(depth+1, nx,ny,start);
            visited[nx][ny] = false;

        }

    }
    
}
