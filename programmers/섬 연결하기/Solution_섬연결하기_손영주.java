package _submission;

import java.util.Arrays;

public class Solution {

	static public int solution(int n, int[][] costs) {

		// 크루스칼

		// 간선 정렬
		// 작은 간선 뽑고 두 요소 유니온 파인드
		// 두 요소가 같은 루트면 패스, 아니면 sum
		// 선택된 간선이 n-1 까지

		int[] p = new int[n];
		int[] r = new int[n];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}

		Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
		// 정렬

		int sum = 0;
		int cnt = 0;

		for (int i = 0; i < costs.length; i++) {
			int a = costs[i][0];
			int b = costs[i][1];
			if (findSet(a, p) != findSet(b, p)) {
				sum += costs[i][2];
				union(a, b, p, r);
				cnt++;
			}
			if (cnt == n - 1) break;
		}
		return sum;
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
