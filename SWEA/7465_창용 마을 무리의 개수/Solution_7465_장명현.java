import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int[] p;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			p = new int[N+1];
			for (int i=1; i<=N; i++) p[i] = i;

			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				union(u, v);
			}
			
			boolean[] group = new boolean[N+1];
			for (int i=1; i<=N; i++) {
				// p[x] = find(p[x])를 했더라도, p[x] 는 root가 아닐 수 있음. 유의.
				group[find(i)] = true;
			}
			
			int answer = 0;
			for (int i=1; i<=N; i++) {
				if (group[i]) answer++;
			}

			sb.append('#').append(test_case).append(' ');
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static int find(int x) {
		if (p[x] == x) {
			return x;
		} else {
			p[x] = find(p[x]);
			return p[x];
		}
	}
	
	public static int isUnion(int u, int v) {
		return find(u) == find(v) ? 1 : 0;
	}
	
	public static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) p[v] = u;
	}
}