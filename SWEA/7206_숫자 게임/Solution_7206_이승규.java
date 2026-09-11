package alg_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_7206_이승규 {
	static int[] memo = new int[100000];
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
		StringBuilder sb;
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			sb = new StringBuilder();
			int num = Integer.parseInt(bf.readLine());
			
			Arrays.fill(memo, -1); // 아직 계산하지 않은 숫자

			for(int i = 0; i < 10; i++) // 한자리수는 더이상 나눌 수 없다
				memo[i] = 0;
			
			int answer = dfs(num);

			sb.append("#").append(test_case).append(" ").append(answer);
			System.out.println(sb);
		}
	}

	public static int dfs(int num) {
		if(memo[num] != -1) {
			return memo[num];
		}

		String numString = Integer.toString(num);
		int result = divide(numString, 0, 1, false);
		memo[num] = result;
		return result;
	}

	public static int divide(String num, int index, int multiply, boolean isDivided) {
		if (index == num.length()) {
			if (isDivided) {
				return 1 + dfs(multiply);
			}
			return 0;
		}

		int max = 0;
		int value = 0;
		
		for (int i = index; i < num.length(); i++) {
			value = value * 10 + (num.charAt(i) - '0');
			
			boolean nextDivided = false;
			if(isDivided)
				nextDivided = true;
			if (i < num.length() - 1)
				nextDivided = true;
			
			int result = divide(num, i+1, multiply * value, nextDivided);
			
			max = Math.max(max, result);
		}
		return max;
	}
}

// 제한시간 46/50