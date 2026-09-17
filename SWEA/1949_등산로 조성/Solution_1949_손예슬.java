package swea;
import java.util.*;
import java.io.*;
public class 등산로 {
	static int N, K;
	static int[][] map;;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int max = 0;
			List<int[]> maxPos = new ArrayList<>();
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j< N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max < map[i][j]) {
						max = map[i][j];
						maxPos.clear();
						maxPos.add(new int[] {i, j});
					}else if(max == map[i][j]) {
						maxPos.add(new int[] {i, j});
					}
				}
			}
			
			// 시작점 하나씩
			int answer = 0;
			for(int[] pos : maxPos) {
				visited = new boolean[N][N];
				visited[pos[0]][pos[1]] = true;
				int a = dfs(pos[0], pos[1], false, 1);
				answer = Math.max(a, answer);
			}
			System.out.println("#" + testcase + " " + answer);
		}
	}
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static boolean[][] visited; // 현재 경로에서 이 전 것 표시
	public static int dfs(int r, int c, boolean flag, int dept) {
		int max = dept;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= N || nc <0 || nc >= N) continue;
			
			if(visited[nr][nc]) continue;
			
			if(map[nr][nc] < map[r][c]) {
				visited[nr][nc] = true;
				int result = dfs(nr, nc, flag, dept+1);
				max = Math.max(max, result);
				visited[nr][nc] = false;
			}
			else if(((map[nr][nc] - K) < map[r][c]) && !flag) {
				int origin = map[nr][nc];
				
				map[nr][nc] = map[r][c]-1;
				visited[nr][nc] = true;
				int result = dfs(nr, nc, true, dept+1);
				max = Math.max(max, result);
				visited[nr][nc] = false;
				map[nr][nc] = origin;
			}
		}
		

		return max;
	}
}

// 이동은 현재 보다 낮은 곳
// 공사는 한 번만 가능
// 최대 K만큼 깎임
// 한번 깎을때 -> 깎아도 작아질 수 있는지, 깎는 정도는 현재보다 -1, 그 외는 불필요한 상황