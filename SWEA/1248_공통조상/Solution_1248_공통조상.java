package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1248_이윤찬 {

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
			
			
			// 공통조상의 노드를 찾는 함수 FindAncesotrNode 
			int CommonAncestorNode = FindAncesotrNode(N1,N2);
			
			// 서브트리의 길이를 구하기 위해 BFS 수행..
			Queue<Integer> que = new  ArrayDeque<>();
			
			que.offer(CommonAncestorNode);
			
			while(!que.isEmpty()) {
				
				int sN = que.poll();
				// 노드하나 확인할때마다 카운트 증가
				count++;
				
				for(int node : graph.get(sN)) {
					
					que.offer(node);
					
				}
				
				
			}
			System.out.println("#"+t+" "+CommonAncestorNode +" " +count);

		}

	}
	
	public static int FindAncesotrNode(int Node1 ,int Node2) {
		// 가장 가까운 공통조상을 찾기 위해서  불리언 형태의 배열 
		boolean[] checkNode = new boolean[V+1];
		
		// 첫번째 노드번호 이용해서 최종 노드는 1-->0으로 마감하기 때문에 0이 아닐 때까지 돌아간다.
		while(Node1 != 0) {
			// 첫 시작 노드부터 TRUE 처리
			checkNode[Node1] = true;
			// Node1의 부모 대입 
			Node1 = parent[Node1];
			// 반복 
		}
		// node2를 cheknode에 넣어서 확인했을때 True이면 멈춘다... 
		while(!checkNode[Node2]) {
			// false일때 Node2의 값을 부모로 초기화 
			Node2= parent[Node2];
			
//			if(Node2==0) {
//				break;
//			}
		}
		
		//최종 Node2의 값이 공통 조상으로 판별 리턴해준다.
		return Node2;
		
		
		
		
		
	
	}

}
