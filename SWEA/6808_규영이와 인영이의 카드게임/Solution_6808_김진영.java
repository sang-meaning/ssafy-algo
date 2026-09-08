
import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T;
	static int result;
	static int[] arr;
	static int[] arr2;
	static int[] card;
	static int[] visited;
	static int[] used;
	static int sum;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			
			int n = 9;
			sum = 1;
			result = 0;
			for(int i=1;i<=n;i++) {
				sum = sum * i;
			}

			arr = new int[n];
			arr2 = new int[n];
			used = new int[n];
			visited = new int[n];
			card = new int[n*2+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				card[arr[i]] = 1;
			}
			
			int index = 0;
			for(int i=1;i<n*2+1;i++) {
				if(card[i] == 0) {
					arr2[index++] = i;
				}
			}

			dfs(0);

			
			sb.append("#"+test_case+" "+result+" "+(sum-result)+"\n");
		}
		System.out.print(sb);
	}
	
	private static void dfs(int depth) {
		if(depth == 9) {

			int total=0;
			int total2=0;
			for(int i=0;i<9;i++) {
				if(arr[i] > arr2[visited[i]]) {
					total = total + arr[i] + arr2[visited[i]];
				}else if(arr[i] < arr2[visited[i]]) {
					total2 = total2 + arr[i] + arr2[visited[i]];
				}
			}
			if(total > total2) {
				result++;
			}
			return;
		}
		
		for(int i=0;i<9;i++) {
			
			if(used[i] == 1) {
				continue;
			}
			
			used[i] = 1;
			visited[depth] = i;
			dfs(depth+1);
			used[i] = 0;
		}
		
		
	}

	
}
