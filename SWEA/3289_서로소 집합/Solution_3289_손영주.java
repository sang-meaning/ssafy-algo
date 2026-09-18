package _submission;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st.nextToken();

		int T = (int) st.nval;

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {

			sb.append("#").append(test_case_num).append(" ");

			// N개의 원소
			// M개의 연산
			st.nextToken();
			int N = (int) st.nval;

			int[] parent = new int[N];
			int[] rank = new int[N];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			} // make-set

			st.nextToken();
			int M = (int) st.nval;

			for (int i = 0; i < M; i++) {
				st.nextToken();
				int mode = (int) st.nval;
				st.nextToken();
				int a = (int) st.nval - 1;
				st.nextToken();
				int b = (int) st.nval - 1;

				if (mode == 0) { // 합집합
					union(a, b, parent, rank);
				} else { // mode == 1, 같은 집합인지 확인
					if (findSet(a, parent) == findSet(b, parent)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void union(int a, int b, int[] parent, int[] rank) {
		int aRoot = findSet(a, parent);
		int bRoot = findSet(b, parent);
		if (aRoot == bRoot) return; // 같은 집합이면 리턴
		if (rank[aRoot] > rank[bRoot]) { // a의 루트 랭크가 큰 경우 a로 병합
			parent[bRoot] = aRoot;
		} else if (rank[aRoot] < rank[bRoot]) { // b의 루트 랭크가 큰 경우 b로 병합
			parent[aRoot] = bRoot;
		} else { // 같은 경우에는 병합 후 병합된 루트의 랭크 상승 (임의로 a에 붙임)
			parent[bRoot] = aRoot;
			rank[aRoot] += 1;
		}
	}

	static int findSet(int e, int[] parent) {
		if (parent[e] == e) return e;
		else return parent[e] = findSet(parent[e], parent);
	}
}