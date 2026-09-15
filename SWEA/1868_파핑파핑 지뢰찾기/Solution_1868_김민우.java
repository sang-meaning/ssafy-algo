import java.util.*;
import java.io.*;

class Solution
{

	static int T, N;
	static char[][] map;
	static boolean[] visited;
	static int answer;
	static int[][] delta = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new char[N][N];
			visited = new boolean[N*N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String s= st.nextToken();
				for(int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j);
				}
			}//입력 끝
			
			answer = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i*N+j] && map[i][j] != '*' && findMine(i, j) == 0) {
						map[i][j] = '0';
						visited[i*N+j] = true;
						bfs(i, j);
						answer++;
					}
				}
			}// 첫 번째 for문 끝
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i*N+j] && map[i][j] == '.')
						answer++;
				}
			}
			
			sb.append("#"+test_case+" "+answer+"\n");
		}//test_case
		System.out.print(sb);
	}//main 끝
	
	public static int findMine(int r, int c) {
		int cnt = 0;
		
		for(int[] dir : delta) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			if(!isIn(nr, nc))
				continue;
			if(map[nr][nc] == '*')
				cnt++;
		}
		
		return cnt;
	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> idxList = new LinkedList<>();
		
		idxList.offer(new int[] {r,c});
		while(!idxList.isEmpty()) {
			int[] cur = idxList.poll();
			
			for(int[] dir : delta) {
				int nr = cur[0] + dir[0];
				int nc = cur[1] + dir[1];
				
				if(!isIn(nr, nc) || visited[nr*N+nc])
					continue;
				visited[nr*N+nc] = true;
				
				int mineNum = findMine(nr, nc);
				if(mineNum == 0) {
					idxList.offer(new int[] {nr, nc});
				}
				map[nr][nc] = (char)(mineNum + '0');
			}
		}
	}
	
	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}