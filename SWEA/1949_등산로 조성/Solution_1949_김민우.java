import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Solution {
	static int T;
	static int N, K;
	static int[][] mount;
	static int maxL;
	static ArrayList<Integer> start;
	static int[][] delta = {{0,-1}, {-1,0}, {0,1}, {1,0}};
	static boolean[][] visited;
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			StringTokenizer nk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(nk.nextToken());
			K = Integer.parseInt(nk.nextToken());
			
			mount = new int[N][N];
			visited = new boolean[N][N];
			start = new ArrayList<>();
			int maxH = 0;
			maxL = 0;
			for(int i =0; i < N; i++) {
				StringTokenizer mt = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					mount[i][j] = Integer.parseInt(mt.nextToken());
					visited[i][j] = false;
					maxH = (mount[i][j] > maxH)?mount[i][j]:maxH;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(mount[i][j] == maxH)
						start.add((i*N)+j);
				}
			}

			for(int s : start) {;
				visited[s/N][s%N] = true;
				search(mount, s, 1, false);
				visited[s/N][s%N] = false;
			}
		
			System.out.printf("#%d %d\n", test_case, maxL);
		}
		
		
			
	}
	
	public static void search(int[][] map, int idx, int curL, Boolean cut) {
		maxL = (maxL>curL)?maxL:curL;
        
        int r = idx / N;
		int c = idx % N;
		
		for(int[] dir : delta) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			if(nr < 0 || nr >= N || nc <0 || nc >= N) continue;
            if(visited[nr][nc]) continue;
			//1. 주변에 자신보다 낮은 길이 없을 때
			if(map[nr][nc] >= map[r][c]) {
				//이미 길을 깎았거나, 깍아도 지나갈 수 없을 때
				if(cut || (map[nr][nc] - K >= map[r][c]))
					continue;
				//깎을 수 있을 때
				else {
					int origin = map[nr][nc];
                    int cutH = map[nr][nc]-map[r][c]+1;
					map[nr][nc] -= cutH;
					visited[nr][nc] = true;
                    cut = true;
					search(map, (nr*N + nc), curL+1, cut);
					visited[nr][nc] = false;
					map[nr][nc] = origin;
                    cut = false;
				}
			}
			//2. 주변에 자신보다 낮은 길이 있을 때
			else {
				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					search(map, (nr*N + nc), curL+1, cut);
					visited[nr][nc] = false;
				}
			}
		}
	}

}
