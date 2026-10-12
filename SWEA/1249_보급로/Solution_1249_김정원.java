
package ssafy.swea.kjw;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1249_김정원 {
	static class Position implements Comparable<Position> {
		int to, from, cost;

		public Position(int to, int from, int cost) {
			super();
			this.to = to;
			this.from = from;
			this.cost = cost;
		}

		public Position(int to, int cost) {
			super();
			this.from = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Position [to=" + to + ", from=" + from + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	static int V;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String st = null;
        for (int test_case = 1; test_case <= T; test_case++) {
        	V = Integer.parseInt(br.readLine());	
        	int[][] battleMap = new int[V][V];
        	
        	boolean[] visited = new boolean[V*V];
        	Position[][] adjList = new Position[V*V][];
        	int[] minCost = new int[V*V];
        	for (int i = 0; i < V; i++) {
        		st = br.readLine();
        		for (int j = 0; j < V; j++) {
        			battleMap[i][j] = st.charAt(j) - '0'; 
        		}
        	}
        	for (int y = 0; y < V; y++) {
        	    for (int x = 0; x < V; x++) {
        	        int from = y * V + x;
        	        List<Position> edges = new ArrayList<>();
        	        
        	        if (x + 1 < V) {
        	            int to = from + 1;
        	            int cost = battleMap[y][x + 1];
        	            edges.add(new Position(to, from, cost));
        	        }

        	        if (y + 1 < V) {
        	            int to = from + V;
        	            int cost = battleMap[y + 1][x];

        	            edges.add(new Position(to, from, cost));
        	        }
        	        
        	        if (x - 1 >= 0) {
        	            int to = from - 1;
        	            int cost = battleMap[y][x - 1];
        	            edges.add(new Position(to, from, cost));
        	        }

        	        if (y - 1 >= 0) {
        	            int to = from - V;
        	            int cost = battleMap[y - 1][x];

        	            edges.add(new Position(to, from, cost));
        	        }


        	        adjList[from] = edges.toArray(new Position[0]);
        	    }
        	}
        	
    		PriorityQueue<Position> pq = new PriorityQueue<>();
    		Arrays.fill(minCost, Integer.MAX_VALUE);
    		int to = 0;
    		int from = 0;
    		int cost = 0;
    		minCost[to] = 0;
    		pq.offer(new Position(to, from, cost));
    		
    		while (!pq.isEmpty()) {
    			// step 1.미방문 정점 중 최단거리의 정점 선택
    			// 수정된 코드 시작
    			Position v = pq.poll();
    			int min = v.cost;
    			int cur = v.from;
    			
				if (visited[cur]) continue; // pq에 들어있는 동일 정점에 대한 잔재
				visited[cur] = true; // 방문 처리
				
    			
    			// step 2. 선택된 정점을 경유지로 하여 미방문 인접정점들의 최단거리 비용과 비교하여 최소값 갱신
    			for (Position cur_pos : adjList[cur]) {
    				if (!visited[cur_pos.to] && minCost[cur_pos.to] > min + cur_pos.cost) {
    					minCost[cur_pos.to] = min + cur_pos.cost;
    					// System.out.println(min + " " +cur_pos.cost);
    					pq.offer(new Position(cur_pos.to, minCost[cur_pos.to])); // 추가된 코드
    				}
    			}
    		}
    		int end = V*V-1;
//        	for (int y = 0; y < V; y++) {
//        	    for (int x = 0; x < V; x++) {
//        	    	System.out.print(minCost[y * V + x] + " ");
//        	    }
//        	    System.out.println();
//        	}
    		// System.out.println(minCost[end] != INF ? minCost[end] : -1);
    		System.out.println(String.format("#%d %d", test_case, minCost[end]));
        }
    }

}