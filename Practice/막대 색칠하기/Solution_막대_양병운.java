package yang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class branch {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[1] = 2;
		dp[2] = 5;
		for(int i=3; i<=N; i++) {
			dp[i] = dp[i-2] + 2*dp[i-1];
		}
		System.out.println(dp[N]);
		//파란색 1, 노란색 1, 빨간색 2
		//1, 2, 3, 4		   5
		//2, 5, 10, 16+f[2]=21, 32+f[3]
		
	}
}
