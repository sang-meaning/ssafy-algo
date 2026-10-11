import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Solution
{
	static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		long cost;
		
		public Edge(int from, int to, long cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	static void makeSet(int N) {
		parent = new int[N];
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		
		parent[rootB] = rootA;
		return true;
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			
			long[] x = new long[N];
			long[] y = new long[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Long.parseLong(st.nextToken());
				
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			
			// 간선 리스트
			ArrayList<Edge> edges = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					
					long dx = x[i] - x[j];
					long dy = y[i] - y[j];
					
					long cost = dx*dx + dy*dy;
					
					edges.add(new Edge(i, j, cost));
				}
			}
			
			Collections.sort(edges);
			
			makeSet(N);
			
			long total = 0;
			int cnt = 0;
			
			// 가장 싼 간선부터 확인
			for (Edge edge : edges) {
				
				// 서로 다른 집합이면 연결
				if (union(edge.from, edge.to)) {
					cnt++;
					total += edge.cost;
					
					if (cnt == N-1) break;
				}
				
			}
			
			long answer = Math.round(E * total);
			
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
}