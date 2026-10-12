package ssafy.swea.kjw;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1251_김정원_크루스칼 {
	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static class Vertex {
		long x,y;

		public Vertex(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Edge[] edgeList;
	static Vertex[] vertexList;
	static int[] parents;
	static double E;
	static int N;
	
	static void makeSets() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) { // a 가 속한 집합의 대표자 리턴
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]); // pass compression
	}
	
	static boolean union(int a, int b) { // a,b 가 속한 집합 합치기
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false; // 이미 같은 집합이어서 합치기 실패
		parents[bRoot] = aRoot;
		return true;
	}	
    
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int test_case = 1; test_case <= T; test_case++) {
        	N = Integer.parseInt(br.readLine());
        	edgeList = new Edge[N * (N - 1) / 2];
        	vertexList = new Vertex[N];
        	parents = new int[N];
        	st = new StringTokenizer(br.readLine());
        	for (int n = 0; n < N; n++) {
        		vertexList[n] = new Vertex(Integer.parseInt(st.nextToken()), 0);
        	}
        	st = new StringTokenizer(br.readLine());
        	for (int n = 0; n < N; n++) {
        		vertexList[n].y = Integer.parseInt(st.nextToken());
        	}
        	E = Double.parseDouble(br.readLine());
        	// 연결 가능한 간선의 가중치를 계산
        	int cnt = 0;
        	for (int s = 0; s < N; s++) {
        		for (int e = s + 1; e < N; e++) {
        			long weight = calWeight(vertexList[s], vertexList[e]);
        			edgeList[cnt++] = new Edge(s, e, weight);
        		}
        	}
        	Arrays.sort(edgeList);
        	makeSets();
    		long result = 0; // MST 비용
    		int edgeCnt = 0; // 사용된 간선수
    		for (Edge edge : edgeList) {
    			// union 성공 : 두 트리가 서로 다른 트리였고 합치기 성공 (싸이클이 발생하지 않는다)
    			if (union(edge.from, edge.to)) {
    				result += edge.weight;
    				if (++edgeCnt == N-1) break;
    			}
    		}
    		System.out.println(String.format("#%d %d", test_case, Math.round(result * E)));
        }
    }

    private static long calWeight(Vertex s, Vertex e) {
        long dx = s.x - e.x;
        long dy = s.y - e.y;
        return dx * dx + dy * dy;
    }
    
}