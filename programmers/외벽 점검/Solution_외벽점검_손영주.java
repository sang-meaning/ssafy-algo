package _submission;

import java.util.Arrays;

public class Solution {
	// 완탐
	// 비트마스킹
	// 비트어려워 살려줘

	static int[][] dp;
	static int N, min, rangeMask;

	static public int solution(int n, int[] weaks, int[] dist) {
		dp = new int[9][1 << 15]; // 친구 수, weaks 커버 여부

		for (int[] dprow : dp) {
			Arrays.fill(dprow, Integer.MAX_VALUE);
		}

		N = n;
		min = Integer.MAX_VALUE;
		rangeMask = (1 << weaks.length) - 1;
		
		for (int d : dist) {
			if(d >= N) { // 외벽점검담당친구 발견
				min = 1;
			}
		}
		
		if (min != 1) { 
			dfs(0, 0, 0, weaks, dist);
			min = (min == Integer.MAX_VALUE) ? -1 : min;
		}

		return min;
	}

	static public void dfs(int fndIdx, int fndCnt, int weakStatus, int[] weaks, int[] dist) {
		// 각 매개변수 의미
		// 지금 검사할 친구 인덱스, 지금까지 투입된 친구 수, 지금까지 커버된 weak 상태

		if (fndCnt >= dp[fndIdx][weakStatus]) {
			// 지금 이 상태에 온 적이 있다. 동일하거나 더 적은 친구 써서.
			return;
		}
		// 없다면 기록해
		dp[fndIdx][weakStatus] = fndCnt;

		if (fndCnt >= min) {
			return;
		}

		if (weakStatus == rangeMask) { // 기저조건
			min = Math.min(fndCnt, min);
		}
		if (fndIdx == dist.length) { // 기저조건
			return;
		}

		// 친구 한바퀴 돌리면서 커버
		int s = 0; // 친구 커버위치 시작지점
		int e = s + dist[fndIdx]; // 친구 커버위치 끝지점

		while (s < N) {
			int weaksCover = 0; // 이 친구로 인해 커버되는 weaks

			for (int i = 0; i < weaks.length; i++) {
				int weak = weaks[i];

				// 친구 범위 내의 커버되는 약점
				// 밖에서 제거했으므로 2바퀴 범위 넘어가는 경우는 없다
				if ((s <= weak && weak <= e) || (s <= weak + N && weak + N <= e)) {
					weaksCover |= 1 << i;
				}
			}

			weaksCover |= weakStatus; // 합치기

			if (weaksCover != weakStatus) { // 커버 상태가 달라졌다면? (이론상 플러스되어야 하므로 weaksCover > weakStatus)
				dfs(fndIdx + 1, fndCnt + 1, weaksCover, weaks, dist);
			}
			s++;
			e++; // 이동
		}

		dfs(fndIdx + 1, fndCnt, weakStatus, weaks, dist);
	}
}
