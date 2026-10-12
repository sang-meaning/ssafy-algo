import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int[][] map;
    static boolean[][] visited;
    static boolean isPossible;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int tc = sc.nextInt();
			
			map = new int[16][16];
			visited = new boolean[16][16];
			isPossible = false;
			
			int startX = 0, startY = 0;

			for(int i = 0; i < 16; i++) {
				String line = sc.next();
				for(int j = 0; j < 16; j++) {
					map[i][j] = line.charAt(j) - '0';
					if(map[i][j] == 2) {
						startX = i;
						startY = j;
					}
				}
			}
			
			dfs(startX, startY);
			
			int result = isPossible ? 1 : 0;
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	static void dfs(int x, int y) {
		if(map[x][y] == 3) {
			isPossible = true;
			return;
		}
		
		visited[x][y] = true;
		

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && nx < 16 && ny >= 0 && ny < 16) {
				if(!visited[nx][ny] && map[nx][ny] != 1) {
					dfs(nx, ny);
					if(isPossible) return;
				}
			}
		}
	}
}