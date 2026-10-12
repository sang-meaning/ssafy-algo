package alg_prac_solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_1949_이승규 {
	static int n, k, maxLength;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> startPoint;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
//System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		String s;
		StringBuilder sb;
		StringTokenizer st;
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			s = bf.readLine();
			st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			map = new int[n][n];
			visited = new boolean[n][n];
			int startValue = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > startValue) {
						startValue = map[i][j];
					}
				}
			}

			startPoint = new ArrayList<int[]>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == startValue)
						startPoint.add(new int[] { i, j });
				}
			}

			maxLength = Integer.MIN_VALUE;
			for (int i = 0; i < startPoint.size(); i++) {
				int startX = startPoint.get(i)[0];
				int startY = startPoint.get(i)[1];
				dfs(startX, startY, 1, false);
			}

			sb.append("#").append(test_case).append(" ").append(maxLength);
			System.out.println(sb);
		}
	}

	static void dfs(int x, int y, int length, boolean didCut) {
		if (!isPossible(x, y, didCut)) { // 더 못하면
			if (length > maxLength)
				maxLength = length;
			return;
		}

		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n)
				continue;
			if (map[nx][ny] < map[x][y] && !visited[nx][ny]) { // 낮은길이면
				dfs(nx, ny, length + 1, didCut);
			} else if (map[nx][ny] >= map[x][y] && !visited[nx][ny]) { // 높다치면
				if (didCut) // 잘랐으면
					continue;
				else {
					if (map[nx][ny] - k < map[x][y] && !visited[nx][ny]) {
						int original = map[nx][ny];
						map[nx][ny] = map[x][y] - 1;
						dfs(nx, ny, length + 1, true);
						map[nx][ny] = original;
					} else
						continue;
				}
			}
		}
		visited[x][y] = false;
	}

	static boolean isPossible(int x, int y, boolean didCut) {
		if (didCut) { // 잘랐으면
			for (int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (map[nx][ny] < map[x][y] && !visited[nx][ny])
					return true;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;

				if (map[nx][ny] - k < map[x][y] && !visited[nx][ny])
					return true;
			}
		}
		return false;
	}

}