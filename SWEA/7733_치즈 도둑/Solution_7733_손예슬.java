
import java.util.*;
import java.io.*;
public class Solution{
	static int T, N;
	static int[][] board;
	static int[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			int max = 0;
			for(int i = 0; i< N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, board[i][j]);
				}
			}
			
			int answer_max = 0;
			for(int day = 0; day <= max; day++) {
				int cnt = 0;
				visited = new int[N][N];
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(board[i][j] > day && visited[i][j] == 0) {
							dfs(i, j, day);
							cnt++;
						}
					}
				}
				answer_max = Math.max(cnt, answer_max);
			}
			System.out.println("#" + testcase + " " + answer_max);
		}
		
		
	}
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void dfs(int r, int c, int day) {
		// 현재 칸이 유효한지
		// 유효 안하면 빠져나와
		// 유효하면 주위 탐색(not visited, over day)
		// 유효했을 땐 1반환
		visited[r][c] = 1;
		
		for(int i = 0; i<4; i++) {
			int nr = r+ dr[i];
			int nc = c+ dc[i];
			if(nr < 0 || nc < 0 || nr >= N || nc >=N ) continue;
			
			if(visited[nr][nc] == 0 && board[nr][nc] > day) {
				dfs(nr, nc, day);
			}
		}
	}
}
