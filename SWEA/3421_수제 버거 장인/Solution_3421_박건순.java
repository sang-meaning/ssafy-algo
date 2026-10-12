package testCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int M;
	static int totalSum;
	static int[][] bad;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			boolean[] selected = new boolean[N];
			if (M > 0) {
				bad = new int[M][2];
				for (int i = 0; i < M; i++) {
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken()) - 1;
					int b = Integer.parseInt(st.nextToken()) - 1;
					bad[i][0] = a;
					bad[i][1] = b;
				}

			}
			totalSum = 0;
			subset(selected, 0);
			System.out.println("#" + test_case + " " + totalSum);
		}
	}

	static void subset(boolean[] selected, int depth) {

		if (depth == N) {
			for (int i = 0; i < M; i++) {
				int a = bad[i][0];
				int b = bad[i][1];

				if (selected[a] && selected[b]) {
					return;
				}
			}
			totalSum++;
			return;
		}

		selected[depth] = true;
		subset(selected, depth + 1);

		selected[depth] = false;
		subset(selected, depth + 1);

	}
}