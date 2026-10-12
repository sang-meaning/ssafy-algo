package submission;

import java.util.*;
import java.io.*;

public class Solution {
	// 메모이제이션

	static int[] dp = new int[100000];
	// 각 숫자별 턴을 기록해두는 배열. 미기록시 0 값 가짐.
	// 알고리즘 내내 초기화할 필요 x

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			String num = br.readLine();

			int max = game(num);
			System.out.println("#" + test_case_num + " " + max);
		}
	}

	// 이 숫자로 얻을 수 있는 최대 턴을 리턴함.
	static int game(String num) {

		int intNum = Integer.parseInt(num);

		if (intNum < 10) {
			return 0;
		}

		if (dp[intNum] != 0) {
			return dp[intNum];
		}

		int maxTurn = 0;

		Queue<Integer> q = calCut(num);
		while (!q.isEmpty()) {
			maxTurn = Math.max(maxTurn, game(String.valueOf(q.poll())));
		}

		return dp[intNum] = maxTurn + 1;
	}

	// String 넣으면 그걸로 나오는 숫자들 리스트 반환해줌..
	// ex 1*234 12*34 123*4 1*2*34 12*3*4 1*2*3*4
	// bfs
	static Queue<Integer> calCut(String num) {

		int intNum = Integer.parseInt(num);
		Queue<Integer> q = new ArrayDeque<>();

		if (num.length() == 1) {
			q.offer(intNum);
			return q;
		}

		for (int ex = 1; ex < num.length(); ex++) {
			String upper = num.substring(0, ex);
			String lower = num.substring(ex, num.length());

			int cal = Integer.parseInt(upper) * Integer.parseInt(lower);

			q.offer(cal);

			Queue<Integer> lowerQ = calCut(lower);

			while (!lowerQ.isEmpty()) {
				cal = Integer.parseInt(upper) * lowerQ.poll();
				q.offer(cal);
			}
		}
		return q;
	}

}