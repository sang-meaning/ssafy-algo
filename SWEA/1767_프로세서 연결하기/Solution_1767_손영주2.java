package _submission;

import java.io.*;
import java.util.*;

public class Solution {

	static int N, processerCnt;

	static int[][] map;
	static int[][] processers = new int[12][2]; // 프로세서 위치 어레이

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			processerCnt = 0;

			for (int c = 0; c < N; c++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int r = 0; r < N; r++) {
					map[c][r] = Integer.parseInt(st.nextToken());
					if (map[c][r] == 1) {
						processers[processerCnt][0] = c;
						processers[processerCnt][1] = r;
						processerCnt++;
					}
				}
			}

			maxProcessor = 0;
			minWire = Integer.MAX_VALUE;

			dfs(0, 0, 0);
			int answer = minWire;
			System.out.println("#" + test_case + " " + answer);
		}
	}

	static int[] dr = { 0, 1, 0, -1 }; // 우 하 상 좌
	static int[] dc = { 1, 0, -1, 0 };

	// 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구하고자 한다.
	// 단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.

	static int maxProcessor = 0;
	static int minWire = Integer.MAX_VALUE;

	public static void dfs(int idx, int cnt, int sum) {
		// 인덱스, 와이어의 합, 지금것 놓은 프로세서의 개수, 프로세서 리스트, 맵, 맵 크기

		// 종료조건
		// 만약 앞으로의 프로세서를 다 더해도 최대값이 안 된다면
		// 현재까지 연결된 개수 + 앞으로의 프로세서 개수 < 기록된 프로세서 개수
		if (cnt + processerCnt - idx < maxProcessor) {
			return;
		}
		// 다 순회하였으면 갱신
		if (idx == processerCnt) {
			if (cnt > maxProcessor) {
				maxProcessor = cnt;
				minWire = sum;
			} else if (maxProcessor == cnt) {
				minWire = Math.min(minWire, sum);
			}
			return;
		}

		// 전파조건
		// 가장자리에 있다면 이미 연결되어 있다.
		// 프로세서 [0] = c , [1] = r
		int c = processers[idx][0]; // processor 위치.
		int r = processers[idx][1];

		if (isEdge(c, r)) {
			dfs(idx + 1, cnt + 1, sum);
		} else {
			// 4방향 각각 전선을 연결한 다음 탐색해 본다.
			for (int dir = 0; dir < 4; dir++) {
				if (canDraw(c, r, dir)) {
					int wire = draw(c, r, dir, 9);
					dfs(idx + 1, cnt + 1, sum + wire);
					draw(c, r, dir, 0);
				}
			} // 연결하지 않고 넘기는 가능성
			dfs(idx + 1, cnt, sum);
		}
	}

	public static boolean isEdge(int c, int r) {
		return (r == N - 1) || (r == 0) || (c == N - 1) || (c == 0);
	}

	public static boolean canDraw(int c, int r, int dir) {
		while (!isEdge(c, r)) {
			c += dc[dir];
			r += dr[dir];
			if (map[c][r] != 0) {
				return false;
			}
		}
		return true;
	}

	public static int draw(int c, int r, int dir, int val) {
		// 그리고 wire 개수 반환
		int cnt = 0;
		while (!isEdge(c, r)) {
			c += dc[dir];
			r += dr[dir];
			map[c][r] = val;
			cnt++;
		}
		return cnt;
	}
}
