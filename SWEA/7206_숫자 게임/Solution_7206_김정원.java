package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_7206_김정원 {
	static Map<Integer, Integer> memo;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			int num = Integer.parseInt(br.readLine());
			memo = new HashMap<>();

			int answer = play(num);

            System.out.println(String.format("#%d %d", test_case, answer));
		}

		System.out.print(sb);
	}

	static int play(int num) {
		// 한 자리 숫자면 더는 못 자름
		if (num < 10) {
			return 0;
		}

		if (memo.get(num) != null) {
			return memo.get(num);
		}

		String str = num + "";
		int max = 0;

		// 숫자 사이를 자를지 말지를 비트로 확인
		// 아무 데도 안 자르는 0은 제외
		for (int bit = 1; bit < (1 << (str.length() - 1)); bit++) {
			int start = 0;
			int mul = 1;

			for (int i = 0; i < str.length() - 1; i++) {
				if ((bit & (1 << i)) != 0) {
					// start부터 i까지가 숫자 한 조각
					String temp = str.substring(start, i + 1);
					int piece = Integer.parseInt(temp);

					mul = mul * piece;
					start = i + 1;
				}
			}

			// 마지막 조각은 반복문에서 처리되지 않으므로 따로 곱함
			String last = str.substring(start);
			mul *= Integer.parseInt(last);

			int result = play(mul);
			result++; // 이번에 자른 횟수

			if (result > max) {
				max = result;
			}
		}

		memo.put(num, max);
		return max;
	}
}