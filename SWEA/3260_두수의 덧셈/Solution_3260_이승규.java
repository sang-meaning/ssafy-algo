package coding_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_3260_이승규 {
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
			ArrayList<Integer> num1 = new ArrayList<>();
			ArrayList<Integer> num2 = new ArrayList<>();
			ArrayList<Integer> answerList = new ArrayList<>();

			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			String numString1 = st.nextToken();
			String numString2 = st.nextToken();

			for (int i = numString1.length() - 1; i >= 0; i--)
				num1.add(numString1.charAt(i) - '0');
			for (int i = numString2.length() - 1; i >= 0; i--)
				num2.add(numString2.charAt(i) - '0'); // 뒤에서부터

			int maxLen;
			if (num1.size() >= num2.size()) {
				num1.add(0);
				maxLen = num1.size();
				for (int i = num2.size(); i < maxLen; i++) {
					num2.add(0);
				}
			} else {
				num2.add(0);
				maxLen = num2.size();
				for (int i = num1.size(); i < maxLen; i++) {
					num1.add(0);
				}
			}

			boolean carry = false;
			int tempResult = 0;

			for (int i = 0; i < maxLen; i++) {
				if (carry) {
					tempResult = num1.get(i) + num2.get(i) + 1;
				} else {
					tempResult = num1.get(i) + num2.get(i);
				}
				carry = false;

				if (tempResult > 9) {
					carry = true;
					tempResult -= 10;
				}
				answerList.add(tempResult);
			}

			StringBuilder sb = new StringBuilder();

			if (answerList.get(answerList.size() - 1) == 0) { // 맨앞자리가 0이면
				for (int i = answerList.size() - 2; i >= 0; i--) {
					sb.append(answerList.get(i));
				}
			} else {
				for (int i = answerList.size() - 1; i >= 0; i--) {
					sb.append(answerList.get(i));
				}
			}

			System.out.println("#" + test_case + " " + sb);
		}
	}
}