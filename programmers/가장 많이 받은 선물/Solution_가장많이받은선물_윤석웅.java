package com.ssafy.swea;

import java.util.*;

class Solution258712 {
	public int solution(String[] friends, String[] gifts) {
		int ln = friends.length;
		int[][] arr = new int[ln][ln];
		Map<String, Integer> locate = new HashMap<>();
		int n = 0;
		for (String friend : friends) {
			locate.put(friend, n++);
		}

		for (int[] row : arr) {
			Arrays.fill(row, 0);
		}
		int[][] interac = new int[ln][ln];
		n = 0;

		for (String trans : gifts) {
			String[] samp = trans.split(" ");
			int n1 = locate.get(samp[0]);
			int n2 = locate.get(samp[1]);

			interac[n1][n2]++;
			interac[n2][n2]++;

		}
		for (int i = 0; i < interac.length; i++) {
			int res = 0;
			for (int j = 0; j < interac.length; j++)
				res += i == j ? 0 : interac[i][j];
			interac[i][i] = res - interac[i][i];
		}
		int[] resultarr = new int[ln];
		for (int i = 0; i < ln; i++) {
			for (int j = i + 1; j < ln; j++) {
				if (interac[i][j] > interac[j][i]) {
					resultarr[i]++;
				} else if (interac[i][j] < interac[j][i]) {
					resultarr[j]++;
				} else if (interac[i][i] > interac[j][j]) {
					resultarr[i]++;
				} else if (interac[i][i] < interac[j][j]) {
					resultarr[j]++;
				}

			}
		}

		int answer = 0;
		for (int item : resultarr) {
			if (answer < item) {
				answer = item;
			}
		}
		return answer;
	}
}