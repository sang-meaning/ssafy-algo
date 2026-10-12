import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {
	
	static int N;
	static boolean[] visited;
	static int[][] synergy;
	static int[][] pairSynergy;
	static int min;//차이의 최솟값
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt(); // 재료의 수
			visited = new boolean[N];			
			synergy = new int[N][N];
			pairSynergy = new int[N][N];
			min = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					synergy[i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i < N; i++) {
			    for (int j = i + 1; j < N; j++) {
			        pairSynergy[i][j] = synergy[i][j] + synergy[j][i];
			    }
			}
			
			dfs(0,0);
			
			System.out.println("#" + test_case + " " + min);
		}
		
	}
	
	static void dfs(int index, int count) {
		if(count == N/2) {
			int a_value = 0;
			int b_value = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					if (visited[i] && visited[j]) {
			            a_value = a_value + pairSynergy[i][j];
			        }

			        if (!visited[i] && !visited[j]) {
			            b_value = b_value + pairSynergy[i][j];
			        }
				}
			}
			
			int gap = Math.abs(a_value - b_value);
			
			if(gap < min) {
				min = gap;
			}
			
			return;
		}
		
		for(int i = index; i < N; i++) {
			visited[i] = true;
			
			dfs(i + 1,count +1);
			
			visited[i] = false; // 백트래킹
		}
	}
}