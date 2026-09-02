import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution
{
	public static void main(String args[]) throws Exception
	{
		 Scanner sc = new Scanner(System.in);


			for(int T = 0; T < 10; T++)
			{
                int test_case = sc.nextInt();
                
				int[][] map = new int[16][16];
				boolean[][] visited = new boolean[16][16];
				Queue<int[]> queue = new ArrayDeque<>();
				int[] dx = {1,-1,0,0};
				int[] dy = {0,0,-1,1};
				int answer = 0;
				  
				
				for(int i = 0; i < 16; i++) {
					String line = sc.next();
					for(int j = 0; j < 16; j++) {
						map[i][j] = line.charAt(j) - '0';
						
						if(map[i][j] == 2) {
							queue.offer(new int[] {i,j});
							visited[i][j] =  true;
						}
					}
				}				
				
				while(!queue.isEmpty() && answer == 0) {
					int[] now = queue.poll();
					
					int x = now[0];
					int y = now[1];
					
					
					
					for(int i = 0; i < 4; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						
						if(nx < 0 || ny < 0 || nx > 15 || ny > 15|| visited[nx][ny] == true || map[nx][ny] == 1) {
							continue;
						}

						if(map[nx][ny] == 3) {
							answer = 1;
							break;
						}
						
						visited[nx][ny] = true;
						queue.offer(new int[] {nx,ny});
		
				}
			}
				System.out.println("#" + test_case + " " + answer);
		}
	}
}