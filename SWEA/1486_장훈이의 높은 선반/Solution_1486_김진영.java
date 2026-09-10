import java.io.*;
import java.util.*;


public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,M;
	static int[] book;
	static int[] arr;
	static int result;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			book = new int[N];
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				book[i] = Integer.parseInt(st.nextToken());
			}
			
			result = Integer.MAX_VALUE;
			dfs(0);
			
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.print(sb);
	}
	
	private static void dfs(int depth) {
		
		if(depth == N) {
			int sum=0;
			for(int i=0;i<N;i++) {
				if(arr[i] == 1) {
					sum += book[i];
				}
			}
			if(sum >= M) {
				int diff = sum-M;
				result = Math.min(result, diff);
			}
			return;
		}
		
		arr[depth] = 1;
		dfs(depth+1);
		arr[depth] = 0;
		dfs(depth+1);
		
	}
}
