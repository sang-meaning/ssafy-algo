public class Solution_막대_임성진 {

	public static void main(String[] args) {
		int N = 6;

		int[] dp = new int[N + 1];
		dp[1] = 2;
		dp[2] = 5;

		for (int i = 3; i <= N; i++) {
			dp[i] = 2 * dp[i - 1] + dp[i - 2];
		}

		System.out.println("f(" + N + ") = " + dp[N]); // 169
	}
}
