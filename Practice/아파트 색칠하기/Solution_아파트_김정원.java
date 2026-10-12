package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_아파트_김정원 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[1] = 2;
		dp[2] = 3;
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		System.out.println(dp[N]);
	}
}
