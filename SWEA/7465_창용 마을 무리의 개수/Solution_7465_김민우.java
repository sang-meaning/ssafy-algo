import java.io.*;
import java.util.*;

public class Solution_7465_김민우 {
	static int[] town;
	static int T, N, M;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			town = new int[N+1];
			for(int i = 1; i <= N; i++) {
				town[i] = i;
			}
			
			int idx1, idx2;
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				idx1 = Integer.parseInt(st.nextToken());
				idx2 = Integer.parseInt(st.nextToken());
				
				unite(idx1, idx2);
			}
			
			List<Integer> company = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				int p = find(town[i]);
				if(company.contains(p))
					continue;
				company.add(p);
			}
			
			sb.append("#"+test_case+" "+company.size()+"\n");
		}//test_case
		System.out.print(sb);
	}//main
	
	public static void unite(int idx1, int idx2) {
		int p1 = find(idx1);
		int p2 = find(idx2);
		if(p1 == p2)
			return;
		town[p1] = p2;
	}
	
	public static int find(int idx) {
		if(idx == town[idx])
			return idx;
		return town[idx] = find(town[idx]);
	}

}
