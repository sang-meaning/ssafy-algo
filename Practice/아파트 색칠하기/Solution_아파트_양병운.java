package yang;

import java.io.*;
import java.util.*;

public class apartment {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//노란색 : 0
		//파란색 : 1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[0] = 1;
		dp[1] = 2;
		if(N>1)
			for(int i=2; i<=N; i++) dp[i] = dp[i-1]+dp[i-2];
		System.out.println(dp[N]);
	}
}
