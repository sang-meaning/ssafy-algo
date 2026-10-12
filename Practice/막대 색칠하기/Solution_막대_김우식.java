package Practice.막대 색칠하기;

public class Solution_막대_김우식 {
	public static void main(String[] args) {
		int[] dp = new int[7];
		dp[1] = 2;
		dp[2] = 5;
		for(int i = 3; i <= 6; i++) {
			dp[i] = dp[i - 1] + dp[i - 1] + dp[i - 2];
		}
		System.out.println(dp[6]);
	}
}
