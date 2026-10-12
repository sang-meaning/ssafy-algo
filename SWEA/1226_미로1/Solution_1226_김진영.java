import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int map[][];
	static int visited[][];
	static int T;
	static int result = 0;
	static Deque<int[]> q;
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		
		for(int test_case=1;test_case<=10;test_case++) {
			
			q = new ArrayDeque<>();
			T = Integer.parseInt(br.readLine());
			
			map = new int[16][16];
			visited = new int[16][16];
			result = 0;
			
			for(int i=0;i<16;i++) {
				String temp = br.readLine();
				for(int j=0;j<16;j++) {
					map[i][j] = temp.charAt(j) - '0';
					if(map[i][j] == 2) {
						visited[i][j] = 1;
						q.add(new int[] {i,j});
					}
				}
			}

			while(!q.isEmpty()) {
				int temp[] = q.poll();
				int cx = temp[0];
				int cy = temp[1];
				
				for(int dir=0;dir<4;dir++) {
					int nx = cx + dx[dir];
					int ny = cy + dy[dir];

					if(nx < 0 || ny < 0 || nx >= 16 || ny >= 16) {
						continue;
					}
					
					if(map[nx][ny] == 3) {
						result = 1;
						break;
					}
					if(map[nx][ny] != 0) {
						continue;
					}
					
					if(visited[nx][ny] == 1) {
						continue;
					}
					
					visited[nx][ny] = 1;
					q.add(new int[] {nx,ny});
				}
			}		
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(result)
			.append("\n");
		}
		System.out.print(sb);
		
	}
    
}