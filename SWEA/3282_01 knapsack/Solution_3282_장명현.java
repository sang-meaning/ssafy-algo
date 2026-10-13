import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N+1][2];
			for (int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[][] dp = new int[N+1][K+1];
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=K; j++) {
					int v = arr[i][0];
					int c = arr[i][1];
					
					dp[i][j] = dp[i-1][j];
					
					if (j >= v) {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v] + c);
					}
				}
			}
			
			int answer = dp[N][K];
			sb.append("#").append(test).append(" ").append(answer).append('\n');
		}
		
		
		System.out.print(sb);
	}
}