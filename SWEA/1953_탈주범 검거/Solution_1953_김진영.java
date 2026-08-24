package swea;

import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,M,sx,sy,time;
	static int result;
	static int[][] map;
	static int[][] visited;
	
	static Deque<int[]> q;
	
	static int[] dx = {1,-1,0,0}; //아래,위,오른쪽/왼쪽
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new int[N][M];
			q = new ArrayDeque<>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;
			q.add(new int[] {sx,sy});
			visited[sx][sy] = 1;

			while(!q.isEmpty()) {
				int temp[] = q.poll();
				
				int cx = temp[0];
				int cy = temp[1];
				
				for(int dir=0;dir<4;dir++) {
					int nx = cx + dx[dir];
					int ny = cy + dy[dir];
					
					if(nx < 0 || ny < 0 || nx > N-1 || ny > M-1) {
						continue;
					}
					
					if(visited[nx][ny] != 0) {
						continue;
					}
					if(map[cx][cy] == 1) {
						if(dir == 0) { // 아래
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 1) { // 위
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 2) { // 오른쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 3) { //왼쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
						
					}else if(map[cx][cy] == 2) {
						if(dir == 0) {// 아래
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 1) { // 위
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
						
					}else if(map[cx][cy] == 3) {
						if(dir == 2) {// 오른쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 3) { //왼쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
					}else if(map[cx][cy] == 4) {
						if(dir == 1) { // 위
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 2) {// 오른쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
					}else if(map[cx][cy] == 5) {
						if(dir == 0) {// 아래
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 2) {// 오른쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
					}else if(map[cx][cy] == 6) {
						if(dir == 0) {// 아래
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 3) { //왼쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
					}else if(map[cx][cy] == 7) {
						if(dir == 1) { // 위
							if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}else if(dir == 3) { //왼쪽
							if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
								visited[nx][ny] = visited[cx][cy]+1;
								q.add(new int[] {nx,ny});
							}
						}
					}
				}	
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(visited[i][j] <= time && visited[i][j] != 0) {
						result++;
					}
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