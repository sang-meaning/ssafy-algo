package _submission;

import java.util.*;
import java.io.*;

public class Solution_7465_손영주 {

	static int N;
	static int calLimit;

	static int[] scores;
	static int[] calories;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] p = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i;
			}

			int[] r = new int[N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				union(a, b, p, r);
			}

			// 중복 체크 하는 법 루트만 세기, 또는 합칠 때 카운트 줄이기
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (p[i] == i) cnt++;
			}
			System.out.println("#" + test_case_num + " " + cnt);
		}
	}

	static void union(int a, int b, int[] p, int[] r) {
		int aRoot = findSet(a, p);
		int bRoot = findSet(b, p);

		if (aRoot == bRoot) return;
		if (r[aRoot] > r[bRoot]) {
			p[bRoot] = aRoot;
		} else if (r[aRoot] < r[bRoot]) {
			p[aRoot] = bRoot;
		} else {
			p[bRoot] = aRoot;
			r[aRoot] += 1;
		}
	}

	static int findSet(int e, int[] p) {
		if (p[e] == e) return e;
		return p[e] = findSet(p[e], p);
	}
}