import java.util.*;

/*
그림상 나중에 받는 직사각형이 이전 직사각형 위에 덮어버림
항상 적절히 직사각형이 겹친다

1. 직사각형을 board 에 놓으려고 한다, 직사각형의 둘레는 2, 내부는 1로 채운다
2. 놓으려는 직사각형 전체 좌표 탐색하는데, 이미 1 || 2 인 칸은 1로 채우고, 그렇지 않은 둘레는 2로 채운다

board == 2 만 이동 가능

시작점에 대해 상 하 좌 우 4 방향으로 탐색

근데 입출력 2번처럼 직사각형 변길이 1이면 4방향 탐색 잘못되는데 : 안쪽을 탐색함
: 모든 좌표를 2배 처리

*/

class Solution {
    static int[][] board;
    static int[][] dist;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        
        board = new int[102][102];
        dist = new int[102][102];
        
        for (int i=0; i<102; i++)
            for (int j=0; j<102; j++)
                dist[i][j] = -1;
        
        int len = rectangle.length;
        for (int i=0; i<len; i++) {
            int a=rectangle[i][0]*2;
            int b=rectangle[i][1]*2;
            int c=rectangle[i][2]*2;
            int d=rectangle[i][3]*2;
            
            
            for (int p=a; p<=c; p++) {
                for (int q=b; q<=d; q++) {
                    // 둘레면 2, 둘레 아니면 1 채울건데 board 값에 따라 또 다름
                    if (p==a || p==c || q==b || q==d) {
                        // 내부가 아니면 2로 채움
                        if (board[p][q] != 1) board[p][q] = 2;
                
                    } else {
                        board[p][q] = 1;
                    }
                    
                }
            }
            
            
        }
        
        // board 완료
        
        characterX *=2;
        characterY *= 2;
        itemX *=2;
        itemY *=2;
        
        Deque<int[]> q = new ArrayDeque<>();
        dist[characterX][characterY] = 0;
        q.add(new int[] {characterX, characterY});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int dir=0; dir<4; dir++) {
                int nx = cur[0]+dx[dir];
                int ny = cur[1]+dy[dir];
                
                if (nx<0||ny<0||nx>=102||ny>=102) continue;
                if (dist[nx][ny] != -1) continue;
                if (board[nx][ny] != 2) continue;
                dist[nx][ny] = dist[cur[0]][cur[1]]+1;
                q.add(new int[] {nx,ny});
                
                if (nx==itemX && ny==itemY) return dist[itemX][itemY]/2;
            }
        }
        
        return dist[itemX][itemY]/2;
        
        
        
    }
}