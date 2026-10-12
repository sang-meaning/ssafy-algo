
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
    	int[] dx = {1, 0, -1, 0};
    	int[] dy = {0, 1, 0, -1};
        
    	int N = maps.length;
    	int M = maps[0].length;
    	int[][] cnt = new int[N][M];

    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {0, 0});
    	cnt[0][0] = 1;
    	
    	boolean br = false;
    	while (!q.isEmpty()) {
    		int[] f = q.poll();
    		int x = f[0];
    		int y = f[1];
    		
    		for (int d=0; d<4; d++) {
    			int nx = x + dx[d];
    			int ny = y + dy[d];
    			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
    			if (maps[nx][ny] == 0) continue;
    			if (cnt[nx][ny] > 0) continue;

    			cnt[nx][ny] = cnt[x][y] + 1;
    			if (nx == N-1 && ny == M-1) {
    				br = true;
    				break;
    			}
    			q.add(new int[] {nx, ny});
    		}
    		
    		if (br) break;
    	}

      return cnt[N-1][M-1] == 0 ? -1 : cnt[N-1][M-1];
    }
}