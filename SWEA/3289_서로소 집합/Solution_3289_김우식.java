package swea;

import java.io.*;
import java.util.*;

public class SWEA3289 {
	static int[] parent;
	
	static void makeSet(int n) {
		parent = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(parent[x] == x) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	static void union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if(rootA != rootB) {
			parent[rootB] = rootA;
		}
	}
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			makeSet(n);
			
			sb.append("#").append(tc).append(" ");
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(command == 0) union(a, b);
				else {
					if(findSet(a) == findSet(b)) {
						sb.append(1);
					}else sb.append(0);
					
				}
				
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
