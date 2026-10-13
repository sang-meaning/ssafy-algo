
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T,N;
	static int day,month,three,year;
	static int[] pool;
	static int[] price;
	static int result;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			three = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			pool = new int[13];
			price = new int[13];
			
			result = Integer.MAX_VALUE;
			Arrays.fill(price, Integer.MAX_VALUE);
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<13;i++) {
				pool[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1;i<13;i++) {
				price[i] = Math.min(pool[i]*day, month);
			}

			dfs(1,0);
			
			result = Math.min(result, year);
			
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.print(sb);
		
	}
	
	private static void dfs(int depth,int total) {
		
		if(depth > 12) {
			result = Math.min(result, total);
			return;
		}
		
		dfs(depth+1,total+price[depth]);
		dfs(depth+3,total+three);
		
	}
}
