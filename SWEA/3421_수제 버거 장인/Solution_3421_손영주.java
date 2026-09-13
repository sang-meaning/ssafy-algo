package submission;

import java.util.*;
import java.io.*;

public class Solution {
	// 야심차게 비트 이용하기.

	static int N, M, cnt;
	// 1 ≤ N ≤ 20
	static int[] badPair;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			badPair = new int[N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				badPair[a] |= 1 << b;
				badPair[b] |= 1 << a;
			} // 입력

			cnt = 0;
			cookHam(0, 0);
			System.out.println("#" + test_case_num + " " + cnt);
		}
	}

	static void cookHam(int idx, int used) {
		// 모든 재료에 대한 선택 마친 경우
		if (idx == N) {
			cnt++;
			return;
		}

		// 재료 넣을 수 없는 경우
		if ((used & (1 << idx)) != 0) {
			// 다음 재료나 보러 간다.
			cookHam(idx + 1, used);
			return;
		}
		// 재료 넣는 경우
		cookHam(idx + 1, used | (1 << idx) | badPair[idx]);
		// 재료 안 넣는 경우
		cookHam(idx + 1, used);
	}

}