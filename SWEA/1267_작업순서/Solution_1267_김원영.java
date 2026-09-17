import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int V;
	static int E;
	static ArrayList<Integer>[] graph;
	static int[] indegree;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for (int tc = 1; tc <= 10; tc++) {

			V = sc.nextInt();
			E = sc.nextInt();
			indegree = new int[V + 1];
			graph = new ArrayList[V + 1];
			
			for(int i=1; i<=V; i++) {
			    graph[i] = new ArrayList<>();
			}
			
			ArrayList<Integer> result = new ArrayList<>();
			Queue<Integer> q = new ArrayDeque<>();

			for (int i = 0; i < E; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				graph[from].add(to);
				indegree[to]++;
			}
			// 위상정렬 시작
			for (int i = 1; i <= V; i++) {
				if (indegree[i] == 0) {
					q.offer(i);
				}
			}

			while (!q.isEmpty()) {
				int cur = q.poll();
				result.add(cur);

				for (int each : graph[cur]) {
					indegree[each]--;

					if (indegree[each] == 0) {
						q.offer(each);
					}
				}
			}
			System.out.print("#"+tc+" ");
			for(int i =0; i< result.size();i++) {
				System.out.print(result.get(i)+" ");
			}
			System.out.println();
		}
	}
}
