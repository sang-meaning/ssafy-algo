package practice;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] roastOfDay = new int[101];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int next_r = x+ dr[i];
			int next_c = y+ dc[i];
			if(next_r>=0 && next_r <N && next_c>=0 && next_c<N) {
				if(map[next_r][next_c]>0 && visited[next_r][next_c] == false) {
					dfs(next_r,next_c);
				}
			}
		}
		return;
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int [N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					visited[i][j] = false;
				}
			}
			for(int day=1;day<=100;day++) {
				int roastCount = 0;
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j] == day) {
							map[i][j] = 0;
						}
					}
				}
				visited = new boolean[N][N];
				
				for(int i=0; i<N;i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j] > 0 && visited[i][j] == false) {
							dfs(i,j);
							roastCount++;
						}else {
							continue;
						}
					}
				}
				roastOfDay[day] = roastCount;
			}
			
			int maxRoasts = 0;
			for(int i=1; i< 101; i++) {
				maxRoasts = Math.max(maxRoasts, roastOfDay[i]);
			}
			System.out.println("#"+tc+" "+maxRoasts);
		}
	}
}
