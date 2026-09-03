package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 조합 완탐

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] snacks = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			int answer = -1;

			for (int i = 0; i < snacks.length - 1; i++) {
				if (snacks[i] >= M) {
					continue;
				}
				for (int j = i + 1; j < snacks.length; j++) {
					int sum = snacks[i] + snacks[j];
					if (sum == M) {
						answer = M;
						break;
					} else if (sum < M) {
						answer = Math.max(answer, sum);
					}
				}
			}

			System.out.println("#" + test_case_num + " " + answer);
		}
	}
}
