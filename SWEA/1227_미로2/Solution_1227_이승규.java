package alg_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution {

	static int[][] map;
	static boolean isPossible;
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

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
		int T = 10;
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			// dfs?
			bf.readLine();
			String s = "";
			map = new int[100][100];
            int startX = 0, startY = 0;
			
			for (int i = 0; i < 100; i++) {
				s = bf.readLine();
				for (int j = 0; j < 100; j++) {
					int num = Integer.parseInt(s.split("")[j]);
					if(num == 2) {
						startX = i;
						startY = j;
					}
					map[i][j] = num;
				}
			}
			isPossible = false;

			dfs(startX, startY);

			int answer = 0;
			if (isPossible)
				answer = 1;
			System.out.println("#" + test_case + " " + answer);
		}
	}

	static void dfs(int x, int y) {
		// 현재 3이라면, isPossible = true, return
		if(map[x][y] == 3) {
			isPossible = true;
			return;
		}
        // 갈곳이 없다면, return
		if(cannotMove(x,y)) {
			return;
		}
		// 현재 위치 1로 변경
		map[x][y] = 1;
		// 4방향 check. 0이 있으면 그쪽으로가서, dfs
		for(int i = 0; i < 4; i++) {
			if(x+dir[i][0] < 0 || x + dir[i][0] >= 100 || y + dir[i][1] < 0 || y + dir[i][1] >= 100)
				continue;
			if(map[x+dir[i][0]][y+dir[i][1]] != 1)
				dfs(x+dir[i][0], y+dir[i][1]);
		}
		// backtrack 필요 x
		
		
	}
	
	static boolean cannotMove(int x, int y) {
		for(int i = 0; i < 4; i++) {
			if(x+dir[i][0] < 0 || x + dir[i][0] >= 100 || y + dir[i][1] < 0 || y + dir[i][1] >= 100)
				continue;
			if(map[x+dir[i][0]][y+dir[i][1]] != 1) 
				return false;
		}
		return true;
	}
}