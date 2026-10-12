package _submission;

import java.util.Arrays;

public class Solution {
	static public int solution(int n, int[][] computers) {

		// 계획: matrix로 받는다
		// visited 쓴다
		// ff인데 사방이 아닌 느낌의..

		boolean[] visited = new boolean[n];
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			if (visited[i] == true) continue;
			cnt++;
			ff(i, visited, computers, n);
		}
		return cnt;
	}

	static public void ff(int cur, boolean[] visited, int[][] computers, int n) {
		// cur는 지금 살펴보는 노드
		visited[cur] = true;
		// cur의 연결상태를 살펴본다
		for (int i = 0; i < n; i++) {
			// 연결된 노드가 이미 갔던 노드면 살펴보지 않는다
			if (visited[i] == true) continue;
			// 살펴볼게 자신이거나 0(= 연결 아님)이면 살펴보지 않는다
			if (cur == i || computers[cur][i] == 0) continue;
			ff(i, visited, computers, n);
		}
	}
}
