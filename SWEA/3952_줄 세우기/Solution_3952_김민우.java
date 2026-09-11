import java.util.*;
import java.io.*;

public class Solution_3952_김민우 {
	
	static int T, N, M;
	static List<Integer>[] graph;
	static int[] indegree;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			indegree = new int[N+1];
			
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				indegree[to]++;
			}
			
			Queue<Integer> topoSort = new LinkedList<>();
			
			for(int i = 1; i <= N; i++) {
				if(indegree[i] == 0)
					topoSort.offer(i);
			}
			
			sb.append("#").append(test_case).append(" ");
			
			while(!topoSort.isEmpty()) {
				int idx = topoSort.poll();
				
				sb.append(idx).append(" ");
				
				for(int adj : graph[idx]) {
					//adj와의 간선 끊기
					//자동적으로 adj의 진입차수는 줄어듦
					indegree[adj]--;
					
					//현재 자신과 연결돼 있던 간선을 끊었는데도 진입차수가 남아있다는 건,
					//다른 from -> adj 간선이 연결돼 있음을 뜻함, 즉 그 노드가 정렬되기 전까지는 queue에 들어갈 수 없음
					if(indegree[adj] != 0)
						continue;
					topoSort.offer(adj);
				}
			}
			sb.append("\n");
			
		}//test_case 끝
		System.out.println(sb);
	}//main 끝

}//Solution 끝
