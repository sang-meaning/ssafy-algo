import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N;
	static double E,result;
	
	static ArrayList<Integer>[] list;
	static int[] parent;
	
	static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		
		if(fa == fb) {
			return false;
		}
		
		if(parent[fa] > parent[fb]) {
			int temp = fa;
			fa = fb;
			fb = temp;
		}
		
		parent[fa] += parent[fb];
		parent[fb] = fa;		
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			list = new ArrayList[N];
			
			for(int i=0;i<N;i++) {
				list[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
			
			E = Double.parseDouble(br.readLine());
			
			
			ArrayList<long[]> edges = new ArrayList<>();
			
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					int x1 = list[i].get(0);
					int y1 = list[i].get(1);
					
					int x2 = list[j].get(0);
					int y2 = list[j].get(1);
					
					long dx = x1 - x2;
					long dy = y1 - y2;
					
					long cost = dx * dx + dy * dy;
					
					edges.add(new long[] {i,j,cost});
				}
			}
			
			edges.sort((a,b) -> Long.compare(a[2], b[2]));
			
			parent = new int[N];
			Arrays.fill(parent, -1);
			
			long sum = 0;
			int cnt = 0;
			
			for(long[] edge : edges) {
				int a = (int) edge[0];
				int b = (int) edge[1];
				long cost = edge[2];
				
				if(union(a,b)) {
					sum += cost;
					cnt++;
					
					if(cnt == N-1) {
						break;
					}
				}
			}
			result = sum * E;
			
			sb.append("#"+tc+" "+Math.round(result)+"\n");
			
		}
		System.out.print(sb);
		
		
		
	}

}
