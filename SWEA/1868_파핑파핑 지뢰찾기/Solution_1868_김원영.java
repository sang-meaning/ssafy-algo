package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int count;
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	static int N;
	static int[][] map ;
	static int[][] visited;
	static void dfs(int cur_r, int cur_c) {
		
		visited[cur_r][cur_c] = 1;
		
		for(int i=0; i<8; i++) {
			int next_r = cur_r+dr[i];
			int next_c = cur_c+dc[i];
			if(next_r>=N || next_r<0 || next_c >=N || next_c<0) {
				continue;
			}
			if(isZero(next_r, next_c) && visited[next_r][next_c] == 0) {
				
				dfs(next_r, next_c);
			}
		}
		return;
	}
	static boolean isZero(int r, int c) {
		for(int i=0; i<8; i++) {
			int next_r = r+dr[i];
			int next_c = c+dc[i];
			if(next_r>=N || next_r<0 || next_c >=N || next_c<0) {
				continue;
			}
			if(map[next_r][next_c] == '*' ) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			visited = new int[N][N];
			for(int i=0; i<N; i++) {
				String line = sc.next();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j);
					visited[i][j] = 0;
					if(map[i][j] == '*') {
						visited[i][j] = 1;
					}
				}
			}
			count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(isZero(i,j) && visited[i][j] == 0) {
						dfs(i,j);
						count += 1;
					}else if(!isZero(i,j) && map[i][j] != '*') {
						count += 1;
					}
					
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!isZero(i,j) && map[i][j] != '*') {
						for(int k=0; k<8; k++) {
							int next_r = i+dr[k];
							int next_c = j+dc[k];
							if(next_r>=N || next_r<0 || next_c>=N || next_c<0) {
								
				                continue;
				            }
							if(map[next_r][next_c] != '*' && isZero(next_r,next_c)) {
								count -= 1;
								break;
							}
						}
					}
				}
			}
			System.out.println("#"+tc+" "+count);
			
			
		}
	}
}
