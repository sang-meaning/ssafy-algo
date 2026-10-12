import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    int N;
	List<Set<Integer>> graph;
	boolean[] visited;

	public int solution(int n, int[][] computers) {
		int answer = 0;
		init(n);
		visited = new boolean[n + 1];
		for (int i = 0; i < computers.length; i++) {
			for (int j = 0; j < computers[i].length; j++) {
				if (i != j && computers[i][j] == 1) {
					addEdge(i, j);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i);
				answer++;
			}
		}

		return answer;
	}

	void init(int n) {
		graph = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			graph.add(new HashSet<>());
		}
	}

	void addEdge(int a, int b) {
		graph.get(a).add(b);
		graph.get(b).add(a);
	}

	void dfs(int cur) {
		visited[cur] = true;

		for (int next : graph.get(cur)) {
			if (visited[next])
				continue;

			dfs(next);
		}
	}
}
