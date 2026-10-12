package Practice.아파트 색칠하기;

public class Solution_아파트_김우식 {
	public static void main(String[] args) {
		int[] dp = new int[9];
		dp[1] = 2;
		dp[2] = 3;
		for(int i = 3; i <= 8; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		System.out.println(dp[8]);
	}
}
