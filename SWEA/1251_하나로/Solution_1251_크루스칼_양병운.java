import java.util.*;
import java.io.*;
class Solution {
    static class Land{
        int x, y;
        public Land(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static class Edge implements Comparable<Edge> {
        int n1, n2;
        long weight;
        public Edge(int n1, int n2){
            this.n1 = n1;
            this.n2 = n2;
        }
        public void setWeight(long weight){
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.weight, e.weight);
        }
    }
    static int N;
    static double E;
    static int[] parents;
    public static void makeSets() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }
    public static int find(int idx){
        if(parents[idx]==idx) return idx;
        return parents[idx] = find(parents[idx]);
    }
    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot==bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
            Land[] lands = new Land[N];
            StringTokenizer stx = new StringTokenizer(br.readLine());
            StringTokenizer sty = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                int x = Integer.parseInt(stx.nextToken());
                int y = Integer.parseInt(sty.nextToken());
                lands[i] = new Land(x, y);
            }
            E = Double.parseDouble(br.readLine());
            //환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)만큼 지불
            Edge[] edges = new Edge[N * (N - 1) / 2];
            int edgeCount = 0;
            for(int i=0; i<N; i++){
                for(int j=i+1; j<N; j++){
                    Edge edge = new Edge(i, j);
                    edge.weight = dist(lands[i], lands[j]);
                    edges[edgeCount++] = edge;
                }
            }
            Arrays.sort(edges);
            makeSets();
            long totalWeight = 0;
            int selectedCount= 0;
            for(Edge e : edges){
                if(union(e.n1, e.n2)){
                    totalWeight += e.weight;
                    selectedCount++;
                    if(selectedCount == N-1) break;
                }
            }
            long answer = Math.round(totalWeight * E);
            System.out.println("#" + test_case + " " + answer);
		}
	}
    public static long dist(Land a, Land b) {
        long dx = (long) a.x - b.x;
        long dy = (long) a.y - b.y;
        return dx * dx + dy * dy;
    }
}