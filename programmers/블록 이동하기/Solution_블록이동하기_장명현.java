import java.util.ArrayDeque;

class Solution {
	public int solution(int[][] board) {
		int N = board.length;
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		boolean[][][] visited = new boolean[N][N][2];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {0, 0, 0, 0});
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			int[] f = q.poll();
			int x = f[0];
			int y = f[1];
			int dir = f[2];
			int cnt = f[3];
			
			int x2 = dir == 0 ? x : x+1;
			int y2 = dir == 0 ? y+1 : y;
			if ((x == N-1 && y == N-1) || (x2 == N-1 && y2 == N-1)) {
				return cnt;
			}
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nx2 = x2 + dx[d];
				int ny2 = y2 + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= N) continue;
				if (board[nx][ny] == 1 || board[nx2][ny2] == 1) continue;
				if (visited[nx][ny][dir]) continue;
				
				visited[nx][ny][dir] = true;
				q.add(new int[] {nx, ny, dir, cnt+1});
			}
			
			if (dir == 0) {
				for (int d=-1; d<=1; d+=2) {
					int nx = x + d;
					if (nx < 0 || nx >= N) continue;
					if (board[nx][y] == 1 || board[nx][y+1] == 1) continue;
					
					int tx = Math.min(x, nx);
					if (!visited[tx][y][1]) {
						visited[tx][y][1] = true;
						q.add(new int[] {tx, y, 1, cnt+1});
					}
					if (!visited[tx][y+1][1]) {
						visited[tx][y+1][1] = true;
						q.add(new int[] {tx, y+1, 1, cnt+1});
					}
				}
			}
			else {
				for (int d=-1; d<=1; d+=2) {
					int ny = y + d;
					if (ny < 0 || ny >= N) continue;
					if (board[x][ny] == 1 || board[x+1][ny] == 1) continue;
					
					int ty = Math.min(y, ny);
					if (!visited[x][ty][0]) {
						visited[x][ty][0] = true;
						q.add(new int[] {x, ty, 0, cnt+1});
					}
					if (!visited[x+1][ty][0]) {
						visited[x+1][ty][0] = true;
						q.add(new int[] {x+1, ty, 0, cnt+1});
					}
				}
			}
		}
		
		return 0;
	}
}
