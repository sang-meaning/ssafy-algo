
import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
	public int solution(int N, int[][] computers) {
		ArrayList<ArrayList<Integer>> g = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			g.add(new ArrayList<>());
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (i == j) continue;
				if (computers[i][j] == 1) {
					g.get(i).add(j);
				}
			}
		}
		
		int answer = 0;
		boolean[] visited = new boolean[100];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			if (visited[i]) continue;
			
			q.add(i);
			visited[i] = true;
			
			while (!q.isEmpty()) {
				int f = q.poll();
				
				for (int u : g.get(f)) {
					if (visited[u]) continue;
					q.add(u);
					visited[u] = true;
				}
			}
			
			answer++;
		}
		
		return answer;
	}
}