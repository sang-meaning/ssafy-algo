import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static int N;
	static int[][] map;
	static boolean[] dessert;
	static int startX;
	static int startY;
	static int maxCount;
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			dessert = new boolean[101];
			map = new int[N][N];
			maxCount = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - 2; i++) {
				for (int j = 0; j < N; j++) {
					if (j - 1 < 0 || j + 1 >= N) {
						continue;
					}
					dessert[map[i][j]] = true;
					startX = i;
					startY = j;
					dfs(i, j, 1, 0);
					dessert[map[i][j]] = false;
				}
			}

			if (maxCount == 0) {
				maxCount = -1;
			}
			System.out.println("#" + test_case + " " + maxCount);
		}
	}

	static void dfs(int x, int y, int count, int dir) {
	    // 직진했을때 좌표
		int nx = x + dx[dir];
	    int ny = y + dy[dir];

	    // 현재 방향으로 이동했을 때 시작점 복귀
	    if (dir == 3 && nx == startX && ny == startY) {
	        maxCount = Math.max(maxCount, count);
	        return;
	    }

	    // 현재 방향으로 직진
	    if (isRange(nx, ny) && !dessert[map[nx][ny]]) {
	        dessert[map[nx][ny]] = true;

	        dfs(nx, ny, count + 1, dir);

	        dessert[map[nx][ny]] = false;
	    }

	    // 다음 방향으로 꺾기
	    if (dir < 3) {
	    	// 꺾은 뒤 좌표
	        int tx = x + dx[dir + 1];
	        int ty = y + dy[dir + 1];
	        
	        // 크기가 1인 사각형 고려
	        if (dir + 1 == 3 && tx == startX && ty == startY) {
	            maxCount = Math.max(maxCount, count);
	            return;
	        }
	        
	        if (isRange(tx, ty) && !dessert[map[tx][ty]]) {
	            dessert[map[tx][ty]] = true;

	            dfs(tx, ty, count + 1, dir + 1);

	            dessert[map[tx][ty]] = false;
	        }
	    }
	}
	// map 범위안에 있는 좌표인지 체크
	static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}