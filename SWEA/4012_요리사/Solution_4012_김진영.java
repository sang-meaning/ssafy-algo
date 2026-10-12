import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T;
	static int N;
	static int[][] arr;
	static int[] visited;
	static int result;
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N][N];
			visited = new int[N];
			result = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0,0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void dfs(int depth, int start) {
		
		if(depth == N/2) {
			
			int food1 = 0;
			int food2 = 0;
			
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					if(visited[i] == 1 && visited[j] == 1) {
						food1 += arr[i][j];
						food1 += arr[j][i];
					}
					else if(visited[i] == 0 && visited[j] == 0){
						food2 += arr[i][j];
						food2 += arr[j][i];
					}
				}
			}
			int diff = Math.abs(food1-food2);
			result = Math.min(result,diff);

			return;
		}
		
		for(int i=start;i<N;i++) {
			if(visited[i] == 1) {
				continue;
			}
			visited[i] = 1;
			dfs(depth+1,i);
			visited[i] = 0;			 
		}
		//dfs(depth+1,start+1);
	}
	
}