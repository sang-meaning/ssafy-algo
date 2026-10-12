import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,M,result;
	static int[][] map;
	static int[] arr;
	static int[] used;
	static int mins;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[M][2];
			arr = new int[N];
			used = new int[N];
			result = 0;
			mins = 0;
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}

			dfs(0);
			sb.append("#"+tc+" "+(result-mins)+"\n");
		}
		System.out.print(sb);
		
	}
	
	private static void dfs(int depth) {
		if(depth == N) {
			result++;

			for(int i=0;i<M;i++) {
				int a = map[i][0]-1;
				int b = map[i][1]-1;
				if(arr[a] == 1 && arr[b] == 1) {
					mins++;
					break;
				}
			}
			return;
		}
		arr[depth] = 1;
		dfs(depth+1);
		arr[depth] = 0;
		dfs(depth+1);
	}
	
}
