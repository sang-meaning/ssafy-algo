import java.io.*;
import java.util.StringTokenizer;

public class Solution_¸·´ë_±è¹Î¿ì {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][3];
		
		dp[1][0] = dp[1][1] = 1;
		dp[1][2] = 0;
		dp[2][0] = dp[2][1] = 2;
		dp[2][2] = 1;
		
		for(int i = 3; i <= N; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
			dp[i][1] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
			dp[i][2] = dp[i-2][0] + dp[i-2][0] + dp[i-2][2];
		}
		int ans = dp[N][0] + dp[N][1] + dp[N][2];
		
		System.out.println(ans);
	}
}
