import java.util.Scanner;

public class Solution {
	static int[] memo;
	static int dp(int number) {
		if(memo[number] != 0) {
			return memo[number];
		}
		else {
			memo[number] = dp(number-1) * 2 + dp(number-2);
			return memo[number];
		}

		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		memo =  new int[input+1];
		memo[1] = 2;
		memo[2] = 5;
		
		System.out.println(dp(input));
		
		
		
	}
}
