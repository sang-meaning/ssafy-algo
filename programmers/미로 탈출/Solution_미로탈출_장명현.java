import java.util.ArrayDeque;

class Solution {
	public int solution(String[] maps) {
		int N = maps.length;
		int M = maps[0].length();
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		int[][] arr = new int[N][M];
		
		int sx = 0, sy = 0;
		int lx = 0, ly = 0;
		int ex = 0, ey = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (maps[i].charAt(j) == 'X') arr[i][j] = 0;
				else {
					arr[i][j] = 1;
					if (maps[i].charAt(j) == 'S') { sx = i; sy = j; }
					if (maps[i].charAt(j) == 'L') { lx = i; ly = j; }
					if (maps[i].charAt(j) == 'E') { ex = i; ey = j; }
				}
			}
		}
		
		int answer = 0;
		int[][] visited = new int[N][M];
		ArrayDeque <int[]> q = new ArrayDeque<>();
		
		visited[sx][sy] = 1;
		q.add(new int[] {sx, sy, 0});

		boolean br = false;
		while (!q.isEmpty()) {
			int[] f = q.poll();
			int x = f[0];
			int y = f[1];
			int c = f[2];
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (visited[nx][ny] == 1) continue;
				if (arr[nx][ny] == 0) continue;
				if (nx == lx && ny == ly) {
					answer = c + 1;
					br = true;
					break;
				}
				
				visited[nx][ny] = 1;
				q.add(new int[] {nx, ny, c+1});
			}
			if (br) break;
		}
		
		if (!br) return -1;
		
		visited = new int[N][M];
		q = new ArrayDeque<>();
		
		visited[lx][ly] = 1;
		q.add(new int[] {lx, ly, 0});

		br = false;
		while (!q.isEmpty()) {
			int[] f = q.poll();
			int x = f[0];
			int y = f[1];
			int c = f[2];
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (visited[nx][ny] == 1) continue;
				if (arr[nx][ny] == 0) continue;
				if (nx == ex && ny == ey) {
					answer += c + 1;
					br = true;
					break;
				}
				
				visited[nx][ny] = 1;
				q.add(new int[] {nx, ny, c+1});
			}
			if (br) break;
		}
		
		if (!br) return -1;
		
		return answer;
	}
}