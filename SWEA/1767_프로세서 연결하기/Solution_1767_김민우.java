import java.io.*;
import java.util.*;


public class Solution_1767_김민우 {
	static int[][] cells;
	static List<int[]> cores;
	static int[][] delta = new int[][] {{0,-1},{-1,0},{0,1},{1,0}};
	static int T, N;
	static int ansC, ansD;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			cells = new int[N][N];
			cores = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
					if(cells[i][j] == 1)
						cores.add(new int[] {i,j});
				}
			}
			
			ansD = Integer.MAX_VALUE;
			ansC = 0;
			dfs(0, 0, 0);
			if(ansD == Integer.MAX_VALUE)
				ansD = 0;
			System.out.printf("#%d %d\n", test_case, ansD);
		}
	}//main
	
	
	public static void dfs(int cnt, int sumD, int sumC) {
		if(cnt == cores.size()) {
			if(sumC > ansC) {
				ansC = sumC;
				ansD = sumD;
			}
			else if (sumC == ansC)
				ansD = (sumD < ansD)?sumD:ansD;
			return;
		}
		
		int[] curCore = cores.get(cnt);
		
		//이미 벽에 붙어서 연결완료된 경우
		//전선길이는 변경 X, 연결코어 +1
		if(curCore[0] == 0 || curCore[0] == N-1 || curCore[1] == 0 || curCore[1] == N-1) {
			dfs(cnt+1, sumD, sumC+1);
		}
		//그 외에는 4방향으로 탐색
		else {
			for(int[] dir : delta) {
				int cur = check(curCore[0], curCore[1], dir);
				if(cur == -1) {
					continue;
				}
				//1. 연결가능하면 해당 경로 -1로 변환
				int routeR = curCore[0] + dir[0];
				int routeC = curCore[1] + dir[1];
				for(int i = 1; i <= cur; i++) {
					cells[routeR][routeC] = -1;
					routeR += dir[0];
					routeC += dir[1];
				}
				
				//2-1. 이후, 다음 프로세서 연결하러 가기
				//2-2. 이때 전선길이 ++, 연결코어 +1
				dfs(cnt+1, sumD+cur, sumC+1);
				
				//3. 원복 후 다음 방향 탐색
				routeR -= dir[0];
				routeC -= dir[1];
				for(int i = 1; i <= cur; i++) {
					cells[routeR][routeC] = 0;
					routeR -= dir[0];
					routeC -= dir[1];
				}
			}
			//4. 전선을 아예 깔지 않는 경우도 고려해서..
			dfs(cnt+1, sumD, sumC);
		}//4방향 탐색 끝
	}//dfs 끝
	
	public static int check(int r, int c, int[] dir) {
		int total = -1;
		int tmp = 0;
		int nr = r + dir[0];
		int nc = c + dir[1];
		
		while(isIn(nr, nc)) {
			if(cells[nr][nc] == -1 || cells[nr][nc] == 1) {
				tmp = -1;
				break;
			}
			tmp++;
			nr += dir[0];
			nc += dir[1];
		}
		
		total = tmp;
		return total;
	}
	
	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}//isIn 끝
}//solution
