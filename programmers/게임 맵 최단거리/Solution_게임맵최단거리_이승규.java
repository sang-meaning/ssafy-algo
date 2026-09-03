import java.util.*;
class Solution { // bfs
    int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];

        int answer = bfs(maps,visited, 0, 0, n, m );
        if (answer == -1) return -1;
        return answer+1;
    }
    
    public int bfs(int[][] maps, boolean[][] visited, int nowx, int nowy, int n, int m) {
        visited[nowx][nowy] = true; //visited 처리
        Queue<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[] {nowx, nowy, 0});
        
        while(!q.isEmpty()) {
            int[] polled = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = polled[0] + dir[i][0];
                int ny = polled[1] + dir[i][1];
                
                if(nx == n-1 && ny == m-1) return polled[2] + 1;
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || maps[nx][ny] == 0) 
                    continue;
                else {
                    if(visited[nx][ny]) continue;
                    else {
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx,ny, polled[2] + 1});
                    }
                }
            }
            
        }
        
        return -1;
    }
}