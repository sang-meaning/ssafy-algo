package _submission;

import java.io.*;
import java.util.*;

public class Solution {
	
	// ai assist

	static int N, M, C;
	static int[][] map;
	static int[][] profit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			profit = new int[N][N - M + 1];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c <= N - M; c++) {
					profit[r][c] = getMaxProfit(r, c);
				}
			}

			int answer = 0;

			for (int r1 = 0; r1 < N; r1++) {
				for (int c1 = 0; c1 <= N - M; c1++) {

					for (int r2 = r1; r2 < N; r2++) {
						for (int c2 = 0; c2 <= N - M; c2++) {

							if (r1 == r2 && c1 + M > c2) {
								continue;
							}

							answer = Math.max(answer, profit[r1][c1] + profit[r2][c2]);
						}
					}
				}
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

	static int getMaxProfit(int r, int startC) {
		int max = 0;

		for (int mask = 0; mask < (1 << M); mask++) {
			int sum = 0;
			int value = 0;

			for (int i = 0; i < M; i++) {
				if ((mask & (1 << i)) != 0) {
					int honey = map[r][startC + i];
					sum += honey;
					value += honey * honey;
				}
			}

			if (sum <= C) {
				max = Math.max(max, value);
			}
		}

		return max;
	}
}
