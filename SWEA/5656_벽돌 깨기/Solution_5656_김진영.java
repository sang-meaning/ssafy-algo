package swea;

import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T,B,N,M;
	static int[][] map;
	static int[] arr;
	static Deque<int[]> q;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int result;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			st = new StringTokenizer(br.readLine());
			
			B = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			q = new ArrayDeque<>();
			map = new int[N][M];
			arr = new int[B];
			result = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0);
			
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(result)
			.append("\n");

		}
		System.out.print(sb);
		
	}
	private static void dfs(int depth) {
		
		
		
		if(depth == B) {
			int count = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] > 0) {
						count++;
					}
				}
			}
			result = Math.min(result, count);
			
			return;
		}
		
		
		for(int map_y=0;map_y<M;map_y++) {
			
			boolean map_x_flag = true;
			int map_x = 0;
			for(map_x=0;map_x<N;map_x++) {
				if(map[map_x][map_y] != 0) {
					map_x_flag = false;
					break;
				}
			}
			
			if(map_x_flag) {
				dfs(depth+1);
				continue;
			}

			//맵 백업	
			int[][] map_back = new int[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					map_back[i][j] = map[i][j];
				}
			}

			ball_bomb(map_x,map_y,map[map_x][map_y]);

			//공 내리기
			ball_down();
			
			arr[depth] = map_y;
			dfs(depth+1);
			
			//맵 복구(백트래킹)
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					map[i][j] = map_back[i][j];
				}
			}
		}
		
		
	}
	
	private static void ball_bomb(int ball_x,int ball_y,int ball_p) {
		
		q.add(new int[] {ball_x,ball_y,map[ball_x][ball_y]});
		
		while(!q.isEmpty()) {
			
			int temp[] = q.poll();
			
			int cx = temp[0];
			int cy = temp[1];
			int ball_power = temp[2];

			for(int area=1;area<ball_power;area++) {
				for(int dir=0;dir<4;dir++) {
					int nx = cx + (dx[dir]*area);
					int ny = cy + (dy[dir]*area);
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
						continue;
					}
					
					if(map[nx][ny] == 0) {
						continue;
					}
					
					q.add(new int[] {nx,ny,map[nx][ny]});
					map[nx][ny] = 0;
				}
			}
			map[cx][cy] = 0;
		}
	}
	
	private static void ball_down() { 
		
		for(int y=0;y<M;y++) {
			int bottem = N-1;
			for(int x=N-1;x>=0;x--) {
				if(map[x][y] != 0) {
					int temp = map[x][y];
					map[x][y] = 0;
					map[bottem][y] = temp;
					
					bottem--;
				}
			}
		}
		
	}

	
	private static void ball_down2() {

	    for(int col = 0; col < M; col++) {

	        int bottom = N - 1;

	        for(int row = N - 1; row >= 0; row--) {

	            if(map[row][col] != 0) {

	                int temp = map[row][col];

	                map[row][col] = 0;
	                map[bottom][col] = temp;

	                bottom--;
	            }
	        }
	    }
	}
}

