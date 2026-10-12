package alg_prac_solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.PriorityBlockingQueue;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class Solution_1251_프림_이승규 {
	static int n;
	static Island[] island;
	static double e;
	static boolean[] visited;
	
	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
//System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */

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
				island[i] = new Island(x[i],y[i]);
			}
			
			e = Double.parseDouble(bf.readLine());
			
			visited = new boolean[n];			
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
	
	static class Pair implements Comparable<Pair> {
		int n;
		long w;
		Pair(int n, long w) {
			this.n =n;
			this.w = w;
		}
		public int compareTo(Pair o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static long mst() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		long sum = 0;
		
		pq.offer(new Pair(0,0)); // 보통 0부터 시작 << 
		
		while(!pq.isEmpty()) {
			Pair pair = pq.poll();
			if(visited[pair.n])
				continue;
			visited[pair.n] = true;
			sum += pair.w;
			
			// 여기서부터 AI 추가 코드
			// 그 섬에서 방문하지 않은 모든 섬으로 가는 간선을 pq에 추가하는 내용
			for(int i = 0; i < n; i++) {
				if(!visited[i]) { // i번쩌 섬을 방문하지 않았으면, 간선 추가 대상
					long dist = distance(island[pair.n], island[i]);
					
					pq.offer(new Pair(i, dist));
				}
			}
		}
		return sum;
	}
	
	static long distance(Island islandA, Island islandB) {
		long x = (long) Math.abs(islandA.islandX - islandB.islandX);
		long y = (long) Math.abs(islandA.islandY - islandB.islandY);
		
		return x * x + y * y;
	}
}