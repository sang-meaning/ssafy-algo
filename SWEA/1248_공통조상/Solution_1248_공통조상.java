package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1248_공통조상 {

	static int T;
	static int V;
	static int E;
	static List<List<Integer>> graph;
	static int[] parent;
	static int count;
	static int N1;
	static int N2;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			count =0;
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			N1 = Integer.parseInt(st.nextToken());
			N2 = Integer.parseInt(st.nextToken());
			parent = new int[V+1];
			graph = new LinkedList<>();
			for(int i = 0 ; i<= V ; i++) {
				graph.add(new LinkedList<>());
			}
			st = new StringTokenizer(br.readLine());
			for(int e = 0 ; e< E; e++) {
				
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				graph.get(v1).add(v2);
				
				parent[v2] = v1;  // v1 부모 v2 자식
			
			}
			
			int CommonAncestorNode = FindAncesotrNode(N1,N2);
			
			
			
			Queue<Integer> que = new  ArrayDeque<>();
			
			que.offer(CommonAncestorNode);
			
			while(!que.isEmpty()) {
				
				int sN = que.poll();
				
				count++;
				
				for(int node : graph.get(sN)) {
					
					que.offer(node);
					
				}
				
				
			}
			System.out.println("#"+t+" "+CommonAncestorNode +" " +count);

		}

	}
	
	public static int FindAncesotrNode(int Node1 ,int Node2) {
		
		boolean[] checkNode = new boolean[V+1];
		
		while(Node1 != 0) {
			
			checkNode[Node1] = true;
			
			Node1 = parent[Node1];
		}
		
		while(!checkNode[Node2]) {
			Node2= parent[Node2];
		}
		return Node2;
		
		
		
		
		
	
	}

}
