package alg_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_2112_이승규 {
	static int d, w, k;
	static int[][] film;
	static int minInjection;

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
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			film = new int[d][w];

			for (int i = 0; i < d; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				for (int j = 0; j < w; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minInjection = Integer.MAX_VALUE;
			dfs(0, 0);
			
			System.out.println("#" + test_case + " " + minInjection);
		}
	}

	static void dfs(int depth, int injection) {
		// 현재 넣은양이 min보다 늘어나면, pruning
		if (injection >= minInjection)
			return;
		// 탈출조건
		if (depth == d) {
			if (checked()) {
				if (injection < minInjection)
					minInjection = injection;
			}
			return;
		}

		int[] backup = film[depth].clone();
		// 안넣기
		dfs(depth + 1, injection);
		// a b 넣어보기
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				for (int j = 0; j < w; j++) {
					film[depth][j] = 0; // a
				}
				dfs(depth + 1, injection + 1);
			} else {
				for (int j = 0; j < w; j++) {
					film[depth][j] = 1; // b
				}
				dfs(depth + 1, injection + 1);
			}
		}
		// backtrack
		film[depth] = backup;
	}

	static boolean checked() {
		// 모든 열을 check
		for(int j = 0; j < w; j++) {
			boolean result = false;
			int cnt = 1;
			int prev = -1;
			for(int i = 0; i < d; i++) {
				if(film[i][j] != prev) {
					prev = film[i][j];
					cnt = 1;
				} else {
					cnt++;
				}
				
				if(cnt >= k) {
					result = true;
				}
			}
			if(!result) {
				return false;
			}
		}
		return true;
	}
}