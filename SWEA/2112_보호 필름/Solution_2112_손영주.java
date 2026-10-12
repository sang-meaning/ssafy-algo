package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	// 부분집합 재귀 가지치기 현최소값보다 작을 경우에만

	static int D, W, K;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			boolean[][] flim = new boolean[D][W];

			for (int d = 0; d < D; d++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int w = 0; w < W; w++) {
					flim[d][w] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
				}
			}
			int answer = 0;
			if (!(K == 1 || test(flim))) {
				answer = yakpum(0, 0, Integer.MAX_VALUE, flim);
			}

			System.out.println("#" + test_case_num + " " + answer);
			// System.out.println(caseNum);
		}
	}

	static boolean test(boolean[][] flim) {
		for (int w = 0; w < W; w++) {
			int cnt = 1;
			for (int d = 1; d < D; d++) {
				if (flim[d][w] == flim[d - 1][w]) {
					cnt++;
				} else {
					cnt = 1;
				}
				if (cnt >= K)
					break;
			}
			if (cnt < K)
				return false;
		}
		return true;
	}

	// static int caseNum = 0;

	static int yakpum(int idx, int cnt, int minCnt, boolean[][] flim) {

		// cnt 가 최소값보다 크면 탈출하는 평범한 가지치기
		if (cnt > minCnt) {
			return minCnt;
		}

		if (idx == D) {
			if (test(flim)) {
				// caseNum++;
				return cnt;
			} else {
				return Integer.MAX_VALUE;
			}
		}
		// 특성 A = 0 : false
		// 특성 B = 1 : true
		boolean[] backup = Arrays.copyOf(flim[idx], W); // 원상태 저장.
		
		for (int i = 0; i < W; i++) {
			flim[idx][i] = true;
		}
		int routeB = yakpum(idx + 1, cnt + 1, minCnt, flim);
		minCnt = Math.min(minCnt, routeB);
		for (int i = 0; i < W; i++) {
			flim[idx][i] = backup[i];
		}
		
		for (int i = 0; i < W; i++) {
			flim[idx][i] = false;
		}
		int routeA = yakpum(idx + 1, cnt + 1, minCnt, flim);
		minCnt = Math.min(minCnt, routeA);
		for (int i = 0; i < W; i++) {
			flim[idx][i] = backup[i];
		}
		
		int routePass = yakpum(idx + 1, cnt, minCnt, flim);
		return Math.min(minCnt, routePass);
	}
}
