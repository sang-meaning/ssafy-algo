package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution{
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] visited = new int [16][16];

	static void dfs(int cur_r, int cur_c) {
		visited[cur_r][cur_c] = 1;
		for(int i=0; i<4; i++) {
			int nr = cur_r+dr[i];
			int nc = cur_c+dc[i];
			
			if(nr>=0 & nr<16 & nc>=0 & nc<16 & (visited[nr][nc] ==0)) {
				dfs(nr,nc);
			}else {
				continue;
			}
		}

	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int i=0; i<T ; i++) {
			
		    for (int j = 0; j < 16; j++) {
		        for (int k = 0; k < 16; k++) {
		            visited[j][k] = 0;
		        }
		    }
		    
			int tc = sc.nextInt();
			
			int[][] map = new int[16][16];
			int start_r = 0;
			int start_c = 0;
			int goal_r = 0;
			int goal_c = 0;
			int possible=0;
			
			for(int j=0; j<16;j++) {
				String line = sc.next();
				for(int k=0; k<16; k++) {
					map[j][k] = line.charAt(k) - '0';
				}
			}
			for(int j=0; j<16;j++) {
				for(int k=0; k<16; k++) {
					
					if(map[j][k]==1) {
						visited[j][k] =1;
					}else if(map[j][k]==2) {
						start_r=j;
						start_c=k;
					}else if(map[j][k]==3){
						goal_r=j;
						goal_c=k;
					}
				}
			}
			dfs(start_r,start_c);
			if(visited[goal_r][goal_c]==1) {
				possible=1;
			}
			System.out.println("#"+tc+" "+possible);

		}
	}
}
