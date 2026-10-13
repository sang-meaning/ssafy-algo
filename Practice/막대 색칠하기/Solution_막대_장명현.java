import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][3];
		
		dp[1][0] = 1; // yellow
		dp[1][1] = 1; // blue
		dp[1][2] = 0; // red
		
		dp[2][0] = 2;
		dp[2][1] = 2;
		dp[2][2] = 1;
		
		for (int i=3; i<=N; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
			dp[i][1] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
			dp[i][2] = dp[i-2][0] + dp[i-2][1] + dp[i-2][2];
		}
		
		int answer = dp[N][0] + dp[N][1] + dp[N][2];
		sb.append(answer);
		System.out.print(sb);
	}
}