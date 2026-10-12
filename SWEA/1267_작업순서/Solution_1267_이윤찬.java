import java.util.*;
import java.io.*;

public class Solution {
	static final int T = 10;
	static int V, E;

	static LinkedList<Integer>[] graph;
	static Queue<Integer> bfs;
	static int[] indegree;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			indegree = new int[V + 1];
			visited = new boolean[V + 1];
			graph = new LinkedList[V + 1];
			bfs = new LinkedList<>();
			for (int i = 1; i <= V; i++) {
				graph[i] = new LinkedList<>();
			}
			st = new StringTokenizer(br.readLine());
			for (int e = 1; e <= E; e++) {
				
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				graph[node1].add(node2);
				indegree[node2]++;
			}

			for (int i = 1; i <= V; i++) {
				if (indegree[i] == 0) {
					bfs.offer(i);
				}
			}
			System.out.print("#" + tc + " ");
			while (!bfs.isEmpty()) {
				int answer = bfs.poll();
				visited[answer]=true;
				
				System.out.print(answer + " ");

				for (int next : graph[answer]) {
					if(!visited[next]) {
						indegree[next]--;
					}
					if(indegree[next]==0) {
						bfs.offer(next);
					}
				}
			}
		}

	}

}
