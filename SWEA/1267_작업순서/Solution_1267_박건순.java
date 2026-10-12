import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	static int V;
	static int E;
	static List<Integer>[] graph;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			init(V);
			boolean[] visited = new boolean[V + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				addEdge(b, a);
			}

			System.out.print("#" + test_case + " ");

			int count = 0;
			while (count < V) {
				for (int i = 1; i <= V; i++) {
					if (visited[i])
						continue;

					boolean possible = true;

					for (int prev : graph[i]) {
						if (!visited[prev]) {
							possible = false;
							break;
						}
					}

					if (possible) {
						visited[i] = true;
						count++;

						System.out.print(i + " ");
					}
				}
			}

			System.out.println("");
		}
	}

	static void init(int n) {
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
	}

	static void addEdge(int a, int b) {
		graph[a].add(b);
	}
}