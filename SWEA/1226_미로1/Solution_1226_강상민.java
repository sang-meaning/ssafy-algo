import java.util.*;
import java.io.*;


public class Solution_1226_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};

    public static void main(String[] args) throws IOException {
        T = 10;

        for (int t=1; t<=T; t++) {
            int trash = Integer.parseInt(br.readLine());

            char[][] board = new char[16][16];
            int[][] dist = new int[16][16];
            int x=-1;
            int y=-1; // 도착점

            Deque<int[]> q = new ArrayDeque<>();


            for (int i=0; i<16; i++) {
                String s = br.readLine();

                for (int j=0; j<16; j++) {
                    dist[i][j] = -1; // 미방문
                    board[i][j] = s.charAt(j);

                    if (board[i][j] == '2') {
                        dist[i][j] = 0;
                        q.add(new int[] {i,j}); // 시작점
                    }

                    if (board[i][j] == '3') {
                        x = i;
                        y = j;
                    }
                }
            }

            // 세팅 완료

            while(!q.isEmpty()) {
                int[] cur = q.poll();

                for (int dir=0; dir<4; dir++) {
                    int nx = cur[0]+dx[dir];
                    int ny = cur[1]+dy[dir];

                    if (nx<0||ny<0||nx>=16||ny>=16) continue;
                    if (board[nx][ny] == '1') continue;
                    if (dist[nx][ny] != -1) continue;

                    q.add(new int[] {nx,ny});
                    dist[nx][ny] = dist[cur[0]][cur[1]]+1;
                }


            }

            sb.append("#"+t+" ");

            if (dist[x][y] == -1) sb.append(0);
            else sb.append(1);

            sb.append("\n");

        }
        System.out.println(sb);
        
    }
    
}
