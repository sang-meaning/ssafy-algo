package coding_prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_4014_이승규 {
	static BufferedReader bf;
	static StringTokenizer st;
	static int n;
	static int x;
	static int[][] map;
	static boolean[] occupied;

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
		// Scanner sc = new Scanner(System.in);
		bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(ignoreEmptyLine());
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			String s = ignoreEmptyLine();
			st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken()); // 지도범위
			x = Integer.parseInt(st.nextToken()); // 활주로 크기
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				s = ignoreEmptyLine();
				st = new StringTokenizer(s);
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				} // 지도
			}

			boolean isPossible = true;
			int possibleCnt = 0;

			for (int i = 0; i < n; i++) {
				isPossible = true;
				occupied = new boolean[n];
				for (int j = 0; j < n - 1; j++) {
					if (map[i][j] == map[i][j + 1]) // 평탄
						continue;
					if (Math.abs(map[i][j] - map[i][j + 1]) > 1) { // 차이가 1 이상이라면, 안됨
						isPossible = false;
						break;
					}
					// 차이가 1이라면
					if (map[i][j] - map[i][j + 1] == 1) {// 내려갈때
						if (!underPossibleCheck(i, j, 0)) {
							isPossible = false;
							break;
						}

					}
					if (map[i][j] - map[i][j + 1] == -1) {// 올라갈때
						if (!upperPossibleCheck(i, j, 0)) {
							isPossible = false;
							break;
						}
					}
				} // i 기준 체크
				if (isPossible) {
					possibleCnt++;
				}
			}

			for (int j = 0; j < n; j++) {
				isPossible = true;
				occupied = new boolean[n];
				for (int i = 0; i < n - 1; i++) {
					if (map[i][j] == map[i + 1][j]) // 평탄
						continue;
					if (Math.abs(map[i][j] - map[i + 1][j]) > 1) { // 차이가 1 이상이라면, 안됨
						isPossible = false;
						break;
					}
					// 차이가 1이라면
					if (map[i][j] - map[i + 1][j] == 1) {// 내려갈때
						if (!underPossibleCheck(i, j, 1)) {
							isPossible = false;
							break;
						}
					}
					if (map[i][j] - map[i + 1][j] == -1) {// 올라갈
						if (!upperPossibleCheck(i, j, 1)) {
							isPossible = false;
							break;
						}
					}
				}

				if (isPossible) {
					possibleCnt++;
				}
			} // j 기준 체크

			System.out.println("#" + test_case + " " + possibleCnt);
		}
	}

	static String ignoreEmptyLine() throws IOException {
		String s;
		do {
			s = bf.readLine();
		} while (s != null && s.trim().isEmpty());
		return s;
	}

	static boolean underPossibleCheck(int i, int j, int way) {
		int[][] dir = { { 0, 1 }, { 1, 0 } };
		int standard = map[i + dir[way][0]][j + dir[way][1]];

		// 1. 설치 가능한지 확인
		for (int range = 1; range <= x; range++) {
			int ni = i + dir[way][0] * range;
			int nj = j + dir[way][1] * range;

			if (ni >= n || nj >= n)
				return false;

			if (map[ni][nj] != standard)
				return false;

			int pos = i * dir[way][0] + j * dir[way][1] + range;
			if (occupied[pos])
				return false;
		}

		// 2. 실제 경사로 설치
		for (int range = 1; range <= x; range++) {
			int pos = i * dir[way][0] + j * dir[way][1] + range;
			occupied[pos] = true;
		}

		return true;
	}

	static boolean upperPossibleCheck(int i, int j, int way) {
		int[][] dir = { { 0, 1 }, { 1, 0 } };

		int standard = map[i][j];

		// 1. 설치 가능한지 확인
		for (int range = 0; range < x; range++) {

			int ni = i - dir[way][0] * range;
			int nj = j - dir[way][1] * range;

			if (ni < 0 || nj < 0)
				return false;

			if (map[ni][nj] != standard)
				return false;

			int pos = i * dir[way][0] + j * dir[way][1] - range;

			if (occupied[pos])
				return false;
		}

		// 2. 실제 경사로 설치
		for (int range = 0; range < x; range++) {
			int pos = i * dir[way][0] + j * dir[way][1] - range;
			occupied[pos] = true;
		}

		return true;
	}

}