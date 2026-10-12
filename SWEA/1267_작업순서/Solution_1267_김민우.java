import java.io.*;
import java.util.*;

public class Solution_1267_김민우 {

	static int N, M;
	static List<Integer>[] graph;
	static int[] indegree;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= 1; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			indegree = new int[N+1];
			
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				indegree[to]++;
			}
			
			Queue<Integer> nodes = new LinkedList<>();
			
			for(int i = 1; i <= N; i++) {
				if(indegree[i] == 0)
					nodes.offer(i);
			}

			sb.append("#"+test_case+" ");
			while(!nodes.isEmpty()) {
				int cur = nodes.poll();
				sb.append(cur+" ");
				for(int adj : graph[cur]) {
					indegree[adj]--;
					if(indegree[adj] == 0)
						nodes.offer(adj);
				}
			}
			
			sb.append("\n");
		}//test_case 끝
		System.out.println(sb);
	}//main 끝

}//Solution 끝
