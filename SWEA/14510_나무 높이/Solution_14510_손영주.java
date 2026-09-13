package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int[] H = new int[N];
			int tallest = 0;
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
				if (H[i] > tallest) {
					tallest = H[i];
				}
			}

			int one = 0;
			int two = 0;
			for (int i = 0; i < N; i++) {
				int gap = tallest - H[i];
				if ((gap % 2) == 1) {
					one++;
					two += (gap - 1) / 2;
				} else {
					two += gap / 2;
				}
			}

			// one 2, two 4
			// 1 2 1 2 - 2 - 2
			// one 4, two 3
			// 1 2 1 2 1 2 1
			// one 2, two 3
			// 1 2 1 2 - 2
			// one 4, two 2
			// 1 2 1 2 1 - 1

			while (one < two - 1) {
				one += 2;
				two--;
			}

			int day = 0;

			if (one > two) {
				day = one * 2 - 1;
			} else {
				day = two * 2;
			}

			System.out.println("#" + test_case_num + " " + day);
		}
	}
}
