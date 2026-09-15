
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,result;
	static int[][] map;
	static int[][] visisted;
	static Deque<int[]> q;
	static int[] dx = {0,0,1,1,1,-1,-1,-1};
	static int[] dy = {1,-1,1,-1,0,1,-1,0};
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visisted = new int[N][N];
			result = 0;
			q = new ArrayDeque<>();
			
			for(int i=0;i<N;i++) {
				String temp = br.readLine();
				for(int j=0;j<N;j++) {
					if(temp.charAt(j) == '.') {
						map[i][j] = 0;
					}else {
						map[i][j] = 99;
						visisted[i][j] = 1;
					}
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int cx = i;
					int cy = j;
					
					int count = 0;
					if(map[i][j] == 99) {
						continue;
					}
					
					for(int dir=0;dir<8;dir++) {
						int nx = cx + dx[dir];
						int ny = cy + dy[dir];
						
						if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
							if(map[nx][ny] == 99) {
								count++;
							}
						}
					}
					map[i][j] = count;
				}
			}

			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == 0 && visisted[i][j] == 0){
						
						visisted[i][j] = 1;
						result++;
						q.add(new int[] {i,j});
						
						while(!q.isEmpty()) {
							int temp[] = q.poll();
							int cx = temp[0];
							int cy = temp[1];

							for(int dir=0;dir<8;dir++) {
								int nx = cx + dx[dir];
								int ny = cy + dy[dir];
								
								if(nx < 0 || ny < 0 || nx > N-1 || ny > N-1) {
									continue;
								}
								
								if(visisted[nx][ny] == 1) {
									continue;
								}
								
								visisted[nx][ny] = 1;
								if(map[nx][ny] == 0) {
									q.add(new int[] {nx,ny});
								}
								
							}
						}
					}
				}
			}

			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visisted[i][j] == 0) {
						result++;
					}
				}
			}

			sb.append("#"+tc+" "+result+"\n");
			
		}
		System.out.print(sb);
	}
}
