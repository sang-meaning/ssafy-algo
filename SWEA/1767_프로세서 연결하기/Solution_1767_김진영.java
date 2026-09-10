import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N;
	static int[][] map;
	static int result;
	static int result_core;
	static int result_connect;
	static int core_count;
	static int[] arr;
	static int[][] cores;
	
	static int[] dx = {1,-1,0,0};// 아래, 위, 오른쪽, 왼쪽
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			result = Integer.MIN_VALUE;
			result_core = 0;
			core_count=0;
			result_connect = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(i != 0 && j != 0 && i != N-1 && j != N-1) {
							core_count++;
						}
					}
				}
			}
			arr = new int[core_count];
			cores = new int[2][core_count];
			
			int index = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == 1) {
						if(i != 0 && j != 0 && i != N-1 && j != N-1) {
							cores[0][index] = i;
							cores[1][index] = j;
							index++;
						}
					}
				}
			}

			dfs(0);

			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(result_connect)
			.append("\n");
		}
		System.out.print(sb);
		
	}

	private static void dfs(int depth) {
		
		if(depth == core_count) {

			int count = 0;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == 2) {
						count++;
					}
				}
			}
			
			//result = Math.max(result, result_core);
			
			if(result_core > result) {
				result = result_core;
				result_connect = count;
			}
			else if(result == result_core) {
				
				result_connect = Math.min(result_connect,count);
				
			}
			return;
		}
		
		for(int dir=0;dir<4;dir++) {

			boolean connect_flag = can_connect(depth,dir);
			//선 연결 가능한지 check후 불가능시 continue

			if(!connect_flag) {
				continue;
			}
			
			//선 연결하기
			connect(depth,dir,2);
			result_core++;
			
			arr[depth] = dir;
			dfs(depth+1);
			
			result_core--;
			connect(depth,dir,0);			
		}
		dfs(depth + 1);

	}

	private static void connect(int depth, int dir, int change) {
		int cx = cores[0][depth];
		int cy = cores[1][depth];
		
		int nx = cx+dx[dir];
		int ny = cy+dy[dir];
		
		while(nx >= 0 && ny >= 0 && nx < N && ny < N) {
			
			map[nx][ny] = change;
			
			nx = nx+dx[dir];
			ny = ny+dy[dir];

		}
	}

	private static boolean can_connect(int depth, int dir) {
		
		int cx = cores[0][depth];
		int cy = cores[1][depth];

		int nx = cx+dx[dir];
		int ny = cy+dy[dir];

		// 아래, 위, 오른쪽, 왼쪽 2 2 1 0 0
		while(map[nx][ny] == 0) {
			
			if(nx == 0 || ny == 0 || nx == N-1 || ny == N-1) {
				return true;
			}
			nx = nx+dx[dir];
			ny = ny+dy[dir];
		}

		return false;
	}

}