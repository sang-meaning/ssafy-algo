package _submission;

import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			int N = Integer.parseInt(br.readLine());

			int mid = (N - 1) / 2;

			int[][] farm = new int[N][N];

			// 당근 개수가 비슷하게 나누려 한다. -> 당근 차이 -> 당근 /2

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = line.charAt(j) - '0';
				}
			}

			int total = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (Math.abs(i - mid) + Math.abs(j - mid) <= mid) {
						total += farm[i][j];
					}
				}
			}

			System.out.println("#" + test_case_num + " " + total);
		}
	}
}