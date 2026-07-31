import java.util.*;

class Solution {
	
	public static int T, N;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	public static int[][] arr, seq;
	public static Map<Integer, int[]> map;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();

			map = new HashMap<> ();
			arr = new int[N][N];
			seq = new int[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					arr[i][j] = sc.nextInt();
					seq[i][j] = 1;
					map.put(arr[i][j], new int[] {i, j});
				}
			}
			
			// 작은 숫자를 시작으로, 각 숫자에서 주변에 연속된 숫자가 있는지 탐색.
			// 연속되었다면, 얼마만큼 연속되어왔는지 및 최대 길이 저장.
			int maxLen = 1;
			for (int i=1; i<=N*N; i++) {
				int[] v = map.get(i);
				int x = v[0];
				int y = v[1];
				
				for (int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if (arr[x][y] == arr[nx][ny] + 1) {
						seq[x][y] = seq[nx][ny] + 1;
						maxLen = Math.max(maxLen, seq[x][y]);
					}
				}
			}
			
			// 최대 길이를 가진 연속된 수열의 최소 시작점을 구하기 때문에, 최소 시작점 중 후보지(seq = 1)부터 탐색 시작.
			int num = 1;
			for (int t=1; t<=N*N; t++) {
				int[] v = map.get(t);
				int i = v[0];
				int j = v[1];
				
				// 최소 시작점 중 후보지(seq = 1)부터 탐색 시작.
				if (seq[i][j] != 1) continue;
				
				Queue <int[]> q = new LinkedList<>();
				q.add(new int[] {i, j});
				
				// 문제에서 [1, N^2]가 하나씩만 나온다 명시되었으므로, 연속된 숫자가 여러번 나올일이 잘 없음.
				// 그러므로 visited가 없어도 대략 4 * maxLen 칸 정도만 탐색선에서 제한됨.
				boolean br = false;
				while (!q.isEmpty()) {
					int[] f = q.poll();
					
					int x = f[0];
					int y = f[1];
					
					if (seq[x][y] == maxLen) {
						br = true;
						num = t;
					}
					
					for (int d=0; d<4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						
						if (arr[nx][ny] == arr[x][y] + 1) {
							q.add(new int[] {nx, ny});
						}
					}
				}
				if (br) break;
			}

			
			System.out.println("#" + test_case + " " + num + " " + maxLen);
		}
	}
}