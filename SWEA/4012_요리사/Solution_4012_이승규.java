package coding_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_4012_이승규 {
	static int n;
	static int[][] powerMap;
	static int[][] totalPower;
	static boolean[] selected;
	static int minDiff;

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
			n = Integer.parseInt(bf.readLine());
			String s;
			StringTokenizer st;
			powerMap = new int[n][n];

			for (int i = 0; i < n; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				for (int j = 0; j < n; j++) {
					powerMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			totalPower = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					totalPower[i][j] = powerMap[i][j] + powerMap[j][i];
				}
			}

			selected = new boolean[n];
			// 반으로 나누는 경우의 수 : nCn/2 / 2 -> 최대 6000정도.. 완탐해도 되는거 아닌가
			// 일단 dfs로

			minDiff = Integer.MAX_VALUE;
			dfs(0, 0);
			
			System.out.println("#" + test_case + " " + minDiff);
		}
	}

	static void dfs(int Ingredient, int foodCnt) {
		if (foodCnt == n / 2) {
			calc();
			return;
		}

		for (int i = Ingredient; i < n; i++) {
			selected[i] = true;
			dfs(i + 1, foodCnt + 1);
			selected[i] = false; // 백트래킹
		}
	}

	static void calc() {
		ArrayList<Integer> ingA = new ArrayList<>();
		ArrayList<Integer> ingB = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			if(selected[i]) {
				ingA.add(i);
			} else
				ingB.add(i);
		}
		
		int resultA = 0;
		int resultB = 0;
		
		for(int i = 0; i < n/2; i++) {
			for(int j = i + 1; j < n/2; j++) {
				resultA += totalPower[ingA.get(i)][ingA.get(j)];
				resultB += totalPower[ingB.get(i)][ingB.get(j)];
			}
		}
		int diff = Math.abs(resultA - resultB);
		minDiff = Math.min(minDiff, diff);
	}

}