package alg_prac_solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_1251_크루스칼_이승규 {
	static int n;
	static Island[] island;
	static double e;
	
	// 크루스칼에서 사용할 부모 배열
	static int[] parent;
	
	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		 */
//		System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(bf.readLine());
			
			island = new Island[n];
			
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			int[] x = new int[n];
			int[] y = new int[n];
			
			for(int i = 0; i < n; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			s = bf.readLine();
			st = new StringTokenizer(s);
			
			for(int i = 0; i < n; i++) {
				y[i] = Integer.parseInt(st.nextToken());
				island[i] = new Island(x[i], y[i]);
			}
			
			e = Double.parseDouble(bf.readLine());
			
			long answer = mst();
			long realAnswer = Math.round(answer * e);
			
			System.out.println("#" + test_case + " " + realAnswer);
		}
	}
	
	static class Island {
		int islandX;
		int islandY;
		
		Island(int x, int y) {
			this.islandX = x;
			this.islandY = y;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long w;
		
		Edge(int from, int to, long w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static long mst() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				long dist = distance(island[i], island[j]);
				
				pq.offer(new Edge(i, j, dist));
			}
		}
		
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		long sum = 0;
		int count = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
	
			if(find(edge.from) == find(edge.to))
				continue;
			
			union(edge.from, edge.to);
			
			sum += edge.w;
			count++;
			
			if(count == n - 1)
				break;
		}
		
		return sum;
	}
	
	static int find(int x) {
		if(parent[x] == x)
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] = a;
		}
	}
	
	static long distance(Island islandA, Island islandB) {
		long x = (long) islandA.islandX - islandB.islandX;
		long y = (long) islandA.islandY - islandB.islandY;
		
		return x * x + y * y;
	}
}