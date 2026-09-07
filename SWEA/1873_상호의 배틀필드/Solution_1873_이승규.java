package alg_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_1873_이승규 {
	static int h;
	static int w;
	static int tankX;
	static int tankY;
	static int tankDir;
	static char[][] map;
	static int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

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
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			tankX = 0;
			tankY = 0;
			tankDir = 0;

			for (int i = 0; i < h; i++) {
				s = bf.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '<' || map[i][j] == 'v' || map[i][j] == '>' || map[i][j] == '^') {
						tankX = i;
						tankY = j;
						if (map[i][j] == '<')
							tankDir = 0;
						if (map[i][j] == 'v')
							tankDir = 1;
						if (map[i][j] == '>')
							tankDir = 2;
						if (map[i][j] == '^')
							tankDir = 3;
					}
				}
			}

			int n = Integer.parseInt(bf.readLine());
			s = bf.readLine();

			for (int i = 0; i < n; i++) {
				char command = s.charAt(i);
				// u
				if (command == 'U') {
					map[tankX][tankY] = '^';
					tankDir = 3;
					if (tankX - 1 >= 0) {
						if (map[tankX - 1][tankY] == '.') {
							map[tankX][tankY] = '.';
							tankX -= 1;
							map[tankX][tankY] = '^';
						}
					}
				}
				// d
				if (command == 'D') {
					map[tankX][tankY] = 'v';
					tankDir = 1;
					if (tankX + 1 < h) {
						if (map[tankX + 1][tankY] == '.') {
							map[tankX][tankY] = '.';
							tankX += 1;
							map[tankX][tankY] = 'v';
						}
					}
				}
				// l
				if (command == 'L') {
					map[tankX][tankY] = '<';
					tankDir = 0;
					if (tankY - 1 >= 0) {
						if (map[tankX][tankY - 1] == '.') {
							map[tankX][tankY] = '.';
							tankY -= 1;
							map[tankX][tankY] = '<';
						}
					}
				}
				// r
				if (command == 'R') {
					map[tankX][tankY] = '>';
					tankDir = 2;
					if (tankY + 1 < w) {
						if (map[tankX][tankY + 1] == '.') {
							map[tankX][tankY] = '.';
							tankY += 1;
							map[tankX][tankY] = '>';
						}
					}
				}
				// s
				if (command == 'S') {
					shoot();
				}
			}
			
			System.out.print("#" + test_case + " "); 
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	static void shoot() {
		int power = 1;
		while (true) {
			int bulletX = tankX + dir[tankDir][0] * power;
			int bulletY = tankY + dir[tankDir][1] * power;
			power++;
			if (bulletX < 0 || bulletY < 0 || bulletX >= h || bulletY >= w)
				break;

			if (map[bulletX][bulletY] == '*') {
				map[bulletX][bulletY] = '.';
				break;
			}
			if (map[bulletX][bulletY] == '#')
				break;
		}
	}
}