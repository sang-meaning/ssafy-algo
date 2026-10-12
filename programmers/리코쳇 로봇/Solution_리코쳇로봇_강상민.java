import java.util.*;

class Solution {
    static int[][] dist;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    
    public int solution(String[] board) {        
        
        // x,y 위치일 때, x + dx[dir], y + dy[dir] 이 OOB 거나 D일 때 dist 추가
        
        Deque<int[]> q = new ArrayDeque<>();
        int n=board.length;
        int m=board[0].length();
        
        dist = new int[n][m];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                dist[i][j] = -1; // 미방문
                if (board[i].charAt(j) == 'R') {
                    q.add(new int[] {i,j,0});
                    dist[i][j] = 0;
                }
            }
        }
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x=cur[0];
            int y=cur[1];
            int cnt=cur[2];
            
            // 종료조건
            if (board[x].charAt(y) == 'G') {
                return cnt;
            }
            
            for (int dir=0; dir<4; dir++) {
                int nx = x;
                int ny = y;
                
                while(true) {
                    int nxtX = nx+dx[dir];
                    int nxtY = ny+dy[dir];
                    
                    if (nxtX<0||nxtY<0||nxtX>=n||nxtY>=m||board[nxtX].charAt(nxtY)=='D') {
                        break; // 멈춤
                    }
                    
                    // 앞으로 가기
                    nx = nxtX;
                    ny = nxtY;
                
                }
                
                if (dist[nx][ny] == -1) { // 처음 가보는 곳
                    q.add(new int[] {nx,ny,cnt+1});
                    dist[nx][ny] = cnt+1;
                }
            }
        }
        
        
        
        
        return -1;
    }
}