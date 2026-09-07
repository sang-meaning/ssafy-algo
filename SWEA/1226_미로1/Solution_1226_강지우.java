import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
	
	static int[][] maze;
	static boolean[][] visited;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static boolean found;
	
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
		
			int T = Integer.parseInt(br.readLine());
			
			maze = new int[16][16];
			visited = new boolean[16][16];
			
			int startX = 0;
			int startY = 0;
			
			for (int i=0; i<16; i++) {
				String line = br.readLine();
				
				for (int j=0; j<16; j++) {
					maze[i][j] = line.charAt(j) - '0';
					
					if (maze[i][j] == 2) {
						startX = i;
						startY = j;
					}
				}
			}
			
			found = false;
			
			dfs(startX, startY);
			
			int result = found ? 1 : 0;
			
			sb.append("#")
			.append(T)
			.append(" ")
			.append(result)
			.append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void dfs(int x, int y) {
		
		visited[x][y] = true;
		
		if (maze[x][y] == 3) {
			found = true;
			return;
		}
		
		for (int k=0; k<4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16) continue;
			if (maze[nx][ny] == 1) continue;
			if (visited[nx][ny]) continue;
			
			dfs(nx, ny);
			
			if (found) {
				return;
			}
		}
	}
}