
import java.util.*;
import java.io.*;

public class Solution {
	static int V, E;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] graph = new ArrayList[V+1];
			
			for(int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			
			int[] indegree = new int[V+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				indegree[to]++;
			}
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			
			for(int i = 1; i <= V; i++) {
				if(indegree[i] == 0) q.offer(i);
			}
			
			sb.append("#").append(tc).append(" ");
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				sb.append(cur).append(" ");
				
				for(int nxt : graph[cur]) {
					indegree[nxt]--;
					
					if(indegree[nxt] == 0) q.offer(nxt);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

}
