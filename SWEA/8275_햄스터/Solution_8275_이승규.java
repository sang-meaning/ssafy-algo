package alg_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_8275_이승규 {
	static int[] start;
	static int[] end;
	static int[] hamCnt;
	static int n,x,m;
	static int[] hamBox, answerBox;
	static int hamMax;
	
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
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			start = new int[m];
			end = new int[m];
			hamCnt = new int[m];
			
			for(int i = 0; i < m; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				start[i] = Integer.parseInt(st.nextToken()) - 1;
				end[i] = Integer.parseInt(st.nextToken()) - 1;
				hamCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			hamMax = Integer.MIN_VALUE;
			hamBox = new int[n];
			answerBox = new int[n];
			dfs(0, 0);
			
			if(hamMax == Integer.MIN_VALUE) {
				System.out.println("#" + test_case + " -1");
			} else {
				System.out.print("#" + test_case + " ");
				for(int i = 0; i < n; i++) {
					System.out.print(answerBox[i] + " ");
				}
			}
		}
	}
	
	public static void dfs(int depth, int hamSum) {
		if(depth == n) {
			if(check()) { // 정답이라면
				if(hamSum > hamMax) { // 이게 최대라면
					hamMax = hamSum;
					for(int i = 0; i < n; i++) {
						answerBox[i] = hamBox[i];
					}
				}
			}
			return;
		}
		
		for(int i = 0; i <= x; i++) {
			hamBox[depth] = i;
			dfs(depth+1, hamSum + i);
		}
	}
	
	public static boolean check()  {
		for(int i = 0; i < m; i++) {
			int temp = 0;
			for(int j = start[i]; j < end[i] + 1; j++) {
				temp += hamBox[j];
			}
			if(temp != hamCnt[i])
				return false;
		}
		return true;
	}
}