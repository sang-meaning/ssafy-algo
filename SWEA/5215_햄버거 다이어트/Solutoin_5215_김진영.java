
import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,L;
	static int[] food;
	static int[] cal;
	static int[] visited;
	static int result;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			food = new int[N];
			cal = new int[N];
			visited = new int[N];
			result = Integer.MIN_VALUE;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				food[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0);
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		System.out.print(sb);
		
	}
	
	private static void dfs(int depth) {
		if(depth == N) {
			int sum = 0;
			int sum2 = 0;
			for(int i=0;i<N;i++) {
				//System.out.printf("%d ",visited[i]);
				if(visited[i] == 1) {
					sum += cal[i];
					sum2 += food[i];
				}
			}
			if(sum < L) {
				result = Math.max(result, sum2);
			}
			return;
		}
		
		visited[depth] = 0;
		dfs(depth+1);
		
		visited[depth] = 1;
		dfs(depth+1);
	}
	
}




