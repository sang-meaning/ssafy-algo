import java.io.*;
import java.util.*;

public class Solution_3289_김민우 {
	static int[] union;
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
			
			union = new int[N+1];
			for(int i = 1; i <= N; i++) {
				union[i] = i;
			}
			
			int cmd, idx1, idx2;
			
			sb.append("#").append(test_case).append(" ");
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				cmd = Integer.parseInt(st.nextToken());
				idx1 = Integer.parseInt(st.nextToken());
				idx2 = Integer.parseInt(st.nextToken());
				
				switch(cmd) {
				case 0:
					unite(idx1, idx2);
					break;
				case 1:
					if(find(idx1)== find(idx2))
						sb.append(1);
					else
						sb.append(0);
					break;
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}//main

	public static void unite(int idx1, int idx2) {
		int root1 = find(idx1);
		int root2 = find(idx2);
		
		if(root1 == root2)
			return;
		union[root1] = root2;
	}
	
	public static int find(int idx) {
		if(idx == union[idx])
			return idx;		
		return union[idx] = find(union[idx]);
	}
}
