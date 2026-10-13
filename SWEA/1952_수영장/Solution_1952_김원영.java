import java.util.Scanner;

public class Solution {
	static int[] cost;
	static int[] month;
	static int[] memo;
	static int dp(int number) {
		if(number <= 3) {
	        return memo[number];
	    }
		if(memo[number] == 0) {
			int temp1 = dp(number-1) + month[number] * cost[1];
			int temp2 = dp(number-1) + cost[2];
			int temp3 = dp(number-3) + cost[3];
			
			memo[number] =Math.min(temp3, Math.min(temp1, temp2));
		}
		return memo[number];

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			cost = new int[5];
			for (int i = 1; i <= 4; i++) {
				cost[i] = sc.nextInt();
			}

			month = new int[13];

			for (int i = 1; i <= 12; i++) {
				month[i] = sc.nextInt();
			}
			memo = new int[13];
			memo[0] = 0;
			memo[1] = Math.min(month[1] * cost[1], cost[2]);
			memo[2] = Math.min(memo[1] + month[2] * cost[1], memo[1] + cost[2]);
			memo[3] = Math.min(cost[3], Math.min(memo[2] + month[3] * cost[1], memo[2] + cost[2]));
			
			int answer = Math.min(cost[4], dp(12));
			
			System.out.println("#"+tc+" "+answer);
			
		}
	}
}
