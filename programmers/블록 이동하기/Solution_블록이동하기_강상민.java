import java.util.*;

// 도파민 터지네

class Solution {
    static int[] dx={0,1}; // 동 남, 로봇 90도 회전이동에 사용
    static int[] dy={1,0};
    
    static int[] ddx={0,1,0,-1}; // 로봇 dir 유지한 채 4방향 이동에 사용
    static int[] ddy={1,0,-1,0};
    
    // dist[x][y][dir] : x,y를 기준으로, dir 방향으로 한칸 뻗어있는 로봇이 x,y 까지 도착하기 위한 최소 시간
    // BFS q.add() 할 때, 로봇이 가로로 놓여졌으면, 왼쪽 좌표 기준,, 로봇이 세로로 놓여졌으면 위쪽 좌표 기준으로 넣었다
    static int[][][] dist;
    
    public int solution(int[][] board) { 
      
        int n = board.length;
        
        dist = new int[n][n][2];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                for (int k=0; k<2; k++) {
                    dist[i][j][k] = -1;
                }
            }
        }
        
        dist[0][0][0] = 0;
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0,0,0}); //x,y,dir
        
        while(!q.isEmpty()) {
            int[] cur = q.poll(); // 갈 수 있는 칸만 큐에 넣었음이 보장, x,y는 로봇의 왼쪽 혹은 위쪽이다
            int curD = cur[2];
            
            int curX1 = cur[0]; // 로봇의 왼쪽 혹은 위쪽 좌표
            int curY1 = cur[1];
            
            int curX2 = cur[0]+dx[curD];
            int curY2 = cur[1]+dy[curD];
        
            
            
            // 이대로 상하좌우 이동하는 것과 회전하는 것 모두 갈 수 있는지 판단하고 큐에 넣기
            
            // 로봇의 두 좌표에 대해 모두 판단해야한다
            // 1) cur[0], cur[1] 과 cur[0]+dx[cur[2]], cur[1]+dy[cur[2]]; 에 대해 4방향 탐색
            
            // 2-1) dir 가 0 인 경우, 로봇의 왼쪽을 축으로 오른쪽을 시계방향으로 이동 or 반시계방향으로 이동, dir 갱신해서 이동 판단
            //                        로봇의 오른쪽을 축으로...
            // 2-2) dir 가 1 인 경우, 로봇의 위쪽을 축으로 아래쪽을 시계 or 반시계 이동, dir 갱신...
            //                        로봇의 아래쪽을 축으로 위쪽을 ...
            
            
            // 1) 구현
            // 회전 없이 4방향 탐색
            for (int dir=0; dir<4; dir++) {
                int nx1 = curX1+ddx[dir];
                int ny1 = curY1+ddy[dir];
                
                int nx2 = curX2+ddx[dir];
                int ny2 = curY2+ddy[dir];
                
                if (nx1<0||ny1<0||nx1>=n||ny1>=n) continue;
                if (board[nx1][ny1] == 1) continue;
                
                if (nx2<0||ny2<0||nx2>=n||ny2>=n) continue;
                if (board[nx2][ny2] == 1) continue;
                
                // 로봇 두 점 중 기준점만 미방문이면 됨
                if (dist[nx1][ny1][curD] != -1) continue;
                
                // 로봇의 두 좌표 모두 이동 가능, curD 유지, 기준점에 대한 dist 갱신
                dist[nx1][ny1][curD] = dist[curX1][curY1][curD]+1;
                // 로봇이 가로든 세로든 nx1,ny1 이 왼쪽 or 위쪽 가리킴
                q.add(new int[] {nx1, ny1, curD});
            }
            
            // 2-1) 구현
            // 로봇이 가로로 놓여있을 때 회전하는 4가지 경우의 수
            if (curD == 0) {
                // 2-1) curD 가 0 인 경우, 로봇의 왼쪽을 축으로 오른쪽을 시계방향으로 이동 or 반시계방향으로 이동, dir 갱신해서 이동 판단
                //                        로봇의 오른쪽을 축으로...
                
                // 1. curX1, curY1 을 축으로 curX2, curY2 를 위로 돌리는 것과 아래로 돌리는 것
                // 위로 올림
                int tx = curX2-1;
                int ty = curY2-1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx][ty+1] == 0) {
                        if (dist[tx][ty][1] == -1) {
                            // curX1, curX2 와 tx, ty 와, nxtDir = 1 이 됨
                            dist[tx][ty][1] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {tx,ty,1});
                        }
                        
                        
                    }
                }
                
                // 아래로 내림
                tx = curX2+1;
                ty = curY2-1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx][ty+1] == 0) {
                        if (dist[curX1][curY1][1] == -1) {
                           
                            dist[curX1][curY1][1] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {curX1,curY1,1});
                        }
                        
                        
                    }
                }
                
                // 2. curX2, curY2 을 축으로 curX1, curY1 를 위로 돌리는 것과 아래로 돌리는 것
                
                tx = curX1-1;
                ty = curY1+1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx][ty-1] == 0) {
                        if (dist[tx][ty][1] == -1) {
                            
                            dist[tx][ty][1] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {tx,ty,1});
                        }
                        
                        
                    }
                }
                
                tx = curX1+1;
                ty = curY1+1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx][ty-1] == 0) {
                        if (dist[curX2][curY2][1] == -1) {
                            
                            dist[curX2][curY2][1] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {curX2,curY2,1});
                        }
                        
                        
                    }
                }
                
            }
            
            // 2-1) 완료
            
            // 2-2) 구현
            // 로봇이 세로로 놓여있을 때 회전하는 4가지 경우의 수
            if (curD == 1) {
                // 위를 축으로
                int tx = curX2-1;
                int ty = curY2+1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx+1][ty] == 0) {
                        if (dist[curX1][curY1][0] == -1) {
                            dist[curX1][curY1][0] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {curX1,curY1,0});
                        }
                        
                        
                    }
                }
                
                tx = curX2-1;
                ty = curY2-1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx+1][ty] == 0) {
                        if (dist[tx][ty][0] == -1) {
                            dist[tx][ty][0] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {tx,ty,0});
                        }
                        
                        
                    }
                }
                
                // 아래를 축으로
                tx = curX1+1;
                ty = curY1+1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx-1][ty] == 0) {
                        if (dist[curX2][curY2][0] == -1) {
                            dist[curX2][curY2][0] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {curX2,curY2,0});
                        }
                        
                        
                    }
                }
                tx = curX1+1;
                ty = curY1-1;
                // oob
                if(tx>=0 && ty>=0 && tx<n && ty<n) {
                    // 경로에 1 없는지
                    if (board[tx][ty] == 0 && board[tx-1][ty] == 0) {
                        if (dist[tx][ty][0] == -1) {
                            dist[tx][ty][0] = dist[curX1][curY1][curD]+1;
                            q.add(new int[] {tx,ty,0});
                        }
                        
                        
                    }
                }
            }
            
            
        }
        
        int aa = dist[n-1][n-2][0];
        int bb = dist[n-2][n-1][1];
        
        // aa나 bb -1이 아니어야 됨
        
        if (aa == -1) aa = Integer.MAX_VALUE;
        if (bb == -1) bb = Integer.MAX_VALUE;
        
        return Math.min(aa,bb);
        
    }
}



/*
dist[x][y][dir] : 로봇이 x,y 위치에 있되, dir 방향으로 1칸 더 차지하고 있는 상황이다
dir 0 1 : 동 남
*/
